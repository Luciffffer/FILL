package be.kdg.fill.views.gamemenu.levelselect;

import java.util.LinkedList;

import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.World;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.Reloadable;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.compontents.LevelCard;
import be.kdg.fill.views.game.GamePresenter;
import be.kdg.fill.views.game.GameView;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class LevelSelectPresenter implements Presenter, Reloadable {
    
    private LevelSelectView view;
    private GameMenuPresenter parent;
    private World world;

    public static final String SCREEN_NAME = "levelselect";

    public LevelSelectPresenter(LevelSelectView levelSelectView, GameMenuPresenter parent) 
    {
        this.view = levelSelectView;
        this.parent = parent;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        view.getBackButton().setOnAction(e -> {
            parent.getSubScreenManager().switchBack();

            WorldSelectPresenter worldSelectPresenter = (WorldSelectPresenter) parent.getSubScreenManager().getCurrentScreen();
            worldSelectPresenter.reload();
        });
    }

    private void updateViewToGame(Level level) 
    {
        ScreenManager mainScreenManager = this.parent.getMainScreenManager();

        if (mainScreenManager.screenExists("game")) {
            mainScreenManager.switchScreen("game");
            GamePresenter gamePresenter = (GamePresenter) mainScreenManager.getCurrentScreen();
            gamePresenter.startGame(level);
        } else {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameView, this.parent.getMainScreenManager(), this.parent.getLoggedInUser());
            mainScreenManager.addScreen(gamePresenter);
            gamePresenter.startGame(level);
        }
    }

    @Override
    public void reload() 
    {
        this.setWorld(this.world);
    }


    // GETTERS

    @Override
    public LevelSelectView getView() 
    {
        return this.view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }


    // SETTERS

    public void setWorld(World world) 
    {
        this.world = world;
        this.view.getTitle().setText("The " + this.world.getName());
        this.view.getLevelCount().setText(this.parent.getLoggedInUser().getWorldProgress(this.world.getId()) + "/" + this.world.getLevelCount());
        this.view.getLevelCards().getChildren().clear();
        
        LinkedList<Level> levels = this.world.getLevels();

        for (Level level: levels) {

            int UserProgress = this.parent.getLoggedInUser().getWorldProgress(world.getId());
            boolean locked = level.getId() > UserProgress + 1;
            
            LevelCard levelCard = new LevelCard(level.getId(), level.getId() <= UserProgress, locked);
            this.view.addLevelCard(levelCard);

            if (!locked) {
                levelCard.setOnAction(e -> {
                    this.updateViewToGame(level);
                });
            } else {
                levelCard.setOnAction(e -> { // rotate animation
                    RotateTransition rotateTransition = levelCard.getRotateTransition();

                    if (rotateTransition == null) {
                        rotateTransition = new RotateTransition(Duration.millis(100), levelCard);
                        rotateTransition.setByAngle(10);
                        rotateTransition.setCycleCount(4);
                        rotateTransition.setAutoReverse(true);
                        levelCard.setRotateTransition(rotateTransition);
                    }

                    if (rotateTransition.getStatus() == RotateTransition.Status.STOPPED) {
                        rotateTransition.play();
                    }
                });
            }

        }

    }

}

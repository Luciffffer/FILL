package be.kdg.fill.views.gamemenu.levelselect;

import java.util.LinkedList;

import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.World;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.compontents.LevelCard;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class LevelSelectPresenter implements Presenter {
    
    private LevelSelectView view;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;
    private World world;

    public static final String SCREEN_NAME = "levelselect";

    public LevelSelectPresenter(LevelSelectView levelSelectView, ScreenManager mainScreenManager, ScreenManager subScreenManager, World world) 
    {
        this.view = levelSelectView;
        this.mainScreenManager = mainScreenManager;
        this.subScreenManager = subScreenManager;
        this.setWorld(world);
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        view.getBackButton().setOnAction(e -> {
            this.subScreenManager.switchBack();

            WorldSelectPresenter worldSelectPresenter = (WorldSelectPresenter) this.subScreenManager.getCurrentScreen();
            worldSelectPresenter.reload();
        });
    }

    private void updateViewToGame(Level level) 
    {
        
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
        this.view.getLevelCount().setText("0/" + this.world.getLevelCount());
        this.view.getLevelCards().getChildren().clear();
        
        LinkedList<Level> levels = this.world.getLevels();

        for (Level level: levels) {
            boolean locked = level.getId() > 2 ? true : false;
            LevelCard levelCard = new LevelCard(level.getId(),level.getId() == 1 ? true : false , locked);
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

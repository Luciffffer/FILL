package be.kdg.fill.views.gamemenu.worldselect;

import java.util.LinkedList;

import be.kdg.fill.models.core.World;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.Reloadable;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.compontents.WorldCard;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.levelselect.LevelSelectPresenter;
import be.kdg.fill.views.gamemenu.levelselect.LevelSelectView;

public class WorldSelectPresenter implements Presenter, Reloadable {
    
    private WorldSelectView view;
    private GameMenuPresenter parent;

    public static final String SCREEN_NAME = "worldselect";

    public WorldSelectPresenter(WorldSelectView worldSelectView, GameMenuPresenter parent) 
    {
        this.view = worldSelectView;
        this.parent = parent;
        this.view.setSmallTitle("Hello " + this.parent.getLoggedInUser().getUsername() + "!");
        this.addWorldsToView();
    }

    private void addWorldsToView() 
    {
        LinkedList<World> worlds = this.parent.getWorldLoader().getWorlds();

        for (World world: worlds) {
            WorldCard worldCard = new WorldCard(world, this.parent.getLoggedInUser().getWorldProgress(world.getId()));

            worldCard.setOnAction(e -> {
                this.updateViewToLevelSelect(world);
            });

            this.view.addWorldCard(worldCard);
        }
    }

    private void updateViewToLevelSelect(World world) 
    {
        ScreenManager subScreenManager = this.parent.getSubScreenManager();

        if (subScreenManager.screenExists("levelselect")) {
            subScreenManager.switchScreen("levelselect");
            LevelSelectPresenter levelSelectPresenter = (LevelSelectPresenter) subScreenManager.getCurrentScreen();
            levelSelectPresenter.setWorld(world);
        } else {
            LevelSelectView levelSelectView = new LevelSelectView();
            LevelSelectPresenter levelSelectPresenter = new LevelSelectPresenter(levelSelectView, this.parent);
            levelSelectPresenter.setWorld(world);
            subScreenManager.addScreen(levelSelectPresenter);
        }
    }

    @Override
    public void reload() 
    {
        this.view.clearWorldCards();
        this.addWorldsToView();
    }


    // GETTERS

    @Override
    public WorldSelectView getView() 
    {
        return view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }
}

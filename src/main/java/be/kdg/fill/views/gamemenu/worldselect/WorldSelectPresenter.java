package be.kdg.fill.views.gamemenu.worldselect;

import java.util.LinkedList;

import be.kdg.fill.models.core.User;
import be.kdg.fill.models.core.World;
import be.kdg.fill.models.helpers.WorldLoader;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.compontents.WorldCard;
import be.kdg.fill.views.gamemenu.levelselect.LevelSelectPresenter;
import be.kdg.fill.views.gamemenu.levelselect.LevelSelectView;

public class WorldSelectPresenter implements Presenter {
    
    private WorldSelectView view;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;
    private User loggedInUser;

    public static final String SCREEN_NAME = "worldselect";

    public WorldSelectPresenter(WorldSelectView worldSelectView, ScreenManager mainScreenManager, ScreenManager subScreenManager, User loggedInUser) 
    {
        this.view = worldSelectView;
        this.mainScreenManager = mainScreenManager;
        this.subScreenManager = subScreenManager;
        this.loggedInUser = loggedInUser;
        this.view.setSmallTitle("Hello " + loggedInUser.getUsername() + "!");
        this.addWorldsToView();
    }

    private void addWorldsToView() 
    {
        LinkedList<World> worlds = WorldLoader.getWorlds();

        for (World world: worlds) {
            WorldCard worldCard = new WorldCard(world);

            worldCard.setOnAction(e -> {
                this.updateViewToLevelSelect(world);
            });

            this.view.addWorldCard(worldCard);
        }
    }

    private void updateViewToLevelSelect(World world) 
    {
        if (subScreenManager.screenExists("levelselect")) {
            subScreenManager.switchScreen("levelselect");
            LevelSelectPresenter levelSelectPresenter = (LevelSelectPresenter) subScreenManager.getCurrentScreen();
            levelSelectPresenter.setWorld(world);
        } else {
            LevelSelectView levelSelectView = new LevelSelectView();
            LevelSelectPresenter levelSelectPresenter = new LevelSelectPresenter(levelSelectView, mainScreenManager, subScreenManager, world);
            subScreenManager.addScreen(levelSelectPresenter);
        }
    }

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

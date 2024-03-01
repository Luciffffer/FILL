package be.kdg.fill.views.gamemenu;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectView;
import be.kdg.fill.views.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.mainmenu.MainMenuView;

public class GameMenuPresenter implements Presenter {
 
    public static final String SCREEN_NAME = "gamemenu";
    private GameMenuView view;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;

    public GameMenuPresenter(GameMenuView gameMenuView, ScreenManager mainScreenManager) 
    {
        this.view = gameMenuView;
        this.mainScreenManager = mainScreenManager;
        this.initializeScreenManager();
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        view.getLogOutButton().setOnAction(e -> {
            this.updateViewToLogOut();
        });
    }

    private void initializeScreenManager() 
    {
        this.subScreenManager = new ScreenManager();
        WorldSelectView worldSelectView = new WorldSelectView();
        WorldSelectPresenter worldSelectPresenter = new WorldSelectPresenter(worldSelectView, mainScreenManager, subScreenManager);
        subScreenManager.addScreen(worldSelectPresenter);

        this.view.setContent(subScreenManager.getRootNode());
    }

    private void updateViewToLogOut() 
    {
        if (mainScreenManager.screenExists("mainmenu")) {
            mainScreenManager.switchScreen("mainmenu");
        } else {
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, mainScreenManager);
            mainScreenManager.addScreen(mainMenuPresenter);
        }
    }


    // GETTERS

    @Override
    public GameMenuView getView() 
    {
        return view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }
}

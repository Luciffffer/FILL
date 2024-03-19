package be.kdg.fill.views.startmenu;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import be.kdg.fill.views.startmenu.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.startmenu.mainmenu.MainMenuView;

public class StartMenuPresenter implements Presenter {
    
    private StartMenuView view;
    private User model;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;

    public static final String SCREEN_NAME = "startmenu";

    public StartMenuPresenter(StartMenuView startMenuView, ScreenManager mainScreenManager, User model) 
    {
        this.view = startMenuView;
        this.mainScreenManager = mainScreenManager;
        this.model = model;
        this.initializeSubScreenManager();
    }

    private void initializeSubScreenManager()
    {
        this.subScreenManager = new ScreenManager();
        
        // create mainmenu
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, this);
        this.subScreenManager.addScreen(mainMenuPresenter);

        this.view.setContent(subScreenManager.getRootNode());
    }
    
    public void updateViewToGameMenu()
    {
        GameMenuView gameMenuView = new GameMenuView();
        GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView, mainScreenManager, model);
        mainScreenManager.addScreen(gameMenuPresenter);
    }
    

    // GETTERS

    public User getModel() 
    {
        return model;
    }

    public ScreenManager getMainScreenManager() 
    {
        return mainScreenManager;
    }

    public ScreenManager getSubScreenManager() 
    {
        return subScreenManager;
    }

    @Override
    public StartMenuView getView() 
    {
        return view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }

}

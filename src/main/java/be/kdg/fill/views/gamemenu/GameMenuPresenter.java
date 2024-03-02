package be.kdg.fill.views.gamemenu;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectView;

public class GameMenuPresenter implements Presenter {
 
    public static final String SCREEN_NAME = "gamemenu";
    private GameMenuView view;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;
    private User loggedInUser;

    public GameMenuPresenter(GameMenuView gameMenuView, ScreenManager mainScreenManager, User loggedInUser) 
    {
        this.view = gameMenuView;
        this.mainScreenManager = mainScreenManager;
        this.loggedInUser = loggedInUser;
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
        WorldSelectPresenter worldSelectPresenter = new WorldSelectPresenter(worldSelectView, mainScreenManager, subScreenManager, loggedInUser);
        subScreenManager.addScreen(worldSelectPresenter);

        this.view.setContent(subScreenManager.getRootNode());
    }

    private void updateViewToLogOut() 
    {
        mainScreenManager.switchScreen("mainmenu");
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

package be.kdg.fill.views.gamemenu;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.admin.dimention.DimentionPresenter;
import be.kdg.fill.views.admin.dimention.DimentionView;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectView;
import be.kdg.fill.views.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

    public GameMenuPresenter(GameMenuView gameMenuView, ScreenManager screenManager) {
        this.view = gameMenuView;
        this.subScreenManager = screenManager;
    }

    private void addEventHandlers()
    {
        view.getLogOutButton().setOnAction(e -> {
            this.updateViewToLogOut();
        });
        view.getAddLevelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (loggedInUser.getUsername().equals("Amir")) {
                    updateViewToDimention();
                } else {
                    view.getAddLevelMessage().setText("To add a level you need to be Admin!");
                }
            }
        });
    }

    private void initializeScreenManager()
    {
        this.subScreenManager = new ScreenManager();
        WorldSelectView worldSelectView = new WorldSelectView(loggedInUser.getUsername());
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

//    private void updateViewToDimention(){
//        if (mainScreenManager.screenExists("dimention")){
//            mainScreenManager.switchScreen("dimention");
//        } else {
//            AddLevelView addLevelView = new AddLevelView();
//            AddLevelPresenter addLevelPresenter = new AddLevelPresenter(addLevelView, subScreenManager);
//            subScreenManager.addScreen(addLevelPresenter);
//        }
//    }

    private void updateViewToDimention(){
        if (mainScreenManager.screenExists("dimention")){
            mainScreenManager.switchScreen("dimention");
        } else {
            DimentionView dimTestView = new DimentionView();
            DimentionPresenter dimTestPresenter = new DimentionPresenter(dimTestView, subScreenManager);
            subScreenManager.addScreen(dimTestPresenter);
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

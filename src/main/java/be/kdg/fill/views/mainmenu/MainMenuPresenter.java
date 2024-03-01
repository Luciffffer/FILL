package be.kdg.fill.views.mainmenu;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainMenuPresenter implements Presenter {

    public static final String SCREEN_NAME = "mainmenu";
    private MainMenuView view;
    private ScreenManager mainScreenManager;

    public MainMenuPresenter(MainMenuView mainMenuView, ScreenManager mainScreenManager) 
    {
        this.view = mainMenuView;
        this.mainScreenManager = mainScreenManager;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        
        view.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToLogin();
            }
        });

        view.getRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToRegister();
            }
        });
    }

    private void updateViewToLogin() 
    {
        if (mainScreenManager.screenExists("gamemenu")) {
            mainScreenManager.switchScreen("gamemenu");
        } else {
            GameMenuView gameMenuView = new GameMenuView();
            GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView, mainScreenManager);
            mainScreenManager.addScreen(gameMenuPresenter);
        }
    }

    private void updateViewToRegister() 
    {
        
    }

    @Override
    public MainMenuView getView() 
    {
        return view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }

}

package be.kdg.fill.views.mainmenu;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import be.kdg.fill.views.login.LoginPresenter;
import be.kdg.fill.views.login.LoginView;
import be.kdg.fill.views.signup.SignUpPresenter;
import be.kdg.fill.views.signup.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainMenuPresenter implements Presenter {

    private MainMenuView view;
    private ScreenManager mainScreenManager;

    public static final String SCREEN_NAME = "mainmenu";

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
        if (mainScreenManager.screenExists("login")) {
            mainScreenManager.switchScreen("login");
        } else {
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, mainScreenManager);
            mainScreenManager.addScreen(loginPresenter);
        }
    }

    private void updateViewToRegister() 
    {
        if (mainScreenManager.screenExists("signup")) {
            mainScreenManager.switchScreen("signup");
        } else {
            SignUpView signUpView = new SignUpView();
            SignUpPresenter signUpPresenter = new SignUpPresenter(signUpView, mainScreenManager);
            mainScreenManager.addScreen(signUpPresenter);
        }
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

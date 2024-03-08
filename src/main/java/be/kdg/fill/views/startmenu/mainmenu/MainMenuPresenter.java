package be.kdg.fill.views.startmenu.mainmenu;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import be.kdg.fill.views.startmenu.login.LoginPresenter;
import be.kdg.fill.views.startmenu.login.LoginView;
import be.kdg.fill.views.startmenu.signup.SignUpPresenter;
import be.kdg.fill.views.startmenu.signup.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainMenuPresenter implements Presenter {

    private MainMenuView view;
    private StartMenuPresenter parent;

    public static final String SCREEN_NAME = "mainmenu";

    public MainMenuPresenter(MainMenuView mainMenuView, StartMenuPresenter startMenuPresenter) 
    {
        this.view = mainMenuView;
        this.parent = startMenuPresenter;
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
        ScreenManager screenManager = parent.getSubScreenManager();

        if (screenManager.screenExists("login")) {
            screenManager.switchScreen("login");
        } else {
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, this.parent);
            screenManager.addScreen(loginPresenter);
        }
    }

    private void updateViewToRegister() 
    {
        ScreenManager screenManager = parent.getSubScreenManager();

        if (screenManager.screenExists("signup")) {
            screenManager.switchScreen("signup");
        } else {
            SignUpView signUpView = new SignUpView();
            SignUpPresenter signUpPresenter = new SignUpPresenter(signUpView, this.parent);
            screenManager.addScreen(signUpPresenter);
        }
    }


    // GETTERS

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

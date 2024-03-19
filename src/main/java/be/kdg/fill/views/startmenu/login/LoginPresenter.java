package be.kdg.fill.views.startmenu.login;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.Reloadable;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import be.kdg.fill.views.startmenu.signup.SignUpPresenter;
import be.kdg.fill.views.startmenu.signup.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginPresenter implements Presenter, Reloadable {

    private LoginView loginView;
    private StartMenuPresenter parent;

    public static final String SCREEN_NAME = "login";

    public LoginPresenter(LoginView loginView, StartMenuPresenter startMenuPresenter) 
    {
        this.loginView = loginView;
        this.parent = startMenuPresenter;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        // back button pressed
        loginView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });

        // login button pressed
        loginView.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf(loginView.getUsernameField().getField().getText());
                String password = String.valueOf(loginView.getPasswordField().getField().getText());

                try {
                    parent.getModel().login(username, password);
                    parent.updateViewToGameMenu();
                } catch (IllegalArgumentException e) {
                    loginView.getErrorLabel().setText(e.getMessage());
                }
            }
        });

        // sign up button pressed
        loginView.getSignUpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToSignUp();
            }
        });

    }

    private void updateViewToSignUp()
    {
        if (!parent.getSubScreenManager().screenExists("signup")) {
            SignUpView signUpView = new SignUpView();
            SignUpPresenter signUpPresenter = new SignUpPresenter(signUpView, this.parent);
            parent.getSubScreenManager().addScreen(signUpPresenter);
        } else {
            parent.getSubScreenManager().switchScreen("signup");
        }
    }

    private void updateViewToMainMenu() 
    {
        parent.getSubScreenManager().switchScreen("mainmenu");
    }

    @Override
    public void reload() 
    {
        loginView.getUsernameField().getField().clear();
        loginView.getUsernameField().labelAnimation(200, 1, 0);
        loginView.getPasswordField().getField().clear();
        loginView.getPasswordField().labelAnimation(200, 1, 0);
        loginView.getErrorLabel().setText("");
    }


    // GETTERS

    @Override
    public LoginView getView() 
    {
        return loginView;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }
}

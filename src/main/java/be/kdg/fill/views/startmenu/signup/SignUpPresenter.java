package be.kdg.fill.views.startmenu.signup;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.Reloadable;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import be.kdg.fill.views.startmenu.login.LoginPresenter;
import be.kdg.fill.views.startmenu.login.LoginView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignUpPresenter implements Presenter, Reloadable {

    private SignUpView signUpView;
    private StartMenuPresenter parent;

    public static final String SCREEN_NAME = "signup";

    public SignUpPresenter(SignUpView signUpView, StartMenuPresenter startMenuPresenter) 
    {
        this.signUpView = signUpView;
        this.parent = startMenuPresenter;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        // auto check if username is valid when typed
        signUpView.getUsernameField().getField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    parent.getModel().setUsername(newValue);
                    signUpView.getUsernameField().clearError();;
                } catch (IllegalArgumentException e) {
                    signUpView.getUsernameField().setError(e.getMessage());
                }
            }
        });

        // auto check if password is valid when typed
        signUpView.getPasswordField().getField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    parent.getModel().setPassword(newValue);
                    signUpView.getPasswordField().clearError();
                } catch (IllegalArgumentException e) {
                    signUpView.getPasswordField().setError(e.getMessage());
                }
            }
        });

        // go back to main menu
        signUpView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });

        // sign up button clicked
        signUpView.getSignUpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf(signUpView.getUsernameField().getField().getText());
                String password = String.valueOf(signUpView.getPasswordField().getField().getText());

                try {
                    User user = parent.getModel();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.register();
                    parent.updateViewToGameMenu();
                } catch (IllegalArgumentException e) {
                    // do nothing because errors are already displayed
                }
            }
        });

        // login button clicked
        signUpView.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToLogin();
            }
        });
    }

    private void updateViewToMainMenu() 
    {
        parent.getSubScreenManager().switchScreen("mainmenu");
    }

    private void updateViewToLogin() 
    {
        if (!parent.getSubScreenManager().screenExists("login")) {
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, this.parent);
            parent.getSubScreenManager().addScreen(loginPresenter);
        } else {
            parent.getSubScreenManager().switchScreen("login");
        }
    }

    @Override
    public void reload() 
    {
        this.signUpView.getUsernameField().getField().clear();
        this.signUpView.getUsernameField().labelAnimation(200, 1, 0);
        this.signUpView.getUsernameField().clearError();
        this.signUpView.getPasswordField().getField().clear();
        this.signUpView.getUsernameField().labelAnimation(200, 1, 0);
        this.signUpView.getPasswordField().clearError();
    }


    // GETTERS

    @Override
    public SignUpView getView() 
    {
        return signUpView;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    } 
}

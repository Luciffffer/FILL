package be.kdg.fill.views.startmenu.login;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginPresenter implements Presenter {

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
        loginView.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf(loginView.getUsernameTextField().getText());
                String password = String.valueOf(loginView.getPasswordPasswordField().getText());

                try {

                    parent.getModel().login(username, password);
                    parent.updateViewToGameMenu();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        loginView.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });

    }

    private void updateViewToMainMenu() 
    {
        parent.getSubScreenManager().switchScreen("mainmenu");
    }

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

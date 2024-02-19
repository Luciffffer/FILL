package be.kdg.fill.views.mainmenu;

import be.kdg.fill.views.login.LoginPresenter;
import be.kdg.fill.views.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainMenuPresenter {
    MainMenuView view;

    public MainMenuPresenter(MainMenuView mainMenuView) 
    {
       this.view = mainMenuView;
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
        LoginView loginView = new LoginView();
        LoginPresenter loginPresenter = new LoginPresenter(loginView);
        this.view.getScene().setRoot(loginView);
    }

    private void updateViewToRegister() 
    {
        
    }

}

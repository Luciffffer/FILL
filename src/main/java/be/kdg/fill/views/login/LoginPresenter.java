package be.kdg.fill.views.login;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import be.kdg.fill.views.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LoginPresenter implements Presenter {

    private LoginView loginView;
    private ScreenManager mainScreenManager;
    private User user;

    public static final String SCREEN_NAME = "login";

    public LoginPresenter(LoginView loginView, ScreenManager mainScreenManager)
    {
        this.mainScreenManager = mainScreenManager;
        this.loginView = loginView;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        Button loginButton = loginView.getLoginButton();
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf(loginView.getUsernameTextField().getText());
                String password = String.valueOf(loginView.getPasswordPasswordField().getText());

                try {
                    user = User.login(username, password);
                    updateViewToGameMenu();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button cancelButton = loginView.getCancelButton();
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });

    }

    private void updateViewToMainMenu() 
    {
        if (mainScreenManager.screenExists("mainmenu")) {
            mainScreenManager.switchScreen("mainmenu");
        } else {
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, mainScreenManager);
            mainScreenManager.addScreen(mainMenuPresenter);
        }
    }

    private void updateViewToGameMenu() 
    {
        if (mainScreenManager.screenExists("gamemenu")) {
            mainScreenManager.switchScreen("gamemenu");
        } else {
            GameMenuView gameMenuView = new GameMenuView();
            GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView, mainScreenManager, user);
            mainScreenManager.addScreen(gameMenuPresenter);
        }
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

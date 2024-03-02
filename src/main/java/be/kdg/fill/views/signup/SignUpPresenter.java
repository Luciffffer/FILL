package be.kdg.fill.views.signup;

import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import be.kdg.fill.views.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignUpPresenter implements Presenter {

    private SignUpView signUpView;
    private ScreenManager mainScreenManager;
    private User user;

    public static final String SCREEN_NAME = "signup";

    public SignUpPresenter(SignUpView signUpView, ScreenManager mainScreenManager, User user) 
    {
        this.signUpView = signUpView;
        this.mainScreenManager = mainScreenManager;
        this.user = user;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        signUpView.getSignUpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = String.valueOf((signUpView.getUsernameTextField().getText()));
                String password = String.valueOf((signUpView.getPasswordPasswordField().getText()));

                try {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.register();
                    updateViewToGameMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        signUpView.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToMainMenu();
            }
        });
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

    private void updateViewToMainMenu() 
    {
        if (mainScreenManager.screenExists("mainmenu")) {
            mainScreenManager.switchScreen("mainmenu");
        } else {
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, mainScreenManager, user);
            mainScreenManager.addScreen(mainMenuPresenter);
        }
    }

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

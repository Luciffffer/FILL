package be.kdg.fill.views.mainmenu;

import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
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
        // TEMP CODE
        GameMenuView gameMenuView = new GameMenuView(null);
        GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView);
        this.view.getScene().setRoot(gameMenuView);
    }

    private void updateViewToRegister() 
    {
        
    }

}

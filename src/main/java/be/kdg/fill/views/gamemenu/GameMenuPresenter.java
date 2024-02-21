package be.kdg.fill.views.gamemenu;

import be.kdg.fill.views.mainmenu.MainMenuPresenter;
import be.kdg.fill.views.mainmenu.MainMenuView;

public class GameMenuPresenter {
 
    private GameMenuView view;

    public GameMenuPresenter(GameMenuView gameMenuView) 
    {
        this.view = gameMenuView;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        view.getLogOutButton().setOnAction(e -> {
            this.updateViewToLogOut();
        });
    }

    private void updateViewToLogOut() 
    {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);
        this.view.getScene().setRoot(mainMenuView);
    }

}

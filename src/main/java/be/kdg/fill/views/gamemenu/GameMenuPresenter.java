package be.kdg.fill.views.gamemenu;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.User;
import be.kdg.fill.models.helpers.WorldLoader;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.gamemenu.addworld.AddWorldPresenter;
import be.kdg.fill.views.gamemenu.addworld.AddWorldView;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameMenuPresenter implements Presenter {
 
    public static final String SCREEN_NAME = "gamemenu";
    private GameMenuView view;
    private ScreenManager mainScreenManager;
    private ScreenManager subScreenManager;
    private User loggedInUser;
    private WorldLoader worldLoader;

    public GameMenuPresenter(GameMenuView gameMenuView, ScreenManager mainScreenManager, User loggedInUser) 
    {
        this.view = gameMenuView;
        this.mainScreenManager = mainScreenManager;
        this.loggedInUser = loggedInUser;
        this.worldLoader = new WorldLoader("worlds");
        if (loggedInUser.isAdmin()) this.view.addAddWorldButton();
        this.initializeSubScreenManager();
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        view.getLogOutButton().setOnAction(e -> {
            this.logOut();
        });

        view.getResetButton().setOnAction(e -> {
            this.promptReset();
        });

        view.getAddWorldButton().setOnAction(e -> {
            this.updateViewToAddWorld();
        });
    }

    private void initializeSubScreenManager() 
    {
        this.subScreenManager = new ScreenManager();
        WorldSelectView worldSelectView = new WorldSelectView();
        WorldSelectPresenter worldSelectPresenter = new WorldSelectPresenter(worldSelectView, this);
        subScreenManager.addScreen(worldSelectPresenter);

        this.view.setContent(subScreenManager.getRootNode());
    }

    private void logOut() 
    {
        mainScreenManager.reset();
    }

    private void promptReset() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reset progress");
        alert.setHeaderText("Are you sure you want to reset your progress?");
        alert.setContentText("This action cannot be undone and will reset your progress in the entire game.");

        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(FillApplication.class.getResourceAsStream("images/fill-icon.png")));

        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                loggedInUser.resetProgress();
                loggedInUser.save();
            }
        });
    }

    private void updateViewToAddWorld() 
    {
        if (!this.subScreenManager.screenExists("addworld")) {
            AddWorldView addWorldView = new AddWorldView();
            AddWorldPresenter addWorldPresenter = new AddWorldPresenter(addWorldView, this);
            this.subScreenManager.addScreen(addWorldPresenter);
        } else {
            this.subScreenManager.switchScreen("addworld");
            AddWorldPresenter addWorldPresenter = (AddWorldPresenter) this.subScreenManager.getCurrentScreen();
            addWorldPresenter.reset();
        }
    }


    // GETTERS

    public User getLoggedInUser() 
    {
        return loggedInUser;
    }

    public ScreenManager getSubScreenManager() 
    {
        return subScreenManager;
    }

    public ScreenManager getMainScreenManager() 
    {
        return mainScreenManager;
    }

    public WorldLoader getWorldLoader() 
    {
        return worldLoader;
    }

    @Override
    public GameMenuView getView() 
    {
        return view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }
}

package be.kdg.fill.views.game;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import be.kdg.fill.models.core.Game;
import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class GamePresenter implements Presenter {
    private GameView view;
    private ScreenManager mainScreenManager;
    private Game game;
    private User loggedInUser;
    private Timer timer;

    public static final String SCREEN_NAME = "game";

    public GamePresenter(GameView gameView, ScreenManager mainScreenManager, User loggedInUser) 
    {
        this.view = gameView;
        this.mainScreenManager = mainScreenManager;
        this.loggedInUser = loggedInUser;
        this.addEventHandlers();
    }

    private void addEventHandlers() 
    {
        this.view.getBackButton().setOnAction(e -> {
            this.mainScreenManager.switchScreen("gamemenu");
            if (this.timer != null) this.timer.cancel();
        });
    }

    public void startGame(Level level) 
    {
        this.game = new Game(level);
        this.view.setWorldTitle(level.getWorld().getName());
        this.view.setLevelCount(level.getId() + "/" + level.getWorld().getLevelCount());
        this.startTimer();
    }

    private void startTimer() 
    {
        this.timer = new Timer();
        this.view.setTimer("00:00");

        this.view.getScene().getWindow().setOnCloseRequest(e -> {
            this.timer.cancel();
        });

        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String elapsedTime = game.getElapsedTime();
                    view.setTimer(elapsedTime);

                    if (elapsedTime.equals("05:00")) {
                        askForBreak();
                    }
                });
            }
        }, 0, 1000);
    }

    private void askForBreak() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Time for a break?");
        alert.setHeaderText("You have been playing for 5 minutes.");
        alert.setContentText("Maybe it's time for a break... Would you like to take a break?");
        alert.showAndWait();

        // create buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            this.timer.cancel();
            this.mainScreenManager.switchScreen("gamemenu");
        }
    }


    // GETTERS

    @Override
    public GameView getView() 
    {
        return this.view;
    }

    @Override
    public String getScreenName() 
    {
        return SCREEN_NAME;
    }

}
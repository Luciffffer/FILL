package be.kdg.fill.views.game;

import be.kdg.fill.models.core.Game;
import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.User;
import be.kdg.fill.views.Presenter;

public class GamePresenter implements Presenter {
    private GameView view;
    private Game game;
    private User loggedInUser;

    public static final String SCREEN_NAME = "game";

    public GamePresenter(GameView gameView, User loggedInUser) {
        this.view = gameView;
        this.loggedInUser = loggedInUser;
    }

    public void startGame(Level level) {
        this.game = new Game(level);
    }

    @Override
    public GameView getView() {
        return this.view;
    }

    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }

}

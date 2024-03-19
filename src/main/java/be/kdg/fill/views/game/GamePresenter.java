package be.kdg.fill.views.game;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import be.kdg.fill.models.core.Game;
import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.StarRating;
import be.kdg.fill.models.core.User;
import be.kdg.fill.models.core.World;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/*
 * This class is responsible for translating the game logic to what the actual user sees.
 * It is responsible for handling the game logic and updating the view accordingly.
 * Also handles changes in screen size.
 */
public class GamePresenter implements Presenter {
    private GameView view;
    private ScreenManager mainScreenManager;
    private Game game;
    private User loggedInUser;
    private Timer timer;

    private double blockSize;
    private int borderWidth;

    public static final String SCREEN_NAME = "game";

    private EventHandler<MouseEvent> handleBoardMouseEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            int row = (int) (e.getY() / blockSize);
            int col = (int) (e.getX() / blockSize);

            game.updateLine(row, col);

            // checks the difference in amount of line elements between the game and the view
            int linesAdded = game.getLine().size() - view.getLineAnchor().getChildren().size() - 1;
            
            if (linesAdded < 0) { 
                // remove amount of line elements from the board
                view.getLineAnchor().getChildren().remove(view.getLineAnchor().getChildren().size() + linesAdded, view.getLineAnchor().getChildren().size());
            } else if (linesAdded == 1) {
                // add a line element to the board
                int[] topOfLine = game.getTopOfLine();
                Group lineBlock = view.addLineBlock(topOfLine[0], topOfLine[1], topOfLine[2]);
                updateLineBlockSizeAndPosition(lineBlock);

                if (game.isFinished()) {
                    view.getBoardStack().removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
                    view.getBoardStack().setOnMouseDragged(null);
                    timer.cancel();
                    loggedInUser.setWorldProgress(game.getLevel().getWorld().getId(), game.getLevel().getId());
                    loggedInUser.save();
                    StarRating starRating = game.getStarRating();
                    boolean hasNextLevel = game.getLevel().getId() != game.getLevel().getWorld().getLevelCount();

                    // this is a small delay purely for the user to see the last line block being placed
                    timer = new Timer(true);          

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                view.showGameFinished(starRating.getValue(), starRating.getExplanation(), hasNextLevel);
                            });
                        }
                    }, 300);
                }
            }
        }
    };

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
            if (this.timer != null) this.timer.cancel();
            this.mainScreenManager.switchScreen("gamemenu");
        });

        this.view.getHelpButton().setOnAction(e -> {
            this.view.showHowToPlay();
        });

        this.view.getResetButton().setOnAction(e -> {
            if (this.timer != null) this.timer.cancel();
            this.startGame(this.game.getLevel());
        });

        this.view.getCloseOverlayButton().setOnAction(e -> {
            this.view.getGameOverlay().setVisible(false);
        });

        Platform.runLater(() -> {
            this.view.getScene().heightProperty().addListener((obs, oldVal, newVal) -> {
                resizeBlocks();
            });
        });
    }

    /**
     * startGame
     * starts the game with a given level.
     * Also works as a reset function.
     * Clears the board and so forth.
     * @param level
     */
    public void startGame(Level level) 
    {
        this.view.getGameOverlay().setVisible(false);
        this.game = new Game(level);
        this.view.setWorldTitle(level.getWorld().getName());
        this.view.setLevelCount(level.getId() + "/" + level.getWorld().getLevelCount());
        this.startTimer();
        Platform.runLater(() -> {
            this.initializeBoard();
            this.resizeBlocks();
            this.view.getLineAnchor().getChildren().clear();
        });

        this.view.getBackToMenuButton().setOnAction(e -> {
            this.mainScreenManager.switchScreen("gamemenu");
        });

        this.view.getNextLevelButton().setOnAction(e -> {
            int nextLevelId = this.game.getLevel().getId() + 1;
            World world = this.game.getLevel().getWorld();
            
            this.startGame(world.getLevels().get(nextLevelId - 1));
        });

        this.view.getBoardStack().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            this.view.getBoardStack().setOnMouseDragged(this.handleBoardMouseEvent);
            this.handleBoardMouseEvent.handle(e);
        });

        this.view.getBoardStack().addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.view.getBoardStack().setOnMouseDragged(null);
        });
    }

    /**
     * startTimer
     * starts the timer for the game.
     */
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

    /**
     * resizeBlocks
     * resizes the blocks on the board to fit the screen.
     * It does this for the background blocks as well as the line blocks.
     */
    private void resizeBlocks() 
    {
        final double height = this.view.getGameCenter().getHeight() / this.game.getPattern().length;
        final double screenHeigth = (int) this.view.getScene().getHeight();
        this.blockSize = height > screenHeigth / 5 ? screenHeigth / 5 : height;
        this.borderWidth = (int) Math.ceil(this.blockSize / 20);

        // update size of background blocks
        this.view.getBoardGrid().getChildren().forEach(node -> {
            if (node instanceof Label) {
                Label block = (Label) node;
                block.setPrefSize(this.blockSize, this.blockSize);
                block.setStyle("-fx-border-width: " + this.borderWidth + "px;");
            }
        });

        // set size of board stack
        this.view.getBoardStack().setMaxHeight(this.blockSize * this.game.getPattern().length);
        this.view.getBoardStack().setMaxWidth(this.blockSize * this.game.getPattern()[0].length);

        // update line blocks
        this.view.getLineAnchor().getChildren().forEach(node -> {
            if (node instanceof Group) {
                this.updateLineBlockSizeAndPosition((Group) node);
            }
        });
    }

    /**
     * updateLineBlockSizeAndPosition
     * updates the size and position of a line block.
     * @param lineBlock
     */
    private void updateLineBlockSizeAndPosition(Group lineBlock)
    {
        double[] blockData = (double[]) lineBlock.getUserData();
        final int row = (int) blockData[0];
        final int col = (int) blockData[1];
        final int direction = (int) blockData[2];
        final double imageWidthRatio = blockData[3];

        // update size of label (easy way to update the size because javafx still thinks the label is in the same position as before rotation)
        double lineHeight = this.blockSize - this.borderWidth * 2;
        lineBlock.getChildren().forEach(block -> {
            if (block instanceof Label) {
                Label label = (Label) block;
                label.setPrefSize(lineHeight * imageWidthRatio, lineHeight);
            }
        });

        // update position of label
        double x = col * this.blockSize + this.borderWidth;
        double y = row * this.blockSize + this.borderWidth;

        // this might be really messy but javafx layouts are the worst 😭😭
        AnchorPane.setTopAnchor(lineBlock, direction == 3 ? y - lineHeight / 2 : y);
        AnchorPane.setLeftAnchor(lineBlock, direction == 2 ? x - lineHeight / 2 : x);
    }

    /**
     * initializeBoard
     * initializes the board with the pattern of the game.
     */
    private void initializeBoard()
    {
        this.view.getBoardGrid().getChildren().clear();
        int[][] pattern = this.game.getPattern();

        for (int i = 0; i < pattern.length; i++) 
        {
            for (int j = 0; j < pattern[i].length; j++) 
            {
                switch (pattern[i][j]) 
                {
                    case 0:
                        continue;
                    case 1:
                        this.view.addBlock(i, j);
                        break;
                    case 2:
                        this.view.addStartBlock(i, j);
                        break;
                }
            }
        }
    }

    /**
     * askForBreak
     * creates an alert to ask the user if they want to take a break.
     */
    private void askForBreak() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Time for a break?");
        alert.setHeaderText("You have been playing for 5 minutes.");
        alert.setContentText("Maybe it's time for a break... Would you like to take a break?");

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

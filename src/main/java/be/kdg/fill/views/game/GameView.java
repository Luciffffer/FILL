package be.kdg.fill.views.game;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.HoverClickable;
import be.kdg.fill.views.compontents.PrimaryButton;
import be.kdg.fill.views.compontents.SecondaryButton;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class GameView extends StackPane {
    
    private ImageView background;
    private HoverClickable backButton;
    private Label titel;
    private ImageView logo;
    private Label worldTitle;
    private Label levelCount;
    private Label timer;
    private HoverClickable helpButton;
    private HoverClickable resetButton;
    private StackPane gameCenter;
    private StackPane boardStack;
    private AnchorPane lineAnchor;
    private GridPane boardGrid;

    private StackPane gameOverlay;
    private VBox gameOverlayBox;

    private HoverClickable closeOverlayButton;
    private VBox howToPlayContent;

    private VBox gameFinishedContent;
    private ImageView gameFinishedIcon;
    private Label gameFinishedParagraph;
    private Rectangle foregroundStarsClip;
    private ImageView foregroundStars;
    private Label starRatingLabel;
    private SecondaryButton backToMenuButton;
    private PrimaryButton nextLevelButton;

    public static final int MENU_WIDTH = 350;

    public GameView() 
    {
        this.initialiseNodes();
        this.layoutNodes();
        this.layoutHowToPlayContent();
        this.layoutGameFinishedContent();
    }

    private void initialiseNodes() 
    {
        this.background = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.backButton = new HoverClickable(100, 1.05);
        this.titel = new Label("Solve the puzzle!");
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.worldTitle = new Label("");
        this.levelCount = new Label("");
        this.timer = new Label("");
        this.helpButton = new HoverClickable(100, 1.05);
        this.resetButton = new HoverClickable(100, 1.05);
        this.boardGrid = new GridPane();
        this.boardStack = new StackPane();
        this.lineAnchor = new AnchorPane();
        this.gameCenter = new StackPane();

        this.gameOverlay = new StackPane();
        this.gameOverlayBox = new VBox();

        this.howToPlayContent = new VBox();
        this.closeOverlayButton = new HoverClickable(100, 1.05);

        this.gameFinishedContent = new VBox();
        this.gameFinishedIcon = new ImageView(new Image(FillApplication.class.getResource("images/checkmark-icon-green.png").toExternalForm()));
        this.gameFinishedParagraph = new Label();
        this.foregroundStarsClip = new Rectangle();
        this.foregroundStars = new ImageView(new Image(FillApplication.class.getResource("images/stars-yellow.png").toExternalForm()));
        this.starRatingLabel = new Label("");
        this.backToMenuButton = new SecondaryButton("Back to menu");
        this.nextLevelButton = new PrimaryButton("Next level");
    }

    private void layoutNodes() 
    {
        this.setStyle("-fx-background-color: #FAF9FB;");

        // Layer 1: Background
        AnchorPane anchorPane = new AnchorPane();

        this.background.setFitWidth(250);
        this.background.setFitHeight(250);
        this.background.setPreserveRatio(true);
        this.background.setRotate(180.0);

        AnchorPane.setBottomAnchor(this.background, 0.0);
        AnchorPane.setRightAnchor(this.background, 0.0);
        anchorPane.getChildren().add(this.background);

        this.getChildren().add(anchorPane);


        // Layer 2: Game
        BorderPane borderPane = new BorderPane();
        this.getChildren().add(borderPane);

        // top menu
        StackPane topMenu = new StackPane();
        topMenu.setPadding(new Insets(12));
        topMenu.getChildren().addAll(this.backButton, this.titel, this.logo);

        StackPane.setAlignment(this.backButton, javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setAlignment(this.logo, javafx.geometry.Pos.CENTER_RIGHT);

        ImageView backArrow = new ImageView(new Image(FillApplication.class.getResource("images/back-arrow.png").toExternalForm()));
        backArrow.setFitWidth(24);
        backArrow.setPreserveRatio(true);
        this.backButton.setGraphic(backArrow);
        this.backButton.getStyleClass().add("button-reset");

        this.titel.getStyleClass().add("h2");

        this.logo.setPreserveRatio(true);
        this.logo.setFitHeight(35);
        this.backButton.prefWidthProperty().bind(this.logo.fitWidthProperty());

        borderPane.setTop(topMenu);


        // game
        BorderPane gameBorderPane = new BorderPane();
        borderPane.setCenter(gameBorderPane);
        gameBorderPane.setPadding(new Insets(24));

        // game top menu
        StackPane gameTopMenu = new StackPane();
        gameTopMenu.setMaxWidth(MENU_WIDTH);
        gameTopMenu.setPadding(new Insets(12));
        BorderPane.setAlignment(gameTopMenu, javafx.geometry.Pos.CENTER);

        this.worldTitle.getStyleClass().add("body");
        this.levelCount.getStyleClass().add("h3");
        this.timer.getStyleClass().add("body");

        gameTopMenu.getChildren().addAll(this.worldTitle, this.levelCount, this.timer);

        StackPane.setAlignment(this.worldTitle, javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setAlignment(this.timer, javafx.geometry.Pos.CENTER_RIGHT);

        gameBorderPane.setTop(gameTopMenu);

        // game center
        BorderPane.setMargin(this.gameCenter, new Insets(12));

        this.boardStack.getChildren().addAll(this.boardGrid, this.lineAnchor);
        this.gameCenter.getChildren().add(this.boardStack);

        gameBorderPane.setCenter(this.gameCenter);

        // game bottom menu
        HBox gameBottomMenu = new HBox();
        gameBottomMenu.setSpacing(24);
        gameBottomMenu.setMaxWidth(USE_PREF_SIZE);
        gameBottomMenu.setPadding(new Insets(12));
        BorderPane.setAlignment(gameBottomMenu, javafx.geometry.Pos.CENTER);


        // help button
        HBox helpButtonBox = new HBox();
        helpButtonBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        helpButtonBox.setSpacing(12);

        ImageView helpIcon = new ImageView(new Image(FillApplication.class.getResource("images/help-icon.png").toExternalForm()));
        helpIcon.setFitWidth(24);
        helpIcon.setPreserveRatio(true);

        Label helpLabel = new Label("How to play");
        helpLabel.getStyleClass().add("body");

        helpButtonBox.getChildren().addAll(helpIcon, helpLabel);

        this.helpButton.setGraphic(helpButtonBox);
        this.helpButton.getStyleClass().add("button-reset");

        // reset button

        HBox resetButtonBox = new HBox();
        resetButtonBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        resetButtonBox.setSpacing(12);

        ImageView resetIcon = new ImageView(new Image(FillApplication.class.getResource("images/reset-icon.png").toExternalForm()));
        resetIcon.setFitWidth(24);
        resetIcon.setPreserveRatio(true);

        Label resetLabel = new Label("Reset");
        resetLabel.getStyleClass().add("body");

        resetButtonBox.getChildren().addAll(resetIcon, resetLabel);

        this.resetButton.setGraphic(resetButtonBox);
        this.resetButton.getStyleClass().add("button-reset");

        gameBottomMenu.getChildren().addAll(this.helpButton, this.resetButton);

        gameBorderPane.setBottom(gameBottomMenu);

        // game overlay
        this.gameOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.gameOverlay.setPadding(new Insets(48));
        this.gameOverlay.setVisible(false);
        this.getChildren().add(this.gameOverlay);

        this.gameOverlayBox.setAlignment(javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(this.gameOverlayBox, javafx.geometry.Pos.CENTER);
        this.gameOverlayBox.setSpacing(24);
        this.gameOverlayBox.setPadding(new Insets(24));
        this.gameOverlayBox.setStyle("-fx-background-color: #FAF9FB; -fx-background-radius: 12px;");
        this.gameOverlayBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.gameOverlayBox.setMaxWidth(350);
        this.gameOverlay.getChildren().add(this.gameOverlayBox);
    }

    private void layoutHowToPlayContent() 
    {
        this.howToPlayContent.setSpacing(24);

        // Top menu
        StackPane overlayTopmenu = new StackPane();

        this.closeOverlayButton.getStyleClass().add("button-reset");
        this.closeOverlayButton.setPadding(new Insets(0));
        ImageView backArrowIcon = new ImageView(new Image(FillApplication.class.getResource("images/cross-icon.png").toExternalForm()));
        backArrowIcon.setFitWidth(24);
        backArrowIcon.setPreserveRatio(true);
        this.closeOverlayButton.setGraphic(backArrowIcon);

        StackPane.setAlignment(this.closeOverlayButton, javafx.geometry.Pos.CENTER_RIGHT);
        overlayTopmenu.getChildren().add(this.closeOverlayButton);

        Label overlayTitle = new Label("How to play");
        overlayTitle.getStyleClass().add("h2");

        StackPane.setAlignment(overlayTitle, javafx.geometry.Pos.CENTER);
        overlayTopmenu.getChildren().add(overlayTitle);

        this.howToPlayContent.getChildren().add(overlayTopmenu);

        // Content
        Label howToPlayParagraph1 = new Label("Fill is a classic puzzle game where you're given a grid with some blocks and a starting point. The goal is to fill the grid with a line that starts at the starting point. Fill the entire grid to win the game!");
        howToPlayParagraph1.getStyleClass().add("body");
        howToPlayParagraph1.setWrapText(true);

        this.howToPlayContent.getChildren().add(howToPlayParagraph1);
    }

    private void layoutGameFinishedContent() 
    {
        this.gameFinishedContent.setPadding(new Insets(12, 0, 12, 0));
        this.gameFinishedContent.setSpacing(24);
        this.gameFinishedContent.setAlignment(javafx.geometry.Pos.CENTER);

        // Icon
        this.gameFinishedIcon.setFitWidth(75);
        this.gameFinishedIcon.setPreserveRatio(true);

        // Text
        VBox gameFinishedText = new VBox();
        gameFinishedText.setAlignment(javafx.geometry.Pos.CENTER);
        gameFinishedText.setSpacing(12);

        Label gameFinishedTitle = new Label("Congratulations!");
        gameFinishedTitle.getStyleClass().add("h2");

        gameFinishedParagraph.getStyleClass().add("body");

        gameFinishedText.getChildren().addAll(gameFinishedTitle, gameFinishedParagraph);

        this.gameFinishedContent.getChildren().addAll(gameFinishedIcon, gameFinishedText);

        // Star rating
        VBox starRating = new VBox();
        starRating.setAlignment(javafx.geometry.Pos.CENTER);
        starRating.setSpacing(12);

        StackPane starBox = new StackPane();
        starBox.setAlignment(javafx.geometry.Pos.CENTER);

        ImageView backgroundStars = new ImageView(new Image(FillApplication.class.getResource("images/stars-grey.png").toExternalForm()));
        backgroundStars.setPreserveRatio(true);
        backgroundStars.setFitWidth(200);

        this.foregroundStars.setPreserveRatio(true);
        this.foregroundStars.setFitWidth(200);

        this.foregroundStarsClip.setScaleX(0);
        this.foregroundStarsClip.setWidth(200);
        this.foregroundStarsClip.setHeight(40);
        this.foregroundStars.setClip(this.foregroundStarsClip);

        starBox.getChildren().addAll(backgroundStars, this.foregroundStars);
        
        this.starRatingLabel.getStyleClass().add("small");

        starRating.getChildren().addAll(starBox, this.starRatingLabel);
        this.gameFinishedContent.getChildren().add(starRating);

        // Buttons
        VBox buttons = new VBox();
        buttons.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setSpacing(6);

        VBox.setMargin(this.backToMenuButton, new Insets(12, 0, 0, 0));

        this.backToMenuButton.setPrefWidth(225);
        this.nextLevelButton.setPrefWidth(225);

        buttons.getChildren().addAll(this.backToMenuButton, this.nextLevelButton);
        this.gameFinishedContent.getChildren().add(buttons);
    }

    // GETTERS

    public HoverClickable getBackButton() 
    {
        return this.backButton;
    }

    public HoverClickable getHelpButton() 
    {
        return this.helpButton;
    }

    public HoverClickable getResetButton() 
    {
        return this.resetButton;
    }

    public StackPane getBoardStack() 
    {
        return this.boardStack;
    }

    public GridPane getBoardGrid() 
    {
        return this.boardGrid;
    }

    public StackPane getGameCenter() 
    {
        return this.gameCenter;
    }

    public StackPane getGameOverlay() 
    {
        return this.gameOverlay;
    }

    public HoverClickable getCloseOverlayButton() 
    {
        return this.closeOverlayButton;
    }

    public AnchorPane getLineAnchor() 
    {
        return this.lineAnchor;
    }

    public PrimaryButton getNextLevelButton() 
    {
        return this.nextLevelButton;
    }

    public SecondaryButton getBackToMenuButton() 
    {
        return this.backToMenuButton;
    }


    // SETTERS

    public void setWorldTitle(String worldTitle) 
    {
        this.worldTitle.setText(worldTitle);
    }

    public void setLevelCount(String levelCount) 
    {
        this.levelCount.setText(levelCount);
    }

    public void setTimer(String timer) 
    {
        this.timer.setText(timer);
    }

    public void addBlock(int i, int j) 
    {
        Label block = new Label();
        block.getStyleClass().add("block");
        this.boardGrid.add(block, j, i);
    }

    public void addStartBlock(int i, int j) 
    {
        Label block = new Label();
        block.getStyleClass().add("block-border");
        
        Image startIcon = new Image(FillApplication.class.getResource("images/start-icon.png").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(startIcon, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        block.setBackground(new Background(backgroundImage));
        
        this.boardGrid.add(block, j, i);
    }

    public Group addLineBlock(int row, int col, int direction) 
    {
        final double tempHeight = 50;
        final double imageWidthRatio = 1.5;

        Label block = new Label();
        block.setPrefHeight(tempHeight);
        block.setPrefWidth(tempHeight * imageWidthRatio);

        // Rotates the block, but javafx still thinks the block is in the original position
        Rotate rotate = new Rotate();
        rotate.setPivotX(tempHeight / 2);
        rotate.setPivotY(tempHeight / 2);
        rotate.setAngle(90 * direction);
        block.getTransforms().add(rotate);

        Image lineIcon = new Image(FillApplication.class.getResource("images/line-icon.png").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(lineIcon, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        block.setBackground(new Background(backgroundImage));
        
        // We add the block to a group wrapper so javafx updates the position correctly
        Group wrapper = new Group(block);

        // set data
        wrapper.setUserData(new double[] { row, col, direction, imageWidthRatio });

        this.lineAnchor.getChildren().add(wrapper);
        return wrapper;
    }


    // METHODS

    public void showHowToPlay() 
    {
        this.gameOverlayBox.getChildren().clear();
        this.gameOverlayBox.getChildren().add(this.howToPlayContent);
        this.gameOverlay.setVisible(true);
    }

    public void showGameFinished(int stars, String starRatingText, boolean hasNextLevel) 
    {
        this.gameOverlayBox.getChildren().clear();
        this.gameOverlayBox.getChildren().add(this.gameFinishedContent);
        this.gameOverlay.setVisible(true);

        // next level button
        if (!hasNextLevel) {
            this.gameFinishedParagraph.setText("You've completed the entire world");
            this.nextLevelButton.setVisible(false);
            this.nextLevelButton.setManaged(false);
        } else {
            this.gameFinishedParagraph.setText("You've completed the puzzle");
            this.nextLevelButton.setVisible(true);
            this.nextLevelButton.setManaged(true);
        }

        // icon animation
        ScaleTransition scaleTransition = new ScaleTransition(javafx.util.Duration.millis(150), this.gameFinishedIcon);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        scaleTransition.setOnFinished(e -> {
            ScaleTransition scaleTransition2 = new ScaleTransition(javafx.util.Duration.millis(150), this.gameFinishedIcon);
            scaleTransition2.setToX(1);
            scaleTransition2.setToY(1);
            scaleTransition2.play();
        });

        scaleTransition.play();

        // star rating animation
        ScaleTransition scaleTransition3 = new ScaleTransition(javafx.util.Duration.millis(500), this.foregroundStarsClip);
        scaleTransition3.setFromX(0);
        scaleTransition3.setToX(stars / 5.0);
        
        TranslateTransition translateTransition = new TranslateTransition(javafx.util.Duration.millis(500), this.foregroundStarsClip);
        translateTransition.setFromX(-100);
        translateTransition.setToX(100 * stars / 5.0 - 100);

        ParallelTransition parallelTransition = new ParallelTransition(scaleTransition3, translateTransition);
        parallelTransition.play();

        // set star rating text
        this.starRatingLabel.setText(starRatingText);

    }

}

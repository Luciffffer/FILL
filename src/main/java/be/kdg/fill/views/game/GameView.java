package be.kdg.fill.views.game;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.HoverClickable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private GridPane boardGrid;

    private StackPane gameOverlay;
    private VBox gameOverlayBox;
    private StackPane gameOverlayContent;
    private HoverClickable closeOverlayButton;
    private Label overlayTitle;
    private VBox howToPlayContent;

    public static final int MENU_WIDTH = 350;

    public GameView() 
    {
        this.initialiseNodes();
        this.layoutNodes();   
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
        this.gameCenter = new StackPane();

        this.gameOverlay = new StackPane();
        this.gameOverlayBox = new VBox();
        this.howToPlayContent = new VBox();
        this.closeOverlayButton = new HoverClickable(100, 1.05);
        this.overlayTitle = new Label("");
        this.gameOverlayContent = new StackPane();
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

        this.boardStack.getChildren().add(this.boardGrid);
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

        StackPane overlayTopmenu = new StackPane();

        this.closeOverlayButton.getStyleClass().add("button-reset");
        this.closeOverlayButton.setPadding(new Insets(0));
        ImageView backArrowIcon = new ImageView(new Image(FillApplication.class.getResource("images/back-arrow.png").toExternalForm()));
        backArrowIcon.setFitWidth(24);
        backArrowIcon.setPreserveRatio(true);
        this.closeOverlayButton.setGraphic(backArrowIcon);

        StackPane.setAlignment(this.closeOverlayButton, javafx.geometry.Pos.CENTER_LEFT);
        overlayTopmenu.getChildren().add(this.closeOverlayButton);

        this.overlayTitle.getStyleClass().add("h2");

        StackPane.setAlignment(this.overlayTitle, javafx.geometry.Pos.CENTER);
        overlayTopmenu.getChildren().add(this.overlayTitle);

        this.gameOverlayBox.getChildren().addAll(overlayTopmenu, gameOverlayContent);

        // how to play content
        this.howToPlayContent.setSpacing(12);

        Label howToPlayParagraph1 = new Label("Fill is a classic puzzle game where you're given a grid with some blocks and a starting point. The goal is to fill the grid with a line that starts at the starting point. Fill the entire grid to win the game!");
        howToPlayParagraph1.getStyleClass().add("body");
        howToPlayParagraph1.setWrapText(true);

        this.howToPlayContent.getChildren().add(howToPlayParagraph1);
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


    // METHODS

    public void showHowToPlay() 
    {
        this.overlayTitle.setText("How to play");
        this.gameOverlayContent.getChildren().clear();
        this.gameOverlayContent.getChildren().add(this.howToPlayContent);
        this.gameOverlay.setVisible(true);
    }

}

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
    private GridPane boardGrid;

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
        this.gameCenter = new StackPane();
    }

    private void layoutNodes() 
    {
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
        this.gameCenter.setStyle("-fx-background-color: pink;");

        this.gameCenter.getChildren().add(this.boardGrid);
        this.boardGrid.setStyle("-fx-background-color: blue;");

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

    public GridPane getBoardGrid() 
    {
        return this.boardGrid;
    }

    public StackPane getGameCenter() 
    {
        return this.gameCenter;
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

}

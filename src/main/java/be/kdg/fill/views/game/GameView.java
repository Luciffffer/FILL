package be.kdg.fill.views.game;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.HoverClickable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameView extends StackPane {
    
    private ImageView background;
    private HoverClickable backButton;
    private Label titel;
    private ImageView logo;
    private Label worldTitle;
    private Label levelCount;
    private Label timer;

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
        BorderPane topMenu = new BorderPane();
        topMenu.setPadding(new Insets(12));
        topMenu.setLeft(this.backButton);
        topMenu.setCenter(this.titel);
        topMenu.setRight(this.logo);

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
        BorderPane gameTopMenu = new BorderPane();
        gameTopMenu.setMaxWidth(350);
        gameTopMenu.setPadding(new Insets(12));
        BorderPane.setAlignment(gameTopMenu, javafx.geometry.Pos.CENTER);

        this.worldTitle.getStyleClass().add("body");
        this.levelCount.getStyleClass().add("h3");
        this.timer.getStyleClass().add("body");

        gameTopMenu.setLeft(this.worldTitle);
        gameTopMenu.setCenter(this.levelCount);
        gameTopMenu.setRight(this.timer);

        this.timer.minWidthProperty().bind(this.worldTitle.widthProperty());
        this.worldTitle.minWidthProperty().bind(this.levelCount.widthProperty());

        gameBorderPane.setTop(gameTopMenu);
    }


    // GETTERS

    public HoverClickable getBackButton() 
    {
        return this.backButton;
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

}

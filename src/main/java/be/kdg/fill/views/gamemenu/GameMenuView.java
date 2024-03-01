package be.kdg.fill.views.gamemenu;

import be.kdg.fill.FillApplication;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameMenuView extends BorderPane {
    
    private ImageView background;
    private ImageView logo;
    private Button resetButton;
    private Button logOutButton;
    private StackPane contentPane;
    private Pane content;
    private AnchorPane backgroundPane;

    public static final int TOP_MENU_HEIGHT = 56;

    public GameMenuView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.background = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.resetButton = new Button("Reset Game");
        this.logOutButton = new Button("Log Out");
        this.contentPane = new StackPane();
        this.backgroundPane = new AnchorPane();
    }

    private void layoutNodes() 
    {
        // Menu
        BorderPane menuPane = new BorderPane();
        menuPane.setPrefHeight(TOP_MENU_HEIGHT);
        menuPane.getStyleClass().add("game-menu");

        javafx.geometry.Insets padding = new javafx.geometry.Insets(12, 12, 12, 12);
        menuPane.paddingProperty().setValue(padding);

        // Logo
        this.logo.setPreserveRatio(true);
        this.logo.setFitHeight(TOP_MENU_HEIGHT - padding.getTop() - padding.getBottom());

        BorderPane.setAlignment(this.logo, javafx.geometry.Pos.CENTER);
        menuPane.setLeft(this.logo);

        // Menu buttons
        HBox menuButtons = new HBox();

        this.resetButton.getStyleClass().add("button-reset");
        this.logOutButton.getStyleClass().add("button-reset");
        this.resetButton.getStyleClass().add("text-button");
        this.logOutButton.getStyleClass().add("text-button");

        menuButtons.setAlignment(javafx.geometry.Pos.CENTER);
        menuButtons.getChildren().addAll(this.resetButton, this.logOutButton);
        menuButtons.setSpacing(12);

        BorderPane.setAlignment(menuButtons, javafx.geometry.Pos.CENTER);
        menuPane.setRight(menuButtons);

        this.setTop(menuPane);

        // scrollpane

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        // Layer 1: Background
        this.background.setFitWidth(250);
        this.background.setFitHeight(250);
        this.background.setPreserveRatio(true);
        this.background.setRotate(180.0);

        AnchorPane.setRightAnchor(this.background, 0.0);
        AnchorPane.setBottomAnchor(this.background, 0.0);
        this.backgroundPane.getChildren().add(this.background);

        this.contentPane.getChildren().add(backgroundPane);
        scrollPane.setContent(this.contentPane);

        this.setCenter(scrollPane);
    }

    public void setContent(Pane content) 
    {
        this.content = content;
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().addAll(this.backgroundPane, this.content);
    }


    // GETTERS

    public Button getResetButton() 
    {
        return resetButton;
    }

    public Button getLogOutButton() 
    {
        return logOutButton;
    }
}

package be.kdg.fill.views.gamemenu;

import be.kdg.fill.FillApplication;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GameMenuView extends StackPane {
    
    private ImageView background;
    private ImageView logo;
    private Button resetButton;
    private Button logOutButton;
    private BorderPane menuPane;
    private Region content;

    public static final int TOP_MENU_HEIGHT = 56;

    public GameMenuView(Region content) 
    {
        this.initializeNodes();
        this.layoutNodes();
        this.setContent(content);
    }

    private void initializeNodes() 
    {
        this.background = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.resetButton = new Button("Reset Game");
        this.logOutButton = new Button("Log Out");
        this.menuPane = new BorderPane();
    }

    private void layoutNodes() 
    {
        // Layer 1: Background
        AnchorPane backgroundPane = new AnchorPane();

        this.background.setFitWidth(250);
        this.background.setFitHeight(250);
        this.background.setPreserveRatio(true);
        this.background.setRotate(180.0);

        AnchorPane.setRightAnchor(this.background, 0.0);
        AnchorPane.setBottomAnchor(this.background, 0.0);
        backgroundPane.getChildren().add(this.background);
        
        // Layer 2: Menu and content
        BorderPane contentPane = new BorderPane();

        // Menu
        menuPane.setPrefHeight(TOP_MENU_HEIGHT);

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

        contentPane.setTop(menuPane);

        this.getChildren().addAll(backgroundPane, contentPane);
    }

    public void setContent(Region content) 
    {
        this.content = content;
        this.menuPane.setCenter(content);
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

package be.kdg.fill.views.gamemenu.levelselect;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.LevelCard;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LevelSelectView extends VBox {
    Text title;
    Button backButton;
    Text levelCount;
    TilePane levelCards;

    public LevelSelectView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.title = new Text("loading...");
        this.backButton = new Button();
        this.levelCount = new Text();
        this.levelCards = new TilePane();
    }

    private void layoutNodes() 
    {
        this.paddingProperty().setValue(new javafx.geometry.Insets(36, 24, 48, 24));
        this.setSpacing(48);
        this.setFillWidth(true);

        // top menu
        BorderPane topMenu = new BorderPane();
        topMenu.setPrefHeight(56);

        // button
        ImageView backButtonImage = new ImageView(FillApplication.class.getResource("images/back-arrow.png").toExternalForm());
        backButtonImage.setFitHeight(24);
        backButtonImage.setPreserveRatio(true);

        this.backButton.getStyleClass().add("button-reset");
        this.backButton.paddingProperty().setValue(new javafx.geometry.Insets(0));
        this.backButton.setGraphic(backButtonImage);

        BorderPane.setAlignment(this.backButton, javafx.geometry.Pos.CENTER);
        topMenu.setLeft(this.backButton);

        // title
        this.title.getStyleClass().add("h1");
        
        BorderPane.setAlignment(this.title, javafx.geometry.Pos.CENTER);
        topMenu.setCenter(this.title);

        // level count
        this.levelCount.getStyleClass().add("body");
        this.levelCount.minWidth(24.0);
        BorderPane.setAlignment(this.levelCount, javafx.geometry.Pos.CENTER);
        topMenu.setRight(this.levelCount);

        // level cards
        this.levelCards.setHgap(24);
        this.levelCards.setVgap(24);

        this.getChildren().addAll(topMenu, this.levelCards);
    }


    // GETTERS

    public Text getTitle() 
    {
        return this.title;
    }

    public Text getLevelCount() 
    {
        return this.levelCount;
    }

    public Button getBackButton() 
    {
        return this.backButton;
    }

    public TilePane getLevelCards() 
    {
        return this.levelCards;
    }


    // SETTERS

    public void addLevelCard(LevelCard levelCard) 
    {
        this.levelCards.getChildren().add(levelCard);
    }

}

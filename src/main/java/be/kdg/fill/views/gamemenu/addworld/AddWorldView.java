package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.FillTextField;
import be.kdg.fill.views.compontents.HoverClickable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddWorldView extends VBox {
    private Label title;
    private HoverClickable backButton;
    private Label stepOne;
    private FillTextField worldName;
    private FillTextField difficultyName;
    private Label stepTwo;

    public AddWorldView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.title = new Label("Add a new world");
        this.backButton = new HoverClickable(100, 1.05);
        this.stepOne = new Label("Step 1: enter basic information about your new world");
        this.worldName = new FillTextField("World name", false);
        this.difficultyName = new FillTextField("Difficulty name", false);
        this.stepTwo = new Label("Step 2: add levels to your world");
    }

    private void layoutNodes() 
    {
        this.paddingProperty().setValue(new javafx.geometry.Insets(36, 12, 12, 12));
        this.setSpacing(48);
        this.setFillWidth(true);
        this.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        // Top menu (contains back button and title)
        StackPane topMenu = new StackPane();

        ImageView backButtonImage = new ImageView(FillApplication.class.getResource("images/back-arrow.png").toExternalForm());
        backButtonImage.setFitHeight(24);
        backButtonImage.setPreserveRatio(true);

        this.backButton.getStyleClass().add("button-reset");
        this.backButton.paddingProperty().setValue(new javafx.geometry.Insets(0));
        this.backButton.setGraphic(backButtonImage);
        StackPane.setAlignment(this.backButton, javafx.geometry.Pos.CENTER_LEFT);

        this.title.getStyleClass().add("h1");
        this.title.setStyle("-fx-text-fill: #E66C6C;");
        StackPane.setAlignment(this.title, javafx.geometry.Pos.CENTER);

        topMenu.getChildren().addAll(this.backButton, this.title);
        this.getChildren().add(topMenu);

        // Step one: basic info
        VBox stepOneBox = new VBox();
        stepOneBox.setAlignment(javafx.geometry.Pos.CENTER);
        stepOneBox.setSpacing(24);

        this.stepOne.getStyleClass().add("h3");

        VBox stepOneButtonBox = new VBox();
        stepOneButtonBox.setAlignment(javafx.geometry.Pos.CENTER);
        stepOneButtonBox.setSpacing(0);

        this.worldName.setMaxWidth(400);
        this.difficultyName.setMaxWidth(400);

        stepOneButtonBox.getChildren().addAll(this.worldName, this.difficultyName);
        stepOneBox.getChildren().addAll(this.stepOne, stepOneButtonBox);
        this.getChildren().addAll(stepOneBox);

        // Step two: add levels
        VBox stepTwoBox = new VBox();
        stepTwoBox.setAlignment(javafx.geometry.Pos.CENTER);
        stepTwoBox.setSpacing(24);

        this.stepTwo.getStyleClass().add("h3");
        stepTwoBox.getChildren().addAll(this.stepTwo);

        this.getChildren().addAll(stepTwoBox);
    }


    // GETTERS

    public HoverClickable getBackButton() 
    {
        return backButton;
    }

    public FillTextField getWorldName() 
    {
        return worldName;
    }

}

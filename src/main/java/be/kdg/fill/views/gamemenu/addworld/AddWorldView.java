package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.FillTextField;
import be.kdg.fill.views.compontents.HoverClickable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddWorldView extends VBox {
    private Label title;
    private HoverClickable backButton;
    private Label stepOne;
    private FillTextField worldName;
    private FillTextField difficultyName;
    private Label stepTwo;
    private HoverClickable addButton;
    private HoverClickable confirmitionButton;
    private HoverClickable savingButton;
    private Label errorLabel;
    public VBox vBox;

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
        this.addButton = new HoverClickable(100, 1.05);
        this.confirmitionButton = new HoverClickable(100, 1.05);
        this.savingButton = new HoverClickable(100, 1.05);
        this.errorLabel = new Label();
        this.vBox = new VBox();
    }

    private void layoutNodes()
    {
        this.paddingProperty().setValue(new Insets(36, 12, 12, 12));
        this.setSpacing(48);
        this.setFillWidth(true);
        this.setAlignment(Pos.TOP_CENTER);

        // Top menu (contains back button and title)
        StackPane topMenu = new StackPane();

        ImageView backButtonImage = new ImageView(FillApplication.class.getResource("images/back-arrow.png").toExternalForm());
        backButtonImage.setFitHeight(24);
        backButtonImage.setPreserveRatio(true);

        this.backButton.getStyleClass().add("button-reset");
        this.backButton.paddingProperty().setValue(new Insets(0));
        this.backButton.setGraphic(backButtonImage);
        StackPane.setAlignment(this.backButton, Pos.CENTER_LEFT);

        this.title.getStyleClass().add("h1");
        this.title.setStyle("-fx-text-fill: #E66C6C;");
        StackPane.setAlignment(this.title, Pos.CENTER);

        topMenu.getChildren().addAll(this.backButton, this.title);
        this.getChildren().add(topMenu);

        // Step one: basic info
        VBox stepOneBox = new VBox();
        stepOneBox.setAlignment(Pos.CENTER);
        stepOneBox.setSpacing(24);

        this.stepOne.getStyleClass().add("h3");

        VBox stepOneButtonBox = new VBox();
        stepOneButtonBox.setAlignment(Pos.CENTER);
        stepOneButtonBox.setSpacing(0);

        this.worldName.setMaxWidth(400);
        this.difficultyName.setMaxWidth(400);

        this.errorLabel.setTextFill(Color.web("FF0000"));
        stepOneButtonBox.getChildren().addAll(this.worldName, this.difficultyName, this.errorLabel);
        stepOneBox.getChildren().addAll(this.stepOne, stepOneButtonBox);
        this.getChildren().addAll(stepOneBox);

        // Step two: add levels
        VBox stepTwoBox = new VBox();
        stepTwoBox.setAlignment(Pos.CENTER);
        stepTwoBox.setSpacing(24);

        this.stepTwo.getStyleClass().add("h3");
        stepTwoBox.getChildren().addAll(this.stepTwo);

        this.getChildren().addAll(stepTwoBox);

        addButton.getStyleClass().add("button-reset");
        confirmitionButton.getStyleClass().add("button-reset");
        savingButton.getStyleClass().add("button-reset");

        ImageView addButtonImage = new ImageView(new Image(FillApplication.class.getResource("images/add-button.png").toExternalForm()));
        addButtonImage.setFitWidth(56);
        addButtonImage.setPreserveRatio(true);
        addButton.setGraphic(addButtonImage);

        ImageView confirmButtonImage = new ImageView(new Image(FillApplication.class.getResource("images/confirm-button.png").toExternalForm()));
        confirmButtonImage.setFitWidth(56);
        confirmButtonImage.setPreserveRatio(true);
        confirmitionButton.setGraphic(confirmButtonImage);

        ImageView savingButtonImage = new ImageView(new Image(FillApplication.class.getResource("images/save-icon.png").toExternalForm()));
        savingButtonImage.setFitWidth(56);
        savingButtonImage.setPreserveRatio(true);
        savingButton.setGraphic(savingButtonImage);

        HBox hBoxButtons = new HBox(addButton, confirmitionButton, savingButton);
        hBoxButtons.setAlignment(Pos.CENTER);


        this.getChildren().addAll(hBoxButtons, vBox);
        Insets margin = new Insets(0, 0, 30, 0);
        VBox.setMargin(vBox, margin);
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

    public FillTextField getDifficultyName() {
        return difficultyName;
    }

    public VBox getvBox() {
        return vBox;
    }

    public HoverClickable getAddButton() {
        return addButton;
    }

    public HoverClickable getConfirmitionButton() {
        return confirmitionButton;
    }

    public HoverClickable getSavingButton() {
        return savingButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}

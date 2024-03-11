package be.kdg.fill.views.admin.dimention;

import be.kdg.fill.views.compontents.PrimaryButton;
import be.kdg.fill.views.compontents.SecondaryButton;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DimentionView extends BorderPane {
    private Label rowsLabel;
    private TextField rowTextField;
    private Label colsLabel;
    private TextField colsTextField;
    private MenuButton difficulty;
    private Button acceptButton;
    private Button cancelButton;

    public DimentionView(){
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.rowTextField = new TextField();
        this.rowsLabel = new Label("Rows:");
        this.colsTextField = new TextField();
        this.colsLabel = new Label("Columns");
        this.difficulty = new MenuButton("Difficulty");
        this.acceptButton = new Button();
        this.cancelButton = new Button();
    }

    private void layoutNodes() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(800, 600);

        acceptButton = new PrimaryButton("Accept");
        cancelButton = new SecondaryButton("Cancel");

        MenuItem overworld = new MenuItem("Overwolrd");
        MenuItem night = new MenuItem("Night");
        MenuItem underground = new MenuItem("Underground");
        MenuItem hell = new MenuItem("Hell");
        difficulty.setFont(Font.font(14));
        difficulty.setTextFill(Color.web("#C95C5C"));

        rowsLabel.setTextFill(Color.web("#aa4bc4"));
        rowsLabel.setFont(Font.font(14));
        colsLabel.setTextFill(Color.web("#aa4bc4"));
        colsLabel.setFont(Font.font(14));

        difficulty.getItems().addAll(overworld, night, underground, hell);

        rowTextField.setPromptText("Give the number of rows");
        colsTextField.setPromptText("Give the number of columns");

        HBox numRowBox = new HBox(10, rowsLabel, rowTextField);
        numRowBox.setAlignment(Pos.CENTER_LEFT);
        HBox numColBox = new HBox(10, colsLabel, colsTextField);

        VBox vBox = new VBox(20, difficulty, numRowBox, numColBox, acceptButton, cancelButton);
        vBox.setAlignment(Pos.CENTER_LEFT);
        borderPane.setCenter(vBox);
        this.setRight(borderPane);
    }

    public TextField getRowTextField() {
        return rowTextField;
    }

    public TextField getColsTextField() {
        return colsTextField;
    }

    public MenuButton getDifficulty() {
        return difficulty;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}

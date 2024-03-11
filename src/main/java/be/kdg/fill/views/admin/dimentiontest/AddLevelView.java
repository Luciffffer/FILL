//package be.kdg.fill.views.admin.dimention;
//
//import be.kdg.fill.views.compontents.PrimaryButton;
//import be.kdg.fill.views.compontents.SecondaryButton;
//import javafx.geometry.Pos;
//import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//public class AddLevelView extends BorderPane {
//    private Label levelLabel;
//    private TextField matrixTextField;
//    private Label instructions;
//    private MenuButton difficulty;
//    private Button acceptButton;
//    private Button cancelButton;
//
//    public AddLevelView(){
//        this.initializeNodes();
//        this.layoutNodes();
//    }
//
//    private void initializeNodes() {
//        this.matrixTextField = new TextField();
//        this.instructions = new Label();
//        this.levelLabel = new Label("New Level:");
//        this.difficulty = new MenuButton("Difficulty");
//        this.acceptButton = new Button();
//        this.cancelButton = new Button();
//    }
//
//    private void layoutNodes() {
//        BorderPane borderPane = new BorderPane();
//        borderPane.setPrefSize(800, 600);
//
//        acceptButton = new PrimaryButton("Accept");
//        cancelButton = new SecondaryButton("Cancel");
//
//        MenuItem overworld = new MenuItem("Overwolrd");
//        MenuItem night = new MenuItem("Night");
//        MenuItem underground = new MenuItem("Underground");
//        MenuItem hell = new MenuItem("Hell");
//        difficulty.setFont(Font.font(14));
//        difficulty.setTextFill(Color.web("#C95C5C"));
//
//        levelLabel.setTextFill(Color.web("#aa4bc4"));
//        levelLabel.setFont(Font.font(14));
//
//        difficulty.getItems().addAll(overworld, night, underground, hell);
//
//        instructions.setText("To add a new level, use square brackets with rows separated by spaces. " +
//                "\nInside, use commas for columns and only include 0 or 1. \nExample: \"[1,0,1] [0,0,1] [1,1,1]\".");
//        instructions.setPrefSize(500, 65);
//        instructions.setTextFill(Color.web("#aa4bc4"));
//        instructions.setFont(Font.font("Arial", 15));
//        instructions.setStyle("-fx-border-color: #C95C5C; -fx-border-width: 1px;");
//
//        matrixTextField.setPromptText("Read the instructions and give the matrix");
//        matrixTextField.setStyle("-fx-font-size: 14px;");
//        matrixTextField.setPrefSize(425, 45);
//
//        HBox numRowBox = new HBox(10, levelLabel, matrixTextField);
//        numRowBox.setAlignment(Pos.CENTER_LEFT);
//
//        VBox vBox = new VBox(20, difficulty, instructions, numRowBox, acceptButton, cancelButton);
//        vBox.setAlignment(Pos.CENTER_LEFT);
//        borderPane.setCenter(vBox);
//        this.setRight(borderPane);
//    }
//
//    public TextField getMatrixTextField() {
//        return matrixTextField;
//    }
//
//    public MenuButton getDifficulty() {
//        return difficulty;
//    }
//
//    public Label getInstructions() {
//        return instructions;
//    }
//
//    public Button getAcceptButton() {
//        return acceptButton;
//    }
//
//    public Button getCancelButton() {
//        return cancelButton;
//    }
//}
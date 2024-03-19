package be.kdg.fill.views.gamemenu.addworld.helpers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LevelCreationBox extends BorderPane {
    private Label idLabel;
    private TextField rowTextField;
    private Label rowLabel;
    private TextField columnTextField;
    private Label columnLabel;
    private TextField positionTextField1;
    private TextField positionTextField2;
    private Label positionLabel;
    private Label errorsLabelLevelCreationBox;
    public int id;

    public LevelCreationBox() {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.idLabel = new Label("id: " + id);
        this.rowTextField = new TextField();
        this.rowLabel = new Label("Row:");
        this.columnTextField = new TextField();
        this.columnLabel = new Label("Column:");
        this.positionTextField1 = new TextField();
        this.positionTextField2 = new TextField();
        this.positionLabel = new Label("Start position:");
        this.errorsLabelLevelCreationBox = new Label();
    }

    private void layoutNodes() {
        idLabel.setTextFill(Color.web("#A26CE6"));
        rowTextField.setPromptText("Give the number of rows");
        rowLabel.setTextFill(Color.web("#A26CE6"));
        columnTextField.setPromptText("Give the number of columns");
        columnLabel.setTextFill(Color.web("#A26CE6"));
        positionTextField1.setPromptText("X");
        positionTextField1.setMaxWidth(40);
        positionTextField2.setPromptText("Y");
        positionTextField2.setMaxWidth(40);
        positionLabel.setTextFill(Color.web("#A26CE6"));
        errorsLabelLevelCreationBox.setTextFill(Color.web("FF0000"));

        HBox hBoxRow = new HBox(5, rowLabel, rowTextField);
        hBoxRow.setAlignment(Pos.CENTER_LEFT);
        HBox hBoxColumn = new HBox(5, columnLabel, columnTextField);
        hBoxColumn.setAlignment(Pos.CENTER_LEFT);
        HBox hBoxPosition = new HBox(5, positionLabel, positionTextField1, positionTextField2);
        hBoxPosition.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(20, idLabel, hBoxRow, hBoxColumn, hBoxPosition, errorsLabelLevelCreationBox);
        this.setLeft(vBox);
    }

    public void setId(int id) {
        this.id = id;
        updateIdLabel();
    }

    public void updateIdLabel() {
        idLabel.setText("id: " + id);
    }

    public TextField getRowTextField() {
        return rowTextField;
    }

    public TextField getColumnTextField() {
        return columnTextField;
    }

    public TextField getPositionTextField1() {
        return positionTextField1;
    }

    public TextField getPositionTextField2() {
        return positionTextField2;
    }

    public int getRows() {
        if(getRowTextField().getText().isEmpty()){
            throw new IllegalArgumentException("Rows cannot be empty!");
        }
        try {
            return Integer.parseInt(getRowTextField().getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rows value must be integer!");
        }
    }

    public int getCols() {
        if (getColumnTextField().getText().isEmpty()){
            throw new IllegalArgumentException("Column cannot be empty");
        } else {
            try {
                return Integer.parseInt(getColumnTextField().getText());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Column value must be integer!");
            }
        }
    }

    public int[] startPosCoordination() {
        if (getPositionTextField1().getText().isEmpty()) {
            throw new IllegalArgumentException("X-value cannot be empty!");
        } else if (getPositionTextField2().getText().isEmpty()) {
            throw new IllegalArgumentException("Y-value cannot be empty!");
        } else {
            try {
                int x = Integer.parseInt(getPositionTextField1().getText());
                int y = Integer.parseInt(getPositionTextField2().getText());
                return new int[]{x, y};
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("X and Y must be Integer!");
            }
        }
    }

    public Label getErrorsLabelLevelCreationBox() {
        return errorsLabelLevelCreationBox;
    }
}
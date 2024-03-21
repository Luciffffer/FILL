package be.kdg.fill.views.gamemenu.addworld.helpers;

import be.kdg.fill.views.compontents.FillTextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelCreationBox extends BorderPane {
    private Label idLabel;
    private FillTextField rowTextField;
    private FillTextField columnTextField;
    private FillTextField positionTextField1;
    private FillTextField positionTextField2;
    private Label positionLabel;
    private Label errorsLabelLevelCreationBox;
    public int id;

    public LevelCreationBox() {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.idLabel = new Label("Level id: " + id);
        this.rowTextField = new FillTextField("Number of rows", false);
        this.columnTextField = new FillTextField("Number of columns", false);
        this.positionLabel = new Label("Position: ");
        this.positionTextField1 = new FillTextField("X", false);
        this.positionTextField2 = new FillTextField("Y", false);
        this.errorsLabelLevelCreationBox = new Label();
    }

    private void layoutNodes() {
        idLabel.setTextFill(Color.web("#A26CE6"));
        idLabel.getStyleClass().add("h1");

        positionLabel.setTextFill(Color.web("#A26CE6"));
        positionLabel.getStyleClass().add("h2");
        positionTextField1.setMaxWidth(40);
        positionTextField2.setMaxWidth(40);
        errorsLabelLevelCreationBox.setTextFill(Color.web("FF0000"));

        HBox hBoxPosition = new HBox(5, positionLabel, positionTextField1, positionTextField2);
        hBoxPosition.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(idLabel, rowTextField, columnTextField, hBoxPosition, errorsLabelLevelCreationBox);
        this.setLeft(vBox);
    }

    public void setId(int id) {
        this.id = id;
        updateIdLabel();
    }

    public void updateIdLabel() {
        idLabel.setText("id: " + id);
    }

    public FillTextField getRowTextField() {
        return rowTextField;
    }

    public FillTextField getColumnTextField() {
        return columnTextField;
    }

    public FillTextField getPositionTextField1() {
        return positionTextField1;
    }

    public FillTextField getPositionTextField2() {
        return positionTextField2;
    }

    public int getRows() {
        if (getRowTextField().getField().getText().isEmpty()) {
            throw new IllegalArgumentException("Rows cannot be empty!");
        } else if (getRowTextField().getField().getText().matches(".*\\s.*")) {
            throw new IllegalArgumentException("Rows cannot contain spaces!");
        }
        try {
            return Integer.parseInt(getRowTextField().getField().getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rows value must be integer!");
        }
    }

    public int getCols() {
        if (getColumnTextField().getField().getText().isEmpty()) {
            throw new IllegalArgumentException("Columns cannot be empty!");
        } else if (getColumnTextField().getField().getText().matches(".*\\s.*")) {
            throw new IllegalArgumentException("Columns cannot contain spaces!");
        } else {
            try {
                return Integer.parseInt(getColumnTextField().getField().getText());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Columns value must be integer!");
            }
        }
    }

    public int[] startPosCoordination() {
        if (getPositionTextField1().getField().getText().isEmpty()) {
            throw new IllegalArgumentException("X-value cannot be empty!");
        } else if (getPositionTextField1().getField().getText().matches(".*\\s.*")) {
            throw new IllegalArgumentException("X-value cannot contain spaces!");
        } else if (getPositionTextField2().getField().getText().isEmpty()) {
            throw new IllegalArgumentException("Y-value cannot be empty!");
        } else if (getPositionTextField2().getField().getText().matches(".*\\s.*")) {
            throw new IllegalArgumentException("Y-value cannot contain spaces!");
        } else {
            try {
                int x = Integer.parseInt(getPositionTextField1().getField().getText());
                int y = Integer.parseInt(getPositionTextField2().getField().getText());
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
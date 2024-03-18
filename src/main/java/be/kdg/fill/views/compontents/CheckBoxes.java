package be.kdg.fill.views.compontents;

import be.kdg.fill.views.gamemenu.addworld.AddWorldPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxes extends GridPane {
    private int rows;
    private int cols;
    private CheckBox[][] checkBoxes;

    public CheckBoxes(){
        this.initializeNodes();
        this.layoutNodes();
    }

    public CheckBoxes(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.initializeNodes();
        this.layoutNodes();
    }


    private void initializeNodes() {
        this.checkBoxes = new CheckBox[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int row = i + 1;
                int column = j + 1;
                checkBoxes[i][j] = new CheckBox(row + "x" + column);
            }
        }
    }

    private void layoutNodes() {
        VBox rowsVbox = new VBox(30);
        for (CheckBox[] checkBox : checkBoxes) {
            HBox rowHbox = new HBox(30);
            rowHbox.getChildren().addAll(checkBox);
            rowsVbox.getChildren().add(rowHbox);
        }

        this.add(rowsVbox, 0, 0);
        setMargin(rowsVbox, new Insets(10, 10, 10, 10));
    }

    public int[][] getCheckBoxStatus() {
        int[][] checkBoxStatus = new int[checkBoxes.length][];

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxStatus[i] = new int[checkBoxes[i].length];
            for (int j = 0; j < checkBoxes[i].length; j++) {
                checkBoxStatus[i][j] = checkBoxes[i][j].isSelected() ? 1 : 0;
            }
        }

        return checkBoxStatus;
    }
}
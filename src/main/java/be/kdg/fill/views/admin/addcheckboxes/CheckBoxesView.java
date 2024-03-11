package be.kdg.fill.views.admin.addcheckboxes;

import be.kdg.fill.views.admin.dimention.DimentionPresenter;
import be.kdg.fill.views.compontents.PrimaryButton;
import be.kdg.fill.views.compontents.SecondaryButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckBoxesView extends GridPane {
    private CheckBox[][] checkBoxes;
    private Button addLevelButton;
    private Button cancelButton;

    public CheckBoxesView(){
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        int rows = DimentionPresenter.getRows();
        int cols = DimentionPresenter.getCols();
        this.checkBoxes = new CheckBox[rows][cols];
        this.addLevelButton = new Button();
        this.cancelButton = new Button();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int row = i + 1;
                int column = j + 1;
                checkBoxes[i][j] = new CheckBox(row + "x" + column);
            }
        }
    }

    private void layoutNodes() {
        addLevelButton = new PrimaryButton("Add Level");
        cancelButton = new SecondaryButton("Cancel");

        VBox rowsVbox = new VBox(30);
        for (int i = 0; i < checkBoxes.length; i++){
            HBox rowHbox = new HBox(30);
            rowHbox.getChildren().addAll(checkBoxes[i]);
            rowsVbox.getChildren().add(rowHbox);
        }
        VBox buttonsVbox = new VBox(20);
        buttonsVbox.getChildren().addAll(addLevelButton, cancelButton);

        this.add(rowsVbox, 0, 0);
        this.add(buttonsVbox, 0, 1);
        setMargin(rowsVbox, new Insets(10, 10, 10, 10));
        setMargin(buttonsVbox, new Insets(10, 10, 10, 10));
    }

    public int[][] getCheckBoxesValues(){
        return null;
    }
}

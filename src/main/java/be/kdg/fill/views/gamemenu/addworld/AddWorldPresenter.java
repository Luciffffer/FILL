package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.models.core.Level;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.compontents.CheckBoxes;
import be.kdg.fill.views.compontents.LevelCreationBox;
import be.kdg.fill.views.compontents.World;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AddWorldPresenter implements Presenter {

    private AddWorldView view;
    private GameMenuPresenter parent;
    private List<LevelCreationBox> levelCreationBoxes;
    private List<CheckBoxes> checkBoxesList;
    //private List<Integer> checkBoxesStatusList;
    private List<Level> levels;
    private JSONArray startPosArray;
    private int nextId = 1;
    public static final String SCREEN_NAME = "addworld";

    public AddWorldPresenter(AddWorldView addWorldView, GameMenuPresenter parent) {
        this.view = addWorldView;
        this.parent = parent;
        this.levelCreationBoxes = new ArrayList<>();
        this.checkBoxesList = new ArrayList<>();
        //this.checkBoxesStatusList = new ArrayList<>();
        this.levels = new ArrayList<>();
        this.startPosArray = new JSONArray();
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addLevelInputInfo();
            }
        });
        view.getConfirmitionButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //view.getvBox().getChildren().clear();
                addCheckBoxes();
            }
        });
        view.getSavingButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addWorldAndLevels();
            }
        });
    }

    public void addLevelInputInfo() {
        LevelCreationBox levelCreationBox = new LevelCreationBox();
        levelCreationBox.setId(nextId++);
        this.levelCreationBoxes.add(levelCreationBox);
        view.getvBox().getChildren().addAll(levelCreationBox);
    }

    public void addCheckBoxes() {

        for (LevelCreationBox box : levelCreationBoxes) {
            int rows = box.getRows();
            int cols = box.getCols();


            CheckBoxes checkBoxes = new CheckBoxes(rows, cols);
            checkBoxesList.add(checkBoxes);
            view.getvBox().getChildren().addAll(checkBoxes);
        }
    }

    public void addWorldAndLevels() {
        List<int[][]> checkBoxesStatusMatrix = new ArrayList<>();

        for (CheckBoxes checkBoxes : checkBoxesList) {
            int[][] checkBoxStatus = checkBoxes.getCheckBoxStatus();
            checkBoxesStatusMatrix.add(checkBoxStatus);
        }

        int idJsonLevel = 0;
        for (int[][] matrix : checkBoxesStatusMatrix) {
            idJsonLevel++;
            startPosArray = new JSONArray();
            for (int[] row : matrix) {
                for (int status : row) {
                    System.out.print(status);
                }
                System.out.println();
            }
            LevelCreationBox levelCreationBox = levelCreationBoxes.get(levels.size()); // Krijg het overeenkomstige LevelCreationBox-object
            int[] startPos = levelCreationBox.startPosCoordination();
            startPosArray.add(startPos[0]);
            startPosArray.add(startPos[1]);

            levels.add(new Level(idJsonLevel, matrix, startPosArray));
        }
        String worldName = String.valueOf(view.getWorldName().getField().getText());
        String difficultyName = String.valueOf(view.getDifficultyName().getField().getText());
        // Sla de levels op naar JSON-bestanden
        World world = new World(worldName, difficultyName);
        for (Level level : levels) {
            world.addLevel(level);
        }
        world.saveToJson();
    }

    public void reset() {
        // temp
    }

    @Override
    public AddWorldView getView() {
        return this.view;
    }

    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }
}

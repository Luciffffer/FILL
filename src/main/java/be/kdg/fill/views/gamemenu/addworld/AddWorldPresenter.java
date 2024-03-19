package be.kdg.fill.views.gamemenu.addworld;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.Level;
import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.addworld.helpers.AddWorld;
import be.kdg.fill.views.gamemenu.addworld.helpers.CheckBoxes;
import be.kdg.fill.views.gamemenu.addworld.helpers.LevelCreationBox;
import be.kdg.fill.views.gamemenu.worldselect.WorldSelectPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AddWorldPresenter implements Presenter {

    private AddWorldView view;
    private GameMenuPresenter parent;
    private List<LevelCreationBox> levelCreationBoxes;
    private List<CheckBoxes> checkBoxesList;
    private LevelCreationBox levelCreationBox;
    private List<Level> levels;
    private int nextId = 1;
    public static final String SCREEN_NAME = "addworld";

    public AddWorldPresenter(AddWorldView addWorldView, GameMenuPresenter parent) {
        this.view = addWorldView;
        this.parent = parent;
        this.levelCreationBoxes = new ArrayList<>();
        this.checkBoxesList = new ArrayList<>();
        this.levels = new ArrayList<>();
        this.levelCreationBox = new LevelCreationBox();
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.getSubScreenManager().switchBack();
                WorldSelectPresenter worldSelectPresenter = (WorldSelectPresenter) parent.getSubScreenManager().getCurrentScreen();
                worldSelectPresenter.reload();
                resetTheListsAndView();
            }
        });
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AddWorldInputControl();
                    addLevelInputInfo();
                    view.getErrorLabel().setText("");
                } catch (Exception e) {
                    view.getErrorLabel().setText(e.getMessage());
                }
            }
        });
        view.getConfirmitionButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AddWorldInputControl();
                    view.getErrorLabel().setText("");
                } catch (Exception e) {
                    view.getErrorLabel().setText(e.getMessage());
                }
                for (LevelCreationBox box : levelCreationBoxes) {
                    try {
                        box.getErrorsLabelLevelCreationBox().setText("");
                        levelCreationBoxInputControl(box);
                        addCheckBoxes();
                    } catch (Exception e) {
                        box.getErrorsLabelLevelCreationBox().setText(e.getMessage());
                    }
                }
            }
        });
        view.getSavingButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AddWorldInputControl();
                    view.getErrorLabel().setText("");
                } catch (Exception e) {
                    view.getErrorLabel().setText(e.getMessage());
                }


                try {
                    for (LevelCreationBox box : levelCreationBoxes) {
                        box.getErrorsLabelLevelCreationBox().setText("");
                        levelCreationBoxInputControl(box);
                    }

                    addWorldAndLevels();
                    lastDialog();
                    resetTheListsAndView();
                } catch (Exception e) {
                    for (LevelCreationBox box : levelCreationBoxes) {
                        box.getErrorsLabelLevelCreationBox().setText(e.getMessage());
                    }
                }
            }
        });
    }

    public void addLevelInputInfo() {
        LevelCreationBox levelCreationBox = new LevelCreationBox();
        levelCreationBox.setId(nextId++);
        levelCreationBoxes.add(levelCreationBox);
        view.getvBox().getChildren().addAll(levelCreationBox);
    }

    public void addCheckBoxes() {
        VBox vBox = new VBox(20);

        checkBoxesList.clear();
        for (LevelCreationBox box : levelCreationBoxes) {
            int rows = box.getRows();
            int cols = box.getCols();
            GridPane gridPane = new GridPane();
            gridPane.add(box, 0, 0);
            CheckBoxes checkBoxes = new CheckBoxes(rows, cols);
            checkBoxesList.add(checkBoxes);
            gridPane.add(checkBoxes, 1, 0);
            vBox.getChildren().addAll(gridPane);
        }
        view.getvBox().getChildren().clear();
        view.getvBox().getChildren().add(vBox);
    }


    public void addWorldAndLevels() {
        List<int[][]> checkBoxesStatusMatrix = new ArrayList<>();

        for (CheckBoxes checkBoxes : checkBoxesList) {
            int[][] checkBoxStatus = checkBoxes.getCheckBoxStatus();
            for (int[] rox : checkBoxStatus) {
                for (int cols : rox) {
                    System.out.println(cols + " ");
                }
            }
            checkBoxesStatusMatrix.add(checkBoxStatus);
        }


        int idJsonLevel = 0;
        for (LevelCreationBox levelCreationBox : levelCreationBoxes) {
            JSONArray startPosArray = new JSONArray();

            System.out.println("checkBoxesListv: " + checkBoxesList.size());
            System.out.println("idJsonLevel: " + idJsonLevel);
            System.out.println("checkBoxesStatusMatrix: " + checkBoxesStatusMatrix.size());
            System.out.println("levels: " + levels.size());
            System.out.println("levelCreationBoxes: " + levelCreationBoxes.size());

            int[] startPos = levelCreationBox.startPosCoordination();
            startPosArray.add(startPos[0]);
            startPosArray.add(startPos[1]);


            levels.add(new Level(idJsonLevel + 1, checkBoxesStatusMatrix.get(idJsonLevel), startPosArray));
            idJsonLevel++;
        }

        String worldName = String.valueOf(view.getWorldName().getField().getText());
        String difficultyName = String.valueOf(view.getDifficultyName().getField().getText());

        AddWorld world = new AddWorld(worldName, difficultyName);
        for (Level level : levels) {
            world.addLevel(level);
        }
        world.saveToJson();
    }

    private void AddWorldInputControl() {
        String worldName = String.valueOf(view.getWorldName().getField().getText());
        String difficultyName = String.valueOf(view.getDifficultyName().getField().getText());
        if (worldName == null || worldName.length() < 4 || worldName.length() > 10) {
            throw new IllegalArgumentException("World name must be between 4 and 10 characters long");
        } else if (difficultyName == null || difficultyName.length() < 4 || difficultyName.length() > 10) {
            throw new IllegalArgumentException("Difficulty name must be between 4 and 10 characters long");
        }
    }

    private void levelCreationBoxInputControl(LevelCreationBox box) {
        int rows = box.getRows();
        int cols = box.getCols();

        if (rows > 30 || rows < 1) {
            throw new IllegalArgumentException("Rows value must be between 1 and 30!");
        } else if (cols > 30 || cols < 1) {
            throw new IllegalArgumentException("Columns value must be between 1 and 30!");
        }
        int[] positionCheck = box.startPosCoordination();
        if (positionCheck[0] >= rows || positionCheck[0] < 0) {
            throw new IllegalArgumentException("The X-coordinate must be within the range of 0 to (row - 1)");
        } else if (positionCheck[1] >= cols || positionCheck[1] < 0) {
            throw new IllegalArgumentException("The Y-coordinate must be within the range of 0 to (column - 1)");
        }
    }

    private void resetTheListsAndView() {
        view.getWorldName().getField().clear();
        view.getDifficultyName().getField().clear();
        levelCreationBoxes.clear();
        checkBoxesList.clear();
        levels.clear();
        this.nextId = 1;
        view.getErrorLabel().setText("");
        view.getvBox().getChildren().clear();
    }

    private void lastDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("World succesfully added!");
        dialog.setHeaderText("What's next?");
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image(FillApplication.class.getResourceAsStream("images/fill-icon.png")));

        ButtonType backButton = new ButtonType("Leave", ButtonBar.ButtonData.OK_DONE);
        ButtonType addAnotherWorldButton = new ButtonType("Add another world", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(backButton, addAnotherWorldButton);
        dialog.showAndWait().ifPresent(choice -> {
            if (choice == backButton) {
                //restTheListsAndViews();

                parent.getSubScreenManager().switchBack();

                WorldSelectPresenter worldSelectPresenter = (WorldSelectPresenter) parent.getSubScreenManager().getCurrentScreen();
                worldSelectPresenter.reload();

            } else if (choice == addAnotherWorldButton) {
                //restTheListsAndViews();
                parent.getSubScreenManager().getCurrentScreen();
            }
        });
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
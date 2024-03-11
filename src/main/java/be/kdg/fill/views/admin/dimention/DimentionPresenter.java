package be.kdg.fill.views.admin.dimention;

import be.kdg.fill.views.Presenter;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.admin.addcheckboxes.CheckBoxesPresenter;
import be.kdg.fill.views.admin.addcheckboxes.CheckBoxesView;
import be.kdg.fill.views.gamemenu.GameMenuPresenter;
import be.kdg.fill.views.gamemenu.GameMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DimentionPresenter implements Presenter {
    private DimentionView view;
    //private NewLevel model;
    private ScreenManager screenManager;
    private static int rows;
    private static int cols;

    public static final String SCREEN_NAME = "dimention";

    public DimentionPresenter(DimentionView view, ScreenManager screenManager) {
        this.view = view;
        this.screenManager = screenManager;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getAcceptButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rows = Integer.parseInt(view.getRowTextField().getText());
                cols = Integer.parseInt(view.getColsTextField().getText());
                updateViewToCheckBoxes();
                System.out.println(rows + " " + cols);
            }
        });
        view.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToGameMenu();
            }
        });
    }

    private void updateViewToCheckBoxes(){
        if (screenManager.screenExists("checkboxes")){
            screenManager.switchScreen("checkboxes");
        } else {
            CheckBoxesView checkBoxesView= new CheckBoxesView();
            CheckBoxesPresenter checkBoxesPresenter = new CheckBoxesPresenter(checkBoxesView, screenManager);
            screenManager.addScreen(checkBoxesPresenter);
        }
    }
    private void updateViewToGameMenu() {
        if (screenManager.screenExists("gamemenu")) {
            screenManager.switchScreen("gamemenu");
        } else {
            GameMenuView gameMenuView = new GameMenuView();
            GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView, screenManager);
            screenManager.addScreen(gameMenuPresenter);
        }
    }


    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public DimentionView getView() {
        return view;
    }

    public static int getRows() {
        return rows;
    }

    public static int getCols() {
        return cols;
    }
}

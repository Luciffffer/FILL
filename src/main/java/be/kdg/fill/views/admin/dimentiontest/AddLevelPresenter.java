//package be.kdg.fill.views.admin.dimention;
//
//import be.kdg.fill.models.core.NewLevel;
//import be.kdg.fill.views.Presenter;
//import be.kdg.fill.views.ScreenManager;
//import be.kdg.fill.views.gamemenu.GameMenuPresenter;
//import be.kdg.fill.views.gamemenu.GameMenuView;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//
//import java.util.Arrays;
//
//public class AddLevelPresenter implements Presenter {
//    private AddLevelView view;
//    private NewLevel model;
//    private ScreenManager screenManager;
//
//    public static final String SCREEN_NAME = "dimention";
//
//    public AddLevelPresenter(AddLevelView view, ScreenManager screenManager) {
//        this.view = view;
//        this.screenManager = screenManager;
//        this.model = new NewLevel();
//        this.addEventHandlers();
//    }
//
//    private void addEventHandlers() {
//        view.getAcceptButton().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                String matrixText = view.getMatrixTextField().getText();
//                int[][] matrix = model.parseMatrix(matrixText);
//                    System.out.println(Arrays.deepToString(matrix));
//            }
//        });
//        view.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                updateViewToGameMenu();
//            }
//        });
//    }
//
//    private void updateViewToGameMenu() {
//        if (screenManager.screenExists("gamemenu")) {
//            screenManager.switchScreen("gamemenu");
//        } else {
//            GameMenuView gameMenuView = new GameMenuView();
//            GameMenuPresenter gameMenuPresenter = new GameMenuPresenter(gameMenuView, screenManager);
//            screenManager.addScreen(gameMenuPresenter);
//        }
//    }
//
//
//    @Override
//    public String getScreenName() {
//        return SCREEN_NAME;
//    }
//
//    @Override
//    public AddLevelView getView() {
//        return view;
//    }
//}

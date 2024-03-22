package be.kdg.fill;

import be.kdg.fill.models.core.User;
import be.kdg.fill.models.helpers.UserFile;
import be.kdg.fill.views.ScreenManager;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import be.kdg.fill.views.startmenu.StartMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FillApplication extends Application {

    @Override
    public void start(Stage stage)
    {
        Font.loadFont(getClass().getResource("fonts/Inter-Bold.ttf").toExternalForm(), 16);
        Font.loadFont(getClass().getResource("fonts/Inter-Regular.ttf").toExternalForm(), 16);
        Font.loadFont(getClass().getResource("fonts/Inter-Medium.ttf").toExternalForm(), 16);

        User user = new User(new UserFile("user-data.bin"));
        ScreenManager mainScreenManager = new ScreenManager();
        StartMenuView startMenuView = new StartMenuView();
        
        StartMenuPresenter startMenuPresenter = new StartMenuPresenter(startMenuView, mainScreenManager, user);
        mainScreenManager.addScreen(startMenuPresenter);

        Scene scene = mainScreenManager.getScene();

        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        stage.setScene(scene);

        stage.setTitle("FILL: A Classic Line Fill Puzzle Game");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/fill-icon.png")));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
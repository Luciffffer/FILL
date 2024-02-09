package be.kdg.fill;

import be.kdg.fill.views.mainmenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FillApplication extends Application {
    @Override
    public void start(Stage stage) 
    {
        MainMenuView mainMenuView = new MainMenuView();
        Scene scene = new Scene(mainMenuView);
        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("FILL: A Classic Line Fill Puzzle Game");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/fill-icon.png")));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMinWidth(400);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }
}
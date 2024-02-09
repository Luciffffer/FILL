package be.kdg.fill;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FillApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new BorderPane(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Fill");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
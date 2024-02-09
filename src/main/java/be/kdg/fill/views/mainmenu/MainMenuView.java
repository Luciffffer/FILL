package be.kdg.fill.views.mainmenu;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MainMenuView extends StackPane {

    private Text title;

    public MainMenuView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.title = new Text("Welcome to FILL!");
    }

    private void layoutNodes() 
    {
        title.getStyleClass().add("h1");
        this.getChildren().add(title);
    }

}

package be.kdg.fill.views;

import java.util.Stack;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ScreenManager {
    
    private Stack<Presenter> screens;
    private StackPane rootNode;
    
    public ScreenManager() 
    {
        screens = new Stack<>();
        this.rootNode = new StackPane();
    }

    public boolean screenExists(String screenName)
    {
        for (Presenter screen: screens) {
            if (screen.getScreenName().equals(screenName)) {
                return true;
            }
        }

        return false;
    }

    public void switchScreen(String screenName)
    {
        for (Presenter screen: screens) {
            if (screen.getScreenName().equals(screenName)) {
                screens.remove(screen);
                screens.push(screen);
                this.rootNode.getChildren().clear();
                this.rootNode.getChildren().add(screen.getView());
                return;
            }
        }

        throw new IllegalArgumentException("Screen with name " + screenName + " does not exist.");
    }

    public void switchBack()
    {
        Presenter topScreen = screens.pop();
        screens.add(0, topScreen);
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(screens.peek().getView());
    }

    public void addScreen(Presenter screen)
    {
        screens.push(screen);
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(screen.getView());
    }

    public Pane getRootNode()
    {
        return this.rootNode;
    }

    public Scene getScene()
    {
        return new Scene(this.rootNode);
    }

    public Presenter getCurrentScreen()
    {
        return screens.peek();
    }

}

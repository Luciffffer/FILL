package be.kdg.fill.views;

import java.util.Stack;

import be.kdg.fill.models.core.User;
import be.kdg.fill.models.helpers.UserFile;
import be.kdg.fill.views.startmenu.StartMenuPresenter;
import be.kdg.fill.views.startmenu.StartMenuView;
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
        for (Presenter screen: this.screens) {
            if (screen.getScreenName().equals(screenName)) {
                this.screens.remove(screen);
                this.screens.push(screen);
                this.rootNode.getChildren().clear();
                this.rootNode.getChildren().add(screen.getView());
                
                // reload the screen if it is reloadable
                if (screen instanceof Reloadable) {
                    ((Reloadable) screen).reload();
                }

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

    public void reset()
    {
        this.screens.clear();

        StartMenuView startMenuView = new StartMenuView();
        StartMenuPresenter startMenuPresenter = new StartMenuPresenter(startMenuView, this, new User(new UserFile("user-data.bin")));

        this.addScreen(startMenuPresenter);
    }

}

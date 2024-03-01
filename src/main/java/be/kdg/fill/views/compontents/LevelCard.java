package be.kdg.fill.views.compontents;

import be.kdg.fill.FillApplication;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;

public class LevelCard extends HoverClickable {
    
    private int levelId;
    private boolean completed;
    private boolean locked;
    private RotateTransition rotateTransition;

    public static final int WIDTH = 100;

    public LevelCard(int levelId, boolean completed, boolean locked) 
    {
        super(100, 1.05);
        this.levelId = levelId;
        this.completed = completed;
        this.locked = locked;
        this.layoutCard();
    }

    private void layoutCard() 
    {
        this.getStyleClass().add("button-reset");
        this.getStyleClass().add("level-card");
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(WIDTH);

        if (this.locked) {
            ImageView lock = new ImageView(FillApplication.class.getResource("images/lock.png").toExternalForm());
            lock.setFitHeight(36);
            lock.setPreserveRatio(true);
            this.setGraphic(lock);
            this.getStyleClass().add("locked");
        } else {
            this.setText(String.valueOf(this.levelId));

            if (this.completed) {
                this.getStyleClass().add("completed");
            }
        }
    }


    // GETTERS

    public RotateTransition getRotateTransition() 
    {
        return rotateTransition;
    }


    // SETTERS

    public void setRotateTransition(RotateTransition rotateTransition) 
    {
        this.rotateTransition = rotateTransition;
    }

}

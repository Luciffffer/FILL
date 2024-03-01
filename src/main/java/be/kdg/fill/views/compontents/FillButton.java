package be.kdg.fill.views.compontents;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public abstract class FillButton extends HoverClickable {
    
    public FillButton(String text, String className, String sideImagePath) 
    {
        super(100, 1.05);
        BorderPane borderPane = new BorderPane();

        Label label = new Label(text);
        label.getStyleClass().add(className);
        label.setPadding(new Insets(12, 24, 12, 24));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        borderPane.setCenter(label);

        ImageView left = new ImageView(sideImagePath);
        left.fitHeightProperty().bind(label.heightProperty());
        left.fitWidthProperty().bind(label.heightProperty().divide(2));
        borderPane.setLeft(left);

        ImageView right = new ImageView(sideImagePath);
        right.fitHeightProperty().bind(label.heightProperty());
        right.fitWidthProperty().bind(label.heightProperty().divide(2));
        right.setRotate(180.0);
        borderPane.setRight(right);

        // move images to overlap the label for animation
        BorderPane.setMargin(left, new Insets(0, -1, 0, 0));
        BorderPane.setMargin(right, new Insets(0, 0, 0, -1));

        this.setGraphic(borderPane);
        this.getStyleClass().add("button-reset");
    }

}

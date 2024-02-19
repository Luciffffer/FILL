package be.kdg.fill.views.compontents;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public abstract class FillButton extends Button {
    
    public FillButton(String text, String className, String sideImagePath) 
    {
        super();
        BorderPane borderPane = new BorderPane();

        Label label = new Label(text);
        label.getStyleClass().add(className);
        label.setPadding(new Insets(8, 24, 8, 24));
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
        this.addHoverAnimation(1.05, 100);
    }
    
    private void addHoverAnimation(double scale, int durationMS)
    {
        this.setOnMouseEntered(e -> {
            // hover gained
            ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), this);
            st.setToX(scale);
            st.setToY(scale);
            st.play();
        });
        this.setOnMouseExited(e -> {
            if (!this.isFocused()) {
                // hover lost and not focused
                ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), this);
                st.setToX(1.0);
                st.setToY(1.0);
                st.play();
            }
        });

        this.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    // Focus gained
                    ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), FillButton.this);
                    st.setToX(scale);
                    st.setToY(scale);
                    st.play();
                } else {
                    // Focus lost
                    ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), FillButton.this);
                    st.setToX(1.0);
                    st.setToY(1.0);
                    st.play();
                }
            }
        });
    }

}

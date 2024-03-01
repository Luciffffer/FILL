package be.kdg.fill.views.compontents;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HoverClickable extends Button {
 
    public HoverClickable(int durationMS, double scale) 
    {
        super();
        this.addHoverAnimation(scale, durationMS);
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
                    ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), HoverClickable.this);
                    st.setToX(scale);
                    st.setToY(scale);
                    st.play();
                } else {
                    // Focus lost
                    ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), HoverClickable.this);
                    st.setToX(1.0);
                    st.setToY(1.0);
                    st.play();
                }
            }
        });
    }

}

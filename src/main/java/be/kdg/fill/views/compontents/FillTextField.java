package be.kdg.fill.views.compontents;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FillTextField extends StackPane {
    private String labelText;
    private boolean isEmpty;
    private boolean isPasswordField;

    private Label label;
    private TextInputControl textField;
    private Rectangle indicatorLineBackground;
    private Rectangle indicatorLine;
    private Label errorLabel;

    public FillTextField(String labelText, boolean isPasswordField)
    {
        super();
        this.labelText = labelText;
        this.isPasswordField = isPasswordField;
        this.isEmpty = true;
        this.initializeNodes();
        this.layoutNodes();
        this.addFocusAnimation();
    }

    private void initializeNodes()
    {
        this.label = new Label(this.labelText);
        this.textField = this.isPasswordField ? new PasswordField() : new TextField();
        this.indicatorLineBackground = new Rectangle();
        this.indicatorLine = new Rectangle();
        this.errorLabel = new Label("");
    }

    private void layoutNodes()
    {
        this.setFocusTraversable(true);
        this.setPadding(new javafx.geometry.Insets(20, 0, 24, 0));

        // TextField
        this.textField.getStyleClass().add("text-field-reset");

        // changes font depending on if it's a password field
        // because inter has the ugliest password balls ever
        if (this.isPasswordField) {
            this.textField.getStyleClass().add("password-field");
        } else {
            this.textField.getStyleClass().add("body");
        }

        this.textField.setPadding(new javafx.geometry.Insets(6, 0, 6, 0));

        this.getChildren().add(this.textField);

        // Indicator line
        this.indicatorLineBackground.getStyleClass().add("indicator-line-background");
        this.indicatorLineBackground.widthProperty().bind(this.textField.widthProperty());
        this.indicatorLineBackground.setHeight(1);
        
        StackPane.setAlignment(this.indicatorLineBackground, javafx.geometry.Pos.BOTTOM_LEFT);

        this.indicatorLine.getStyleClass().add("indicator-line");
        this.indicatorLine.scaleXProperty().set(0);
        this.indicatorLine.widthProperty().bind(this.textField.widthProperty());
        this.indicatorLine.setHeight(1);

        StackPane.setAlignment(this.indicatorLine, javafx.geometry.Pos.BOTTOM_LEFT);

        this.getChildren().addAll(this.indicatorLineBackground, this.indicatorLine);

        // name Label
        this.label.getStyleClass().add("body");
        this.label.setStyle("-fx-text-fill: #B78BBE;");
        this.label.prefHeightProperty().bind(this.textField.heightProperty());
        
        StackPane.setAlignment(this.label, javafx.geometry.Pos.BOTTOM_LEFT);

        this.getChildren().add(this.label);

        // error label
        this.errorLabel.getStyleClass().add("small-error");
        this.errorLabel.setWrapText(true);
        StackPane.setAlignment(this.errorLabel, javafx.geometry.Pos.TOP_LEFT);
        this.errorLabel.setTranslateY(40);

        this.getChildren().add(this.errorLabel);
    }

    private void addFocusAnimation()
    {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.TEXT);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.DEFAULT);
            }
        });

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField.requestFocus();
            }
        });

        this.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    textField.requestFocus();  
                }
            }
        });

        this.textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    if (isEmpty) labelAnimation(200, 0.8, -20);
                    indicatorAnimation(300, 1);
                    label.setStyle("-fx-text-fill: #A87EDC;");
                } else {
                    if (isEmpty) labelAnimation(200, 1, 0);
                    if (errorLabel.getText().isEmpty()) indicatorAnimation(300, 0);
                    label.setStyle("-fx-text-fill: #B78BBE;");
                }
            }
        });

        this.textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                isEmpty = newValue.isEmpty() ? true : false; 
            }
        });
    }

    private void labelAnimation(int durationMS, double scale, int translateY)
    {
        double translateX = scale == 1 ? 0 : (-1 + scale) * label.getWidth() / 2 + 1;

        TranslateTransition tt = new TranslateTransition(Duration.millis(durationMS), label);
        tt.setToY(translateY);
        tt.setToX(translateX);
        
        ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), label);
        st.setToX(scale);
        st.setToY(scale);

        ParallelTransition pt = new ParallelTransition(tt, st);
        pt.play();
    }

    private void indicatorAnimation(int durationMS, int scale)
    {
        ScaleTransition st = new ScaleTransition(Duration.millis(durationMS), indicatorLine);
        st.setToX(scale);
        st.setToY(1);
        st.play();
    }

    public void setError(String errorMessage)
    {
        this.errorLabel.setText(errorMessage);
        this.indicatorLine.setStyle("-fx-fill: red");
    }

    public void clearError()
    {
        this.errorLabel.setText("");
        this.indicatorLine.setStyle("");
    }

    public TextInputControl getField()
    {
        return this.textField;
    }

}

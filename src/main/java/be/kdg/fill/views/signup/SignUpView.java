package be.kdg.fill.views.signup;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.PrimaryButton;
import be.kdg.fill.views.compontents.SecondaryButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SignUpView extends BorderPane {
    private ImageView logo;
    private Button signUpButton;
    private TextField usernameTextField;
    private Label usernameLabel;
    private PasswordField passwordPasswordField;
    private Label passwordLabel;
    private Text signUpMessageLabel;
    private Button cancelButton;
    private Label instructionsLabel;
    private ImageView sideImageDown;
    private ImageView sideImageUp;

    public SignUpView(){
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.sideImageUp = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.sideImageDown = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());

        this.signUpButton = new PrimaryButton("Sign Up");
        this.cancelButton = new SecondaryButton("Cancel");

        this.usernameTextField = new TextField();
        usernameTextField.setPromptText("Type your new username here");

        this.passwordPasswordField = new PasswordField();
        passwordPasswordField.setPromptText("Type your new password here");

        this.usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", 15));

        this.passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", 15));

        this.signUpMessageLabel = new Text();
        signUpMessageLabel.setFill(Color.RED);

        this.instructionsLabel = new Label();
        instructionsLabel.setText("Username must be between 3 and 20 characters long \nand cannot contain spaces.");
        instructionsLabel.setTextFill(Color.web("#aa4bc4"));
        instructionsLabel.setFont(Font.font("Arial Narrow", 15));
    }

    private void layoutNodes() {
        BorderPane outerBorderPane = new BorderPane();
        outerBorderPane.setPrefSize(800, 600);

        BorderPane innerBorderPane = new BorderPane();
        innerBorderPane.setPrefSize(600, 400);

        logo.setFitHeight(120);
        usernameTextField.setMaxWidth(300);
        passwordPasswordField.setMaxWidth(300);
        signUpButton.setMinWidth(200);
        cancelButton.setMinWidth(200);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(logo,instructionsLabel, usernameLabel, usernameTextField, passwordLabel, passwordPasswordField, signUpMessageLabel,
                signUpButton, cancelButton);

        vbox.setAlignment(Pos.CENTER_LEFT);
        innerBorderPane.setCenter(vbox);
        outerBorderPane.setCenter(innerBorderPane);

        this.setRight(innerBorderPane);
    }


    public Button getSignUpButton() {
        return signUpButton;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }


    public Text getSignUpMessageLabel() {
        return signUpMessageLabel;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}

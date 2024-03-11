package be.kdg.fill.views.login;

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

public class LoginView extends BorderPane {
    private ImageView sideImageLeftUp;
    private ImageView sideImageRightDown;
    private ImageView logo;
    private Button loginButton;
    private TextField usernameTextField;
    private Label usernameLabel;
    private PasswordField passwordPasswordField;
    private Label passwordLabel;
    private Text loginMessageLabel;
    private Button cancelButton;
    
    public LoginView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes()
    {
        this.sideImageLeftUp = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.sideImageRightDown = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());

        this.loginButton = new PrimaryButton("Log in");
        this.cancelButton = new SecondaryButton("Cancel");

        this.usernameLabel = new Label("Username");
        this.usernameLabel.setFont(Font.font("Arial", 15));

        this.passwordLabel = new Label("Password");
        this.passwordLabel.setFont(Font.font("Arial", 15));

        this.usernameTextField = new TextField();
        this.usernameTextField.setPromptText("Type your username here");

        this.passwordPasswordField = new PasswordField();
        this.passwordPasswordField.setPromptText("Type your password here");

        this.loginMessageLabel = new Text();
        this.loginMessageLabel.setFill(Color.RED);
    }

    private void layoutNodes()
    {
        BorderPane outerBorderPane = new BorderPane();
        outerBorderPane.setPrefSize(800, 600);

        BorderPane innerBorderPane = new BorderPane();
        innerBorderPane.setPrefSize(600, 400);


        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(logo, usernameLabel, usernameTextField, passwordLabel, passwordPasswordField, loginMessageLabel,
                loginButton, cancelButton);

        vbox.setAlignment(Pos.CENTER_LEFT);
        innerBorderPane.setCenter(vbox);
        outerBorderPane.setCenter(innerBorderPane);

        this.setRight(innerBorderPane);

        logo.setFitHeight(120);

        usernameTextField.setMaxWidth(300);
        passwordPasswordField.setMaxWidth(300);
        loginButton.setMinWidth(200);
        cancelButton.setMinWidth(200);
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Text getLoginMessageLabel() {
        return loginMessageLabel;
    }
}

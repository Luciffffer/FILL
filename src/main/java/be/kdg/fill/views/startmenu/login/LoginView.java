package be.kdg.fill.views.startmenu.login;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.FillTextField;
import be.kdg.fill.views.compontents.HoverClickable;
import be.kdg.fill.views.compontents.PrimaryButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginView extends StackPane {
    private ImageView logo;
    private FillTextField usernameTextField;
    private FillTextField passwordPasswordField;
    private Label title;
    private HoverClickable backButton;
    private PrimaryButton loginButton;
    private Button signUpButton;
    private Label errorLabel;
    
    public LoginView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes()
    {
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());

        this.title = new Label("Log in");
        this.backButton = new HoverClickable(100, 1.05);

        this.usernameTextField = new FillTextField("Username", false);
        this.passwordPasswordField = new FillTextField("Password", true);

        this.loginButton = new PrimaryButton("Log in");
        this.signUpButton = new Button("Sign up");

        this.errorLabel = new Label();
    }

    private void layoutNodes()
    {
        VBox contentPane = new VBox();
        contentPane.setAlignment(Pos.CENTER);
        contentPane.setSpacing(36.0);

        // logo
        this.logo.setPreserveRatio(true);
        this.logo.setFitWidth(175);

        contentPane.getChildren().add(logo);

        // title & backbutton
        VBox mainBox = new VBox();
        mainBox.setSpacing(24.0);
        mainBox.setAlignment(Pos.CENTER);
        contentPane.getChildren().add(mainBox);

        StackPane titlePane = new StackPane();
        titlePane.setMaxWidth(350);

        this.title.getStyleClass().add("h2");
        StackPane.setAlignment(title, Pos.CENTER);

        ImageView backArrow = new ImageView(new Image(FillApplication.class.getResource("images/back-arrow.png").toExternalForm()));
        backArrow.setFitWidth(24);
        backArrow.setPreserveRatio(true);
        this.backButton.setGraphic(backArrow);
        this.backButton.getStyleClass().add("button-reset");
        StackPane.setAlignment(this.backButton, Pos.CENTER_LEFT);

        titlePane.getChildren().addAll(this.backButton, this.title);
        mainBox.getChildren().add(titlePane);

        // error label
        this.errorLabel.getStyleClass().add("body");
        this.errorLabel.setStyle("-fx-text-fill: red;");
        this.errorLabel.visibleProperty().bind(this.errorLabel.textProperty().isNotEmpty());
        this.errorLabel.managedProperty().bind(this.errorLabel.visibleProperty());
        mainBox.getChildren().add(this.errorLabel);

        // input fields
        VBox inputBox = new VBox();
        inputBox.setSpacing(6.0);
        inputBox.setAlignment(Pos.CENTER);

        usernameTextField.setMaxWidth(330);
        passwordPasswordField.setMaxWidth(330);

        inputBox.getChildren().addAll(usernameTextField, passwordPasswordField);

        mainBox.getChildren().add(inputBox);

        // buttons
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(12.0);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new javafx.geometry.Insets(12, 0, 0, 0));

        this.loginButton.setMinWidth(175);
        this.signUpButton.getStyleClass().add("button-reset");
        this.signUpButton.getStyleClass().add("text-button");

        buttonBox.getChildren().addAll(this.loginButton, this.signUpButton);
        mainBox.getChildren().add(buttonBox);

        this.getChildren().add(contentPane);
    }


    // GETTERS

    public FillTextField getUsernameField()
    {
        return this.usernameTextField;
    }

    public FillTextField getPasswordField() 
    {
        return this.passwordPasswordField;
    }

    public HoverClickable getBackButton() 
    {
        return this.backButton;
    }

    public PrimaryButton getLoginButton()
    {
        return this.loginButton;
    }

    public Button getSignUpButton()
    {
        return this.signUpButton;
    }

    public Label getErrorLabel()
    {
        return this.errorLabel;
    }
}

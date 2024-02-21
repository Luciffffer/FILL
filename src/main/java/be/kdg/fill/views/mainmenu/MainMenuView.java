package be.kdg.fill.views.mainmenu;

import be.kdg.fill.FillApplication;
import be.kdg.fill.views.compontents.PrimaryButton;
import be.kdg.fill.views.compontents.SecondaryButton;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenuView extends StackPane {

    private Text title;
    private Text subtitle;
    private ImageView logo;
    private ImageView sideImageLeft;
    private ImageView sideImageRight;
    private PrimaryButton loginButton;
    private SecondaryButton registerButton;

    public MainMenuView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.title = new Text("Welcome to FILL!");
        this.subtitle = new Text("Please log in or create an account to continue.");
        this.logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.sideImageLeft = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.sideImageRight = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.loginButton = new PrimaryButton("Log in");
        this.registerButton = new SecondaryButton("Sign up");
    }

    private void layoutNodes() 
    {
        // Layer 1: Background

        AnchorPane backgroundPane = new AnchorPane();
        
        this.sideImageLeft.setFitWidth(200);
        this.sideImageLeft.setFitHeight(200);
        this.sideImageLeft.setPreserveRatio(true);
        
        this.sideImageRight.setFitWidth(200);
        this.sideImageRight.setFitHeight(200);
        this.sideImageRight.setPreserveRatio(true);
        this.sideImageRight.setRotate(180.0);

        AnchorPane.setTopAnchor(sideImageLeft, 0.0);
        AnchorPane.setLeftAnchor(sideImageLeft, 0.0);

        AnchorPane.setBottomAnchor(sideImageRight, 0.0);
        AnchorPane.setRightAnchor(sideImageRight, 0.0);

        backgroundPane.getChildren().addAll(sideImageLeft, sideImageRight);
        this.getChildren().add(backgroundPane);

        // Layer 2: Content

        VBox contentPane = new VBox();
        contentPane.setAlignment(javafx.geometry.Pos.CENTER);
        contentPane.setSpacing(48.0);

        this.logo.setPreserveRatio(true);
        this.logo.setFitWidth(300);

        VBox textGroup = new VBox();
        textGroup.setAlignment(javafx.geometry.Pos.CENTER);
        textGroup.setSpacing(16.0);

        this.title.getStyleClass().add("h1");
        this.subtitle.getStyleClass().add("body");

        textGroup.getChildren().addAll(title, subtitle);

        VBox buttonGroup = new VBox();
        buttonGroup.setAlignment(javafx.geometry.Pos.CENTER);
        buttonGroup.setSpacing(16.0);
        buttonGroup.getChildren().addAll(loginButton, registerButton);

        loginButton.setMinWidth(176);
        registerButton.setMinWidth(176);

        contentPane.getChildren().addAll(logo, textGroup, buttonGroup);

        this.getChildren().add(contentPane);
    }


    // GETTERS

    public Button getLoginButton() 
    {
        return loginButton;
    }

    public Button getRegisterButton() 
    {
        return registerButton;
    }

}

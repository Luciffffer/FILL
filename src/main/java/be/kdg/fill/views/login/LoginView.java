package be.kdg.fill.views.login;

import be.kdg.fill.FillApplication;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class LoginView extends BorderPane {
    
    public LoginView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes()
    {

    }

    private void layoutNodes()
    {
        ImageView logo = new ImageView(FillApplication.class.getResource("images/fill-logo.png").toExternalForm());
        this.getChildren().add(logo);
    }

}

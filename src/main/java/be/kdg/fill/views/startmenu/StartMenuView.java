package be.kdg.fill.views.startmenu;

import be.kdg.fill.FillApplication;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * This view contains the start menu background. The content/second layer can be changed.
 */
public class StartMenuView extends StackPane {
    
    private ImageView sideImageLeft;
    private ImageView sideImageRight;

    public StartMenuView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.sideImageLeft = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
        this.sideImageRight = new ImageView(FillApplication.class.getResource("images/background-graphic-blocks.png").toExternalForm());
    }

    private void layoutNodes() 
    {
        this.setStyle("-fx-background-color: #FAF9FB;");

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
    }

    public void setContent(Pane content) 
    {
        this.getChildren().add(content);
    }

}

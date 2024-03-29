package be.kdg.fill.views.compontents;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.World;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WorldCard extends HoverClickable {
    
    private Text title;
    private ImageView image;
    private Text difficulty;
    private Text completedLevels;
    public static final int WIDTH = 150;

    public WorldCard(World world, int completedLevels) 
    {
        super(100, 1.05);
        this.initializeNodes(world, completedLevels);
        this.layoutNodes();
    }

    private void initializeNodes(World world, int completedLevels) 
    {
        this.title = new Text(world.getName());
        this.image = new ImageView(new Image(FillApplication.class.getResource(world.getImagePath()).toExternalForm()));
        this.difficulty = new Text(world.getDifficultyName());
        this.completedLevels = new Text(completedLevels + "/" + world.getLevelCount());
    }

    private void layoutNodes() 
    {
        this.paddingProperty().setValue(new javafx.geometry.Insets(0));
        this.getStyleClass().add("button-reset");
        this.setPrefWidth(WIDTH);

        VBox vBox = new VBox();
        vBox.setSpacing(16);

        this.image.setPreserveRatio(true);
        this.image.setFitWidth(WIDTH);

        VBox textBox = new VBox();
        textBox.setSpacing(8);

        this.title.getStyleClass().add("h2");

        AnchorPane informationPane = new AnchorPane();

        this.difficulty.getStyleClass().add("small");
        this.completedLevels.getStyleClass().add("small");

        AnchorPane.setLeftAnchor(this.difficulty, 0.0);
        AnchorPane.setBottomAnchor(this.difficulty, 0.0);

        AnchorPane.setRightAnchor(this.completedLevels, 0.0);
        AnchorPane.setBottomAnchor(this.completedLevels, 0.0);

        informationPane.getChildren().addAll(this.difficulty, this.completedLevels);
        textBox.getChildren().addAll(this.title, informationPane);
        vBox.getChildren().addAll(this.image, textBox);

        this.setGraphic(vBox);
    }

}

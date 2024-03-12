package be.kdg.fill.views.gamemenu.worldselect;

import be.kdg.fill.views.compontents.WorldCard;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WorldSelectView extends VBox {
    
    private Text smallTitle;
    private Text title;
    private TilePane worldCards;

    public WorldSelectView() 
    {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() 
    {
        this.smallTitle = new Text("Hello");
        this.title = new Text("Select a world to play!");
        this.worldCards = new TilePane();
    }

    private void layoutNodes() 
    {
        this.paddingProperty().setValue(new javafx.geometry.Insets(36, 12, 12, 12));
        this.setSpacing(48);
        this.setFillWidth(true);

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setSpacing(6);

        this.smallTitle.getStyleClass().add("body");
        this.title.getStyleClass().add("h1");

        vBox.getChildren().addAll(this.smallTitle, this.title);

        this.worldCards.setHgap(24);
        this.worldCards.setVgap(24);
        this.worldCards.setAlignment(javafx.geometry.Pos.CENTER);

        this.getChildren().addAll(vBox, this.worldCards);
    }

    public void setSmallTitle(String smallTitle) 
    {
        this.smallTitle.setText(smallTitle);
    }

    public void addWorldCard(WorldCard worldCard) 
    {
        this.worldCards.getChildren().add(worldCard);
    }

    public void clearWorldCards() 
    {
        this.worldCards.getChildren().clear();
    }

}

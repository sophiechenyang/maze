package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.GameModel;

public class WandScene {
	private GameModel gameModel;
	
	public WandScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	public Parent makeWandScene() {
		Pane root = new Pane();
		
		Text text = new Text("Hover over harry and scroll to move it to the upper right corner");
		
    	Image wandImg = new Image("file:img/elder-wand.png");
    	ImageView wandView = new ImageView(wandImg);
    	wandView.setFitWidth(120);
    	wandView.setX(500);
    	wandView.setY(0);
    	wandView.setPreserveRatio(true);
    	
    	Image harryImg = new Image("file:img/harry_broom.png");
    	ImageView harryView = new ImageView(harryImg);
    	harryView.setFitWidth(80);
    	harryView.setX(0);
    	harryView.setY(400);
    	harryView.setPreserveRatio(true);
    	
    	harryView.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent event) {
            	harryView.setTranslateX(harryView.getTranslateX() + event.getDeltaX());
            	harryView.setTranslateY(harryView.getTranslateY() + event.getDeltaY());
                //event.consume();
            	
            	if (harryView.getTranslateX()> 500 && harryView.getTranslateY() < -100) {
            		text.setText("wand retrieved");
            		System.out.println("wand retrieved!");
            		wandView.setImage(null);
            		gameModel.setWandRetrieved(true);
            	}
                
                System.out.println(harryView.getTranslateX());
                System.out.println(harryView.getTranslateY());
            }
        });
    	
		root.getChildren().addAll(wandView, harryView, text);
		return root;
	}
}

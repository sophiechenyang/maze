package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.GameModel;

public class SnakeScene {

	private GameModel gameModel;
	
	public SnakeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeSnakeScene() {
		VBox root = new VBox();
		
    	Image naginiImg = new Image("file:img/nagini.png");
    	ImageView naginiView = new ImageView(naginiImg);
    	naginiView.setFitWidth(700);
    	naginiView.setPreserveRatio(true);
    	
    	Text text = new Text("Kill the snake by shrinking it ");
    	
    	naginiView.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
            	naginiView.setScaleX(naginiView.getScaleX() * event.getZoomFactor());
            	naginiView.setScaleY(naginiView.getScaleY() * event.getZoomFactor());

            	System.out.println(naginiView.getScaleX());
            	if (naginiView.getScaleX() < 0.1);
            		text.setText("Now the snake is harmless");
            		gameModel.setSnakeDefeated(true);
            
            }
            
        });
    	
		root.getChildren().addAll(naginiView, text);
		return root;
	}
}

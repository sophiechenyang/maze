package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.GameModel;

public class SwipeScene {
	private GameModel gameModel;
	
	public SwipeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeSwipeScene() {
		//VBox root = new VBox();
		Pane root = new Pane();
		
		Rectangle rect = new Rectangle(100, 100, 100, 100);
        rect.setFill(Color.DARKMAGENTA);
        
		Button closeButton = new Button("Return to Game");
		
		Text text = new Text("Hover over harry and scroll to move it to the upper right corner");
    	
    	Image clickImg = new Image("file:img/harry.png");
    	ImageView clickView = new ImageView(clickImg);
    	clickView.setFitWidth(200);
    	clickView.setPreserveRatio(true);
		
    	Image swipeImg = new Image("file:img/safe.jpeg");
    	ImageView swipeView = new ImageView(swipeImg);
    	swipeView.setX(70);
    	swipeView.setFitWidth(500);
    	swipeView.setPreserveRatio(true);
    	
    	swipeView.setOnScrollFinished(new EventHandler<ScrollEvent>(){
    		@Override public void handle(ScrollEvent event) {
            	System.out.println(event);
            	//rect.setTranslateX(rect.getTranslateX() + event.getDeltaX());
                //rect.setTranslateY(rect.getTranslateY() + event.getDeltaY());
                rect.setFill(Color.ANTIQUEWHITE);
                rect.setY(300);
                
                
                
                if(event.getTotalDeltaX() > 100) {
                	System.out.println("swiped right");
                } else if (event.getTotalDeltaX() < -100) {
                	System.out.println("swiped left");
                } else if (event.getTotalDeltaY() < -100) {
                	System.out.println("swiped up");
                } else if (event.getTotalDeltaY() > 100) {
                	System.out.println("swiped down");
                }
            }	
            
        });  
    	
    	root.getChildren().addAll(clickView, text, rect, swipeView);
    	return root; 	
	}
}

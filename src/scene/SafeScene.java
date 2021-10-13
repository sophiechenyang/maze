package scene;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;

public class SafeScene {
	private GameModel gameModel;
	
	public SafeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeSafeScene() {
		VBox root = new VBox();
		Button closeButton = new Button("Return to Game");
		
		Text text = new Text("Hover over harry and scroll to move it to the upper right corner");
    	
    	Image harryImg = new Image("file:img/safe.jpeg");
    	ImageView safeView = new ImageView(harryImg);
    	safeView.setFitWidth(500);

    	safeView.setPreserveRatio(true);
    	
    	safeView.setOnRotate(new EventHandler<RotateEvent>() {
			@Override
			public void handle(RotateEvent event) {
				safeView.setRotate(safeView.getRotate() + event.getAngle());
				
				if (safeView.getRotate()>90 && !gameModel.isCodeRetrieved()) {
					gameModel.setCodeRetrieved(true);
					Text text = new Text("rotation success !");
					
					closeButton.setOnMouseClicked(e -> closeWindow());
					root.getChildren().addAll(text, closeButton);
				}
				
			}

			private void closeWindow() {
				Stage stage = (Stage) closeButton.getScene().getWindow();
				stage.close();
			}
        });
    	
		root.getChildren().addAll(safeView, text);
		return root;
		
	}
}

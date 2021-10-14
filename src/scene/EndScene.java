package scene;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameModel;

public class EndScene {
	private GameModel gameModel;
	private Button closeButton = new Button("Return to Game");
	
	public EndScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeEndScene() {
		Pane root = new Pane();
    	
    	Image successImg = new Image("file:img/success_end.png");
    	ImageView successView = new ImageView(successImg);
    	successView.setFitWidth(800);

    	successView.setPreserveRatio(true);
    	
		root.getChildren().addAll(successView);
		return root;
		
	}
	
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

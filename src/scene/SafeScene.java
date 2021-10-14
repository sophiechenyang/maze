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
	private Button closeButton = new Button("Equip the Book");
	
	public SafeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeSafeScene() {
		Pane root = new Pane();
		
    	Image sealImg = new Image("file:img/magic_seal.png");
    	ImageView sealView = new ImageView(sealImg);
    	sealView.setFitWidth(245);
    	sealView.setPreserveRatio(true);
    	sealView.setX(279.5);
    	sealView.setY(102.5);
		
		Image introImg = new Image("file:img/rotate_intro.png");
		ImageView introView = new ImageView(introImg);
		introView.setOnMouseClicked(e -> introView.setImage(null));
		
		Image instructImg = new Image("file:img/rotate_instruct.png");
		ImageView instructView = new ImageView(instructImg);
    	
		Image unlockedImg = new Image("file:img/rotate_unlocked.png");
		ImageView unlockedView = new ImageView(unlockedImg);
		
		closeButton.setLayoutX(350);
		closeButton.setLayoutY(155);
		closeButton.setOnMouseClicked(e -> closeWindow());
    	
    	
    	sealView.setOnRotate(new EventHandler<RotateEvent>() {
			@Override
			public void handle(RotateEvent event) {
				sealView.setRotate(sealView.getRotate() + event.getAngle());
				
				if (sealView.getRotate()> 180 && !gameModel.isCodeRetrieved()) {
					gameModel.setCodeRetrieved(true);
					root.getChildren().clear();
					root.getChildren().addAll(unlockedView, closeButton);
				}
				
			}
        });
    	
    	//root.getChildren().addAll(introView, sealView);
		root.getChildren().addAll(instructView,introView,sealView);
		return root;
		
	}
	
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;

public class SnakeScene {

	private GameModel gameModel;
	Button closeButton = new Button("Continue to Game");

	public SnakeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public Parent makeSnakeScene() {
		Pane root = new Pane();

		Image instructImg = new Image("file:img/snake_instruct.png");
		ImageView instructView = new ImageView(instructImg);
		
		Image naginiImg = new Image("file:img/nagini.png");
		ImageView naginiView = new ImageView(naginiImg);
		naginiView.setFitWidth(470);
		naginiView.setPreserveRatio(true);
		naginiView.setX(64);
		naginiView.setY(140);
		
		closeButton.setLayoutX(600);
		closeButton.setLayoutY(260);
		closeButton.setOnMouseClicked(e -> closeWindow());
		
		Image snakedefeated = new Image("file:img/snake_defeated.png");

		naginiView.setOnZoom(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
				naginiView.setScaleX(naginiView.getScaleX() * event.getZoomFactor());
				naginiView.setScaleY(naginiView.getScaleY() * event.getZoomFactor());

				//System.out.println(naginiView.getScaleX());
				if (naginiView.getScaleX() < 0.18 && !gameModel.isSnakeDefeated()) {
					
					gameModel.setSnakeDefeated(true);
					instructView.setImage(snakedefeated);
					root.getChildren().addAll(closeButton);
				}
			}
		});

		root.getChildren().addAll(instructView, naginiView);
		return root;
	}

	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

package scene;

import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.GameModel;
import view.GameView;

public class ReadyScene {

	private GameModel gameModel;
	private GameView gameView;
	Button closeButton = new Button("Back to the Maze");

	public ReadyScene(GameModel gameModel) {
		this.gameModel = gameModel;
		this.gameView = gameView;
	}

	public Parent makeReadyScene() {
		Pane root = new Pane();

		Image instructImg = new Image("file:img/notReady.png");
		ImageView instructView = new ImageView(instructImg);
		
		closeButton.setLayoutX(350);
		closeButton.setLayoutY(180);
		closeButton.setOnMouseClicked(e -> closeWindow());

		root.getChildren().addAll(instructView, closeButton);
		return root;
	}

	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

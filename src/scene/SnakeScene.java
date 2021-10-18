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

public class SnakeScene {

	private GameModel gameModel;
	private GameView gameView;
	Button closeButton = new Button("Continue to Game");
	Image naginiImg = new Image("file:img/nagini.png");
	ImageView naginiView = new ImageView(naginiImg);

	public SnakeScene(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
	}

	public Parent makeSnakeScene() {
		Pane root = new Pane();

		Image instructImg = new Image("file:img/snake_instruct.png");
		ImageView instructView = new ImageView(instructImg);
		animateNagini();
		

		naginiView.setFitWidth(250);
		naginiView.setPreserveRatio(true);
		naginiView.setX(140);
		naginiView.setY(190);
		
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
					gameModel.increaseGamePointsBy(2000);
					gameView.updateScore(gameModel);
					instructView.setImage(snakedefeated);
					root.getChildren().addAll(closeButton);
				}
			}
		});

		root.getChildren().addAll(instructView, naginiView);
		return root;
	}
	
	private void animateNagini() {
		FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), naginiView);
		fadeIn.setFromValue(0.0f);
		fadeIn.setToValue(1.0f);
		fadeIn.setCycleCount(1);
		
        ScaleTransition scaleTransition = 
                new ScaleTransition(Duration.millis(2000), naginiView);
            scaleTransition.setToX(2f);
            scaleTransition.setToY(2f);
            scaleTransition.setCycleCount(1);
		
//        SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, scaleTransition);
//        sequentialTransition.setCycleCount(1);
//        sequentialTransition.play();
        
        ParallelTransition parallelTransition = new ParallelTransition(
                fadeIn,
                scaleTransition
        );
        
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
	}

	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

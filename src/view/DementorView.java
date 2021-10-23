package view;

import java.util.ArrayList;

import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.DementorModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;

public class DementorView extends ImageView {
	private Image dementorImage = new Image("file:img/dementor2.png");
	FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), this);
	TranslateTransition goUp = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition = new RotateTransition(Duration.millis(800), this);
	TranslateTransition goRight = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(800), this);
	TranslateTransition goDown = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition3 = new RotateTransition(Duration.millis(800), this);
	TranslateTransition goLeft = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition4 = new RotateTransition(Duration.millis(800), this);

	SequentialTransition sequentialOngoing = new SequentialTransition(goUp, rotateTransition, goRight,
			rotateTransition2, goDown, rotateTransition3, goLeft, rotateTransition4);

	SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, sequentialOngoing);
	private int dementorX;
	private int dementorY;
	private GameController gameController;

	public DementorView(DementorModel dementor, GameController gameController) {
		int tileSize = TileModel.getTileSize();

		this.gameController = gameController;
		this.setImage(dementorImage);
		this.setFitWidth(tileSize);
		this.setFitHeight(tileSize);
		setFocusTraversable(true);

		dementorX = dementor.getX();
		dementorY = dementor.getY();
		int increment = tileSize;
		// int increment = tileSize * 2;
		int currentdementorX = dementorX * tileSize;
		int currentdementorY = dementorY * tileSize;

		setTranslateX(currentdementorX);
		setTranslateY(currentdementorY);

		animateDementor(dementor, currentdementorY, currentdementorX, increment);
	}

	private void animateDementor(DementorModel dementor, int currentdementorY, int currentdementorX, int increment) {

		fadeIn.setFromValue(0.0f);
		fadeIn.setToValue(0.6f);
		fadeIn.setCycleCount(1);

		goUp.setToY(currentdementorY - increment);

		rotateTransition.setByAngle(90f);
		rotateTransition.setOnFinished(e -> {
			dementor.setX(dementorX + 1);
		});

		goRight.setToX(currentdementorX + increment);

		rotateTransition2.setByAngle(90f);
		rotateTransition2.setOnFinished(e -> {

			dementor.setY(dementorY);
		});

		goDown.setToY(currentdementorY);

		rotateTransition3.setByAngle(90f);
		rotateTransition3.setOnFinished(e -> {

			dementor.setX(dementorX);
		});

		goLeft.setToX(currentdementorX);

		rotateTransition4.setByAngle(90f);
		rotateTransition4.setOnFinished(e -> {

			dementor.setY(dementorY - 1);
		});

		sequentialOngoing.setCycleCount(Timeline.INDEFINITE);

		sequentialTransition.play();

	}

	public void stopDementor() {
		sequentialTransition.stop();
	}

	public void fadeDementor(DementorView dementorview) {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), dementorview);
		fadeOut.setFromValue(1.0f);
		fadeOut.setToValue(0.0f);
		fadeOut.setCycleCount(1);
		fadeOut.setAutoReverse(false);
		fadeOut.play();
	};

	public void setPlayerHandler(EventHandler<MouseEvent> listenForClick) {
		this.setOnMouseClicked(listenForClick);
	}

	public void setHoverHandler(EventHandler<MouseEvent> checkForHover) {
		this.setOnMouseEntered(checkForHover);
	}

	public void moveX(int x) {
		setTranslateX(x);
	}

	public void moveY(int y) {
		setTranslateY(y);
	}

}

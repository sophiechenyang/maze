package view;

import java.util.ArrayList;

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
import model.BeetleModel;
import model.GameModel;

public class BeetleView extends ImageView {
	private Image beetleImage = new Image("file:img/beetle.png");
	FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), this);
	TranslateTransition goUp = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition = new RotateTransition(Duration.millis(1500), this);
	TranslateTransition goDown = new TranslateTransition(Duration.millis(1000), this);
	RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(1500), this);
	SequentialTransition sequentialOngoing = new SequentialTransition(
			goUp, 
			rotateTransition,
			goDown,
			rotateTransition2);

	
	SequentialTransition sequentialTransition = new SequentialTransition(
		fadeIn, sequentialOngoing
	);

	public BeetleView(BeetleModel beetle) {
		this.setImage(beetleImage);
		this.setFitWidth(25);
		this.setFitHeight(25);
		setFocusTraversable(true);

		int beetleX = beetle.getX();
		int beetleY = beetle.getY();
		int translate = 45;
		int increment = 35;
		int currentBeetleX = 7 + beetleX  * translate;
		int currentBeetleY = 7 + beetleY * translate;

		setTranslateX(currentBeetleX);
		setTranslateY(currentBeetleY);
		

		animateBeetle(currentBeetleY, increment);
	}
	
	private void animateBeetle(int currentBeetleY, int increment) {
		
		fadeIn.setFromValue(0.0f);
		fadeIn.setToValue(1.0f);
		fadeIn.setCycleCount(1);
		fadeIn.setAutoReverse(false);
		
		goUp.setFromY(currentBeetleY);
		goUp.setToY(currentBeetleY - increment);
		goUp.setAutoReverse(true);
		
		rotateTransition.setByAngle(180f);
		rotateTransition.setAutoReverse(true);
		
		goDown.setFromY(currentBeetleY - increment);
		goDown.setToY(currentBeetleY);
		goDown.setAutoReverse(true);
		
		rotateTransition2.setByAngle(180f);
		rotateTransition2.setAutoReverse(true);

		sequentialOngoing.setCycleCount(Timeline.INDEFINITE);
		
		sequentialTransition.play();
		

	}
	
	public void stopBeetle() {
		sequentialTransition.stop();
	}
	
	public void fadeBeetle(BeetleView beetleview) {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), beetleview);
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

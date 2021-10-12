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
import model.DementorModel;
import model.GameModel;
import model.TileModel;

public class DementorView extends ImageView {
	private Image dementorImage = new Image("file:img/dementor2.png");
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

	public DementorView(DementorModel beetle) {
		int tileSize = TileModel.getTileSize();
		
		this.setImage(dementorImage);
		this.setFitWidth(tileSize);
		this.setFitHeight(tileSize);
		setFocusTraversable(true);

		int dementorX = beetle.getX();
		int dementorY = beetle.getY();
		int increment = tileSize * 2;
		int currentdementorX = dementorX  * tileSize;
		int currentdementorY = dementorY * tileSize;

		setTranslateX(currentdementorX);
		setTranslateY(currentdementorY);
		

		animateBeetle(currentdementorY, increment);
	}
	
	private void animateBeetle(int currentdementorY, int increment) {
		
		fadeIn.setFromValue(0.0f);
		fadeIn.setToValue(0.6f);
		fadeIn.setCycleCount(1);
		fadeIn.setAutoReverse(false);
		
		goUp.setFromY(currentdementorY);
		goUp.setToY(currentdementorY - increment);
		goUp.setAutoReverse(true);
		
		rotateTransition.setByAngle(180f);
		rotateTransition.setAutoReverse(true);
		
		goDown.setFromY(currentdementorY - increment);
		goDown.setToY(currentdementorY);
		goDown.setAutoReverse(true);
		
		rotateTransition2.setByAngle(180f);
		rotateTransition2.setAutoReverse(true);

		sequentialOngoing.setCycleCount(Timeline.INDEFINITE);
		
		sequentialTransition.play();
		

	}
	
	public void stopBeetle() {
		sequentialTransition.stop();
	}
	
	public void fadeBeetle(DementorView beetleview) {
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

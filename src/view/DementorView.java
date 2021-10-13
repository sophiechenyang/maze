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
	private int dementorX;
	private int dementorY;
	
	public DementorView(DementorModel dementor) {
		int tileSize = TileModel.getTileSize();
		
		this.setImage(dementorImage);
		this.setFitWidth(tileSize);
		this.setFitHeight(tileSize);
		setFocusTraversable(true);

		dementorX = dementor.getX();
		dementorY = dementor.getY();
		int increment = tileSize;
		//int increment = tileSize * 2;
		int currentdementorX = dementorX  * tileSize;
		int currentdementorY = dementorY * tileSize;

		setTranslateX(currentdementorX);
		setTranslateY(currentdementorY);
		

		animateDementor(dementor, currentdementorY, increment);
	}
	
	private void animateDementor(DementorModel dementor, int currentdementorY, int increment) {
		
		fadeIn.setFromValue(0.0f);
		fadeIn.setToValue(0.6f);
		fadeIn.setCycleCount(1);
		
		goUp.setToY(currentdementorY - increment);
		dementor.setY(dementorY - 1);
		
		rotateTransition.setByAngle(180f);
		
		goDown.setToY(currentdementorY);
		dementor.setY(dementorY);
		
		rotateTransition2.setByAngle(180f);

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

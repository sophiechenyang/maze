package view;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.GameModel;
import model.ManaModel;

public class ManaView extends ImageView {
	private Image manaImage = new Image("file:img/mana.png");
	private int imageSize = 30;
	private int currentManaX;
	private int currentManaY;

	int tileSize = GameModel.getTileSize();
	int offset = ( tileSize - imageSize ) / 2;
	
	public ManaView(ManaModel treasure) {
		this.setImage(manaImage);
		this.setFitWidth(imageSize);
		this.setFitHeight(imageSize);
		
		int treasureX = treasure.getX();
		int treasureY = treasure.getY();
		
		currentManaX = offset + treasureX * tileSize;
		currentManaY = offset + treasureY * tileSize;
		
		setFocusTraversable(true);
		
		setTranslateX(currentManaX);
		setTranslateY(currentManaY);
		
	}

	public void setPlayerHandler(EventHandler<MouseEvent> detectDrag) {
		this.setOnDragDetected(detectDrag);
	}
	
	public void hoverHandler(EventHandler<MouseEvent> detectHover) {
		this.setOnMouseEntered(detectHover);
	}
	
	public void collectTreasure() {
		
		int newX = GameModel.getColumns() * tileSize;
		
		TranslateTransition moveTreasure = new TranslateTransition(Duration.millis(500), this);
		moveTreasure.setFromY(currentManaY);
		moveTreasure.setToY(0);
		moveTreasure.setFromX(currentManaX);
		moveTreasure.setToX(newX);
		moveTreasure.setCycleCount(1);
		
		FadeTransition fadeOut = new FadeTransition(Duration.millis(500), this);
		fadeOut.setFromValue(1.0f);
		fadeOut.setToValue(0.0f);
		fadeOut.setCycleCount(1);
		
		ParallelTransition collectJewel = new ParallelTransition(
			moveTreasure, fadeOut
		);
		
		
		collectJewel.play();
	}

}

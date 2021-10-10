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
import model.TreasureModel;

public class TreasureView extends ImageView {
	private Image treasureImage = new Image("file:img/gem.png");
	private int imageSize = 30;
	private int currentTreasureX;
	private int currentTreasureY;

	int tileSize = GameModel.getTileSize();
	int offset = ( tileSize - imageSize ) / 2;
	
	public TreasureView(TreasureModel treasure) {
		this.setImage(treasureImage);
		this.setFitWidth(imageSize);
		this.setFitHeight(imageSize);
		
		int treasureX = treasure.getX();
		int treasureY = treasure.getY();
		
		currentTreasureX = offset + treasureX * tileSize;
		currentTreasureY = offset + treasureY * tileSize;
		
		setFocusTraversable(true);
		
		setTranslateX(currentTreasureX);
		setTranslateY(currentTreasureY);
		
	}

	public void setPlayerHandler(EventHandler<MouseEvent> detectDrag) {
		this.setOnDragDetected(detectDrag);
	}
	
	public void collectTreasure() {
		
		int newX = GameModel.getColumns() * tileSize;
		
		TranslateTransition moveTreasure = new TranslateTransition(Duration.millis(500), this);
		moveTreasure.setFromY(currentTreasureY);
		moveTreasure.setToY(0);
		moveTreasure.setFromX(currentTreasureX);
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

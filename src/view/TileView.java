package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.TileModel;

public class TileView extends ImageView {
	private int tileSize = GameModel.getTileSize();
	private Image tileImage = new Image("file:img/tile2.png");
	private Image grassTile = new Image("file:img/tile.png");
	private Image wandImage = new Image("file:img/wand.png");
	private Image snakeImage = new Image("file:img/snake.png");
	private Image rotateImage = new Image("file:img/book.png");
	private Image swipeImage = new Image("file:img/bush.png");
	private Image voldemortImage = new Image("file:img/voldemort.png");
	private Image hallowsImage = new Image("file:img/hallows.png");

	private int tileType;

	public TileView(TileModel tileModel, int tileType) {
		this.tileType = tileType;

		if (tileType == 1) {
			this.setImage(grassTile);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		} else if (tileType == 3) {
			this.setImage(snakeImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		} else if (tileType == 4) {
			this.setImage(wandImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		} else if (tileType == 5) {
			this.setImage(rotateImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		} else if (tileType == 6) {
			this.setImage(swipeImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		}else if (tileType == 7) {
			this.setImage(voldemortImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		}else if (tileType == 8) {
			this.setImage(tileImage);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		}else if (tileType == 9) {
			this.setImage(hallowsImage);

			this.setFitWidth(tileSize-5);
			this.setFitHeight(tileSize-5);
		}
		

		setTranslateX(tileModel.getX() * tileSize);
		setTranslateY(tileModel.getY() * tileSize);

	}
	
	public int getTileType() {
		return tileType;
	}

	public void setPlayerHandler(EventHandler<MouseEvent> listenForClick) {
		this.setOnMouseClicked(listenForClick);
	}
	
	public void updateTileView() {
		this.setImage(null);
	}
	
}

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
	private Image tileImage = new Image("file:img/tile.png");
	private Image grassTile = new Image("file:img/grass_tile.png");
	private Image wandImage = new Image("file:img/wand.png");
	private Image snakeImage = new Image("file:img/nagini_tile.jpg");

	private int tileType;

	public TileView(TileModel tileModel, int tileType) {

		if (tileType == 1) {
			this.setImage(grassTile);

			this.setFitWidth(tileSize);
			this.setFitHeight(tileSize);
		} else if (tileType == 0) {
			this.setImage(tileImage);

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
		}

		setTranslateX(tileModel.getX() * tileSize);
		setTranslateY(tileModel.getY() * tileSize);

	}

	public void setPlayerHandler(EventHandler<MouseEvent> listenForClick) {
		this.setOnMouseClicked(listenForClick);
	}
	
}

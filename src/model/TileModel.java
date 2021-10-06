package model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileModel extends StackPane {
	private int x, y;
	private static final int TILE_SIZE = 30;

	private Rectangle tileColor = new Rectangle(TILE_SIZE, TILE_SIZE);

	public TileModel(int x, int y) {
		this.x = x;
		this.y = y;

		getChildren().add(tileColor);

		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);
	}
	
	public void setColor(int i) {
		if (i == 1) {
			tileColor.setFill(Color.GREEN);
		} else if (i == 0) {
			tileColor.setFill(Color.BEIGE);
		} else {
			tileColor.setFill(Color.YELLOW);
		}

	}

}

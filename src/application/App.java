package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.Point;

public class App extends Pane {

	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int DOWN = 4;

	private static final int TILE_SIZE = 30;
	private static final int X_TILES = 15;
	private static final int Y_TILES = 10;

	private Tile[][] grid = new Tile[X_TILES][Y_TILES];

	private int[][] maze = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 } };

	public App() {
		initUI();
	}

	private void initUI() {

		Pane background = new Pane();

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				Tile tile = new Tile(x, y);

				grid[x][y] = tile;
				int colorIndex = maze[y][x];
				tile.setColor(colorIndex);

				background.getChildren().add(tile);

			}
		}

		Player player = new Player(1, 9);

		player.setOnKeyPressed(e -> {
			KeyCode code = e.getCode();
			if (code == KeyCode.RIGHT && maze[player.y][player.x+1] == 0 ) {
				player.x += 1;
				player.setTranslateX(player.x * TILE_SIZE);

			} else if (code == KeyCode.LEFT && maze[player.y][player.x-1] == 0 ) {
				player.x -= 1;
				player.setTranslateX(player.x * TILE_SIZE);
			} else if (code == KeyCode.UP && maze[player.y-1][player.x] == 0 ) {
				player.y -= 1;
				player.setTranslateY(player.y * TILE_SIZE);
			} else if (code == KeyCode.DOWN && maze[player.y+1][player.x] == 0) {
				player.y += 1;
				player.setTranslateY(player.y * TILE_SIZE);
			} else {
				return;
				//System.out.println(player.x);
				//System.out.println(player.y);
			}
		});

		background.getChildren().add(player);

		getChildren().add(background);

	}

	public class Player extends Button {
		//Rectangle playerRect = new Rectangle(TILE_SIZE+5, TILE_SIZE);
		private int x, y;

		public Player(int x, int y) {
			this.x = x;
			this.y = y;
			//getChildren().add(playerRect);

			setTranslateX(x * TILE_SIZE);
			setTranslateY(y * TILE_SIZE);
		}

	}

	public class Tile extends StackPane {
		private int x, y;

		private Rectangle tileColor = new Rectangle(TILE_SIZE, TILE_SIZE);

		public Tile(int x, int y) {
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
}

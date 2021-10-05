package view;

import controller.PlayerController;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import model.GameModel;
import model.PlayerModel;
import model.TileView;

public class GameView {

	private int[][] maze = GameModel.getMaze();
	private int x_tiles = GameModel.getX_TILES();
	private int y_tiles = GameModel.getY_TILES();
	
	PlayerModel player = new PlayerModel(1,9);
	PlayerView playerview = new PlayerView(player);
	PlayerController playercontroller = new PlayerController(playerview, player);
	
	private TileView[][] grid = new TileView[x_tiles][y_tiles];

	public Parent createContent() {
		Pane rootpane = new Pane();
		createMaze(rootpane);
		
		rootpane.getChildren().add(playerview);	

		return rootpane;
	}
	
	public void createMaze(Pane pane) {

		for (int y = 0; y < y_tiles; y++) {
			for (int x = 0; x < x_tiles; x++) {
				TileView tile = new TileView(x, y);

				grid[x][y] = tile;
				int colorIndex = getMaze()[y][x];
				tile.setColor(colorIndex);

				pane.getChildren().add(tile);
			}
		}
	}

	public int[][] getMaze() {
		return maze;
	}

}

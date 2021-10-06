package view;

import controller.PlayerController;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.WandModel;

public class GameView {

	GameModel game = new GameModel();
	private int[][] maze = GameModel.getMaze();
	private int x_tiles = GameModel.getX_TILES();
	private int y_tiles = GameModel.getY_TILES();
	
	PlayerModel player = new PlayerModel(1,9);
	PlayerView playerview = new PlayerView(player);
	PlayerController playercontroller = new PlayerController(playerview, player, game);
	
	WandModel wand = new WandModel(5,1);
	WandView wandview = new WandView(wand);
	
	private TileModel[][] grid = new TileModel[x_tiles][y_tiles];

	public Parent createContent() {
		Pane rootpane = new Pane();
		createMaze(rootpane);
				
		rootpane.getChildren().add(playerview);
		rootpane.getChildren().add(wandview);
		System.out.println(maze[player.getY()][player.getX()]);
		
		winMessage();

		return rootpane;
	}
	
	public void createMaze(Pane pane) {

		for (int y = 0; y < y_tiles; y++) {
			for (int x = 0; x < x_tiles; x++) {
				TileModel tile = new TileModel(x, y);

				grid[x][y] = tile;
				int colorIndex = maze[y][x];
				tile.setColor(colorIndex);

				pane.getChildren().add(tile);
			}
		}
	}
	
	public void winMessage() {
		if (game.isGameWon())
			System.out.println("game is won!");
	}



}

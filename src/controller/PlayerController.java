package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.PlayerModel;
import model.GameModel;
import view.PlayerView;

public class PlayerController {
	
	private PlayerView view;
	private PlayerModel model;
	private GameModel gameModel;
	//private GameController gameController;
	private int moveSize = GameModel.getTileSize();
	private int[][] maze = GameModel.getMaze();
	private boolean hasWand = false;
	
	public PlayerController(PlayerView view, PlayerModel model, GameModel game) {
		this.view = view;
		this.model = model;
		this.gameModel = game;
		
		this.view.setPlayerHandler(new MovePlayer());
	}

	class MovePlayer implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
			System.out.println("hi");
			KeyCode code = e.getCode();
			int currentPlayerX = model.getX();
			int currentPlayerY = model.getY();

			if (code == KeyCode.RIGHT && maze[currentPlayerY][currentPlayerX+1] !=1) {
				
				model.setX(currentPlayerX + 1);
				view.moveX(model.getX() * moveSize);

			} else if (code == KeyCode.LEFT && maze[currentPlayerY][currentPlayerX-1] !=1) {
				model.setX(currentPlayerX - 1);
				view.moveX(model.getX() * moveSize);
				
			} else if (code == KeyCode.UP && maze[currentPlayerY - 1][currentPlayerX] !=1) {
				model.setY(currentPlayerY - 1);
				view.moveY(model.getY() * moveSize);
				
			} else if (code == KeyCode.DOWN  && maze[currentPlayerY + 1][currentPlayerX] !=1) {
				model.setY(currentPlayerY + 1);
				view.moveY(model.getY()* moveSize);
				
			} else {
				return;
			}
			
			if (maze[currentPlayerY][currentPlayerX] == 3) {
				gameModel.setGameWon(true);
				System.out.println("Game won:" + gameModel.isGameWon());
			}
			
//			if (currentPlayerX == WandModel.getX() && currentPlayerY == WandModel.getY())
//				hasWand = true;
//				System.out.println("Has wand:" + hasWand);
//				//gameController.launchSnakeScene();

		}
	}

}
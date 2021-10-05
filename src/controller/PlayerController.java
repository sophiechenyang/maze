package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.PlayerModel;
import model.GameModel;
import view.PlayerView;

public class PlayerController {
	
	private PlayerView view;
	private PlayerModel model;
	private int moveSize = GameModel.getTileSize();
	private int[][] maze = GameModel.getMaze();
	
	public PlayerController(PlayerView view, PlayerModel model) {
		this.view = view;
		this.model = model;
		
		this.view.setPlayerHandler(new MovePlayer());
	}

	class MovePlayer implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
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
				System.out.println("game is won!");
			}

		}
	}

}
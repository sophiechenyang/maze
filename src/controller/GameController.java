package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.BeetleModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.TreasureModel;
import view.BeetleView;
import view.GameView;
import view.PlayerView;
import view.TileView;
import view.TreasureView;

public class GameController {
	private GameView gameView = new GameView();
	private GameModel gameModel = new GameModel();
	private PlayerModel player;
	private PlayerView playerView;
	private int[][] maze = gameModel.getMaze();
	Timer timer;
	private int tileSize = gameModel.getTileSize();

	public GameController() {
		startGame();
		
		gameView.setKeyPressHandler(new activateAmulet());
	}

	public void startGame() {
		timer = new Timer();
		//timer.schedule(new RemindTask(), 0, 8000);
		//timer.schedule(new CheckWin(), 0, 100);
		gameModel.setGameActive(true);

		for (int y = 0; y < GameModel.getRows(); y++) {
			for (int x = 0; x < GameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile, maze[y][x]);
				if (tile.isHasTreasure() && maze[y][x] == 0)
					createTreasure(x, y);
			}
		}

		createPlayer();

	}

	class RemindTask extends TimerTask {

		ArrayList<BeetleModel> beetleList = gameModel.getBeetleList();

		public void run() {
			Platform.runLater(() -> {
				int beetleCount = beetleList.size();

				if (beetleCount < 20) {
					createBeetle();
				} else {
					setGameOver();
				}
			});
		}
	}
	
	class CheckWin extends TimerTask {

		public void run() {
			Platform.runLater(() -> {
				
				System.out.println("player location is X:" + player.getX() + "Y: " + player.getY());
				
			});
		}
	}

	public void createPlayer() {
		player = new PlayerModel(2);
		playerView = gameView.createPlayer(player);
	}

	public void createBeetle() {
		BeetleModel beetle = gameModel.createBeatle();
		BeetleView beetleView = gameView.createBeatle(beetle, gameModel);
		BeetleController beetleController = new BeetleController(beetle, beetleView, gameModel, this);
	}

	void createTreasure(int x, int y) {
		TreasureModel treasure = gameModel.createTreasure(x, y);
		TreasureView treasureView = gameView.createTreasure(treasure);
		TreasureController treasureController = new TreasureController(treasure, treasureView, gameModel, gameView);
	}

	class resetGameEvent implements EventHandler<MouseEvent> {
		public void handle(MouseEvent e) {
			resetGame();
			e.consume();
		}
	}

	class activateAmulet implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();

			int currentPlayerX = player.getX();
			int currentPlayerY = player.getY();

			if (code == KeyCode.RIGHT && maze[currentPlayerY][currentPlayerX + 1] != 1) {

				player.setX(currentPlayerX + 1);
				playerView.moveX(player.getX() * tileSize);

			} else if (code == KeyCode.LEFT && maze[currentPlayerY][currentPlayerX - 1] != 1) {
				player.setX(currentPlayerX - 1);
				playerView.moveX(player.getX() * tileSize);

			} else if (code == KeyCode.UP && maze[currentPlayerY - 1][currentPlayerX] != 1) {
				player.setY(currentPlayerY - 1);
				playerView.moveY(player.getY() * tileSize);

			} else if (code == KeyCode.DOWN && maze[currentPlayerY + 1][currentPlayerX] != 1) {
				player.setY(currentPlayerY + 1);
				playerView.moveY(player.getY() * tileSize);

			} else {
				return;
			}
			
			if (player.getX() == 1 && player.getY() == 7) {
				gameModel.setGameWon(true);
				System.out.println("Game won:" + gameModel.isGameWon());
			}
			
			if (maze[player.getY()][player.getX()] == 3 && gameModel.isSnakeDefeated() ==false ) {
				Main.launchSnakeScene(gameModel);
			} 
			
			if (maze[player.getY()][player.getX()] == 4 && gameModel.isWandRetrieved() ==false ) {
				Main.launchWandScene(gameModel);
			}
		}
	}

	public GameView getGameView() {
		return gameView;
	}


	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void updateBeetleScore() {
		gameModel.increaseGamePointsBy(5);
		gameView.updateScore(gameModel);
	}

	public void setGameWon() {
		if (gameModel.isGameWon() == false && gameModel.isGameOver() == false) {
			gameModel.setGameWon(true);
			gameModel.setGameActive(false);
			gameView.setWinView();
			timer.cancel();
		}
	}

	public void setGameOver() {
		if (gameModel.isGameWon() == false && gameModel.isGameOver() == false) {
			gameModel.setGameOver(true);
			gameModel.setGameActive(false);
			gameView.setLostView();
			timer.cancel();
		}
	}

	public void resetGame() {
		timer.cancel();
		gameView.reset();
		gameModel.reset();
		startGame();
	}

}

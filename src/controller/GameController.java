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
import model.DementorModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.ManaModel;
import view.DementorView;
import view.GameView;
import view.PlayerView;
import view.TileView;
import view.ManaView;

public class GameController {
	private GameView gameView = new GameView();
	private GameModel gameModel = new GameModel();
	private PlayerModel playerModel;
	private PlayerView playerView;
	private int[][] maze = gameModel.getMaze();
	Timer timer;
	private int tileSize = gameModel.getTileSize();
	
	public GameController(boolean difficulty, int playerType) {
		gameView.setKeyPressHandler(new events());
		gameModel.setPlayerType(playerType);
		gameModel.isAdvanced();
		startGame();
	}

	public void startGame() {
		timer = new Timer();
		//timer.schedule(new RemindTask(), 0, 10000);
		//timer.schedule(new CheckWin(), 0, 100);
		gameModel.setGameActive(true);
		
		createPlayer(gameModel.getPlayerType());

		for (int y = 0; y < GameModel.getRows(); y++) {
			for (int x = 0; x < GameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile, maze[y][x]);
				if (tile.isHasTreasure() && maze[y][x] == 0)
					createTreasure(x, y);
			}
		}

		

	}

	class RemindTask extends TimerTask {

		ArrayList<DementorModel> beetleList = gameModel.getBeetleList();

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
				
				System.out.println("player location is X:" + playerModel.getX() + "Y: " + playerModel.getY());
				
			});
		}
	}

	public void createPlayer(int playerType) {
		playerModel = gameModel.createPlayer(playerType);
		playerView = gameView.createPlayer(playerModel);
	}

	public void createBeetle() {
		DementorModel dementorModel = gameModel.createBeatle();
		DementorView dementorView = gameView.createDementor(dementorModel, gameModel);
		DementorController dementorController = new DementorController(dementorModel, dementorView, gameModel, this);
	}

	void createTreasure(int x, int y) {
		ManaModel treasure = gameModel.createTreasure(x, y);
		ManaView manaView = gameView.createTreasure(treasure);
		ManaController manaController = new ManaController(treasure, manaView, gameModel, gameView, playerModel);
	}

	class resetGameEvent implements EventHandler<MouseEvent> {
		public void handle(MouseEvent e) {
			resetGame();
			e.consume();
		}
	}

	class events implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();

			int currentPlayerX = playerModel.getX();
			int currentPlayerY = playerModel.getY();

			if (code == KeyCode.RIGHT && maze[currentPlayerY][currentPlayerX + 1] != 1) {

				playerModel.setX(currentPlayerX + 1);
				playerView.moveX(playerModel.getX() * tileSize);

			} else if (code == KeyCode.LEFT && maze[currentPlayerY][currentPlayerX - 1] != 1) {
				playerModel.setX(currentPlayerX - 1);
				playerView.moveX(playerModel.getX() * tileSize);

			} else if (code == KeyCode.UP && maze[currentPlayerY - 1][currentPlayerX] != 1) {
				playerModel.setY(currentPlayerY - 1);
				playerView.moveY(playerModel.getY() * tileSize);

			} else if (code == KeyCode.DOWN && maze[currentPlayerY + 1][currentPlayerX] != 1) {
				playerModel.setY(currentPlayerY + 1);
				playerView.moveY(playerModel.getY() * tileSize);

			} else {
				return;
			}
			
			if (playerModel.getX() == 1 && playerModel.getY() == 7) {
				gameModel.setGameWon(true);
				System.out.println("Game won:" + gameModel.isGameWon());
			}
			
			if (maze[playerModel.getY()][playerModel.getX()] == 3 && !gameModel.isSnakeDefeated()) {
				Main.launchSnakeScene(gameModel);
			} 
			
			if (maze[playerModel.getY()][playerModel.getX()] == 4 && !gameModel.isWandRetrieved()) {
				Main.launchWandScene(gameModel);
			}
			
			if (maze[playerModel.getY()][playerModel.getX()] == 5 && !gameModel.isCodeRetrieved()) {
				Main.launchSafeScene(gameModel);
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

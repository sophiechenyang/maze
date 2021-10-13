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
	private ArrayList<DementorModel> dementorList = gameModel.getDementorList();
	private ArrayList<TileView> tileViewList = gameView.getTileViewList();
	
	public GameController(boolean difficulty, int playerType) {
		gameView.setKeyPressHandler(new events());
		gameModel.setPlayerType(playerType);
		gameModel.isAdvanced();
		
		startGame();
	}

	public void startGame() {
		timer = new Timer();
		
		//timer.schedule(new CheckWin(), 0, 100);
		gameModel.setGameActive(true);
		
		//createPlayer(gameModel.getPlayerType());
		
		playerModel = gameModel.createPlayer(gameModel.getPlayerType());

		for (int y = 0; y < GameModel.getRows(); y++) {
			for (int x = 0; x < GameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile, maze[y][x]);
				if (tile.isHasTreasure() && maze[y][x] == 0)
					createMana(x, y);
			}
		}
		
		playerView = gameView.createPlayer(playerModel); // This is called after jewels and tiles so player display on top 

		timer.schedule(new RemindTask(), 0, 10000);

	}

	class RemindTask extends TimerTask {

		public void run() {
			Platform.runLater(() -> {
				int dementorCount = dementorList.size();

				if (dementorCount < 20) {
					createDementor();
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

	public void createDementor() {
		DementorModel dementorModel = gameModel.createDementor();
		DementorView dementorView = gameView.createDementor(dementorModel, gameModel);
		DementorController dementorController = new DementorController(dementorModel, dementorView, gameModel, gameView, this, playerModel);
	}

	void createMana(int x, int y) {
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
			
			if (playerModel.getX() == 13 && playerModel.getY() == 10) {
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
			
			if (maze[playerModel.getY()][playerModel.getX()] == 6 && !gameModel.isClearedDementors()) {
				Main.launchSwipeScene(gameModel);
			}
			
			if (maze[playerModel.getY()][playerModel.getX()] == 7) {
				Main.launchVoldemortScene(gameModel);
			}
			
			
			// TO DO: fix this to refer to the square that has the 3
			if (gameModel.isSnakeDefeated()) {
				
				//TileView tile = tileViewList[2][3];
				
				System.out.println(gameModel.isSnakeDefeated());
				tileViewList.forEach((tileView) -> {
					
					// 
					
					// get tileModel of the tile that has the 3 in the maze, and turn off it's tile view
//					if (maze[playerModel.getY()][playerModel.getX()]==3) {
//						tileView.setImage(null);
//					}
				});
			}
				
				
			//decrease health when running into dementor
			for (int i =0; i< dementorList.size(); i++) {
				DementorModel dementor = dementorList.get(i);
				if (dementor.getX() == playerModel.getX() && dementor.getY() == playerModel.getY()) {
					System.out.println("caught by dementor");
					playerModel.reduceHealth(5);
					gameView.updatePlayerStats(playerModel);
				}
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

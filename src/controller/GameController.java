package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
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
	private int[][] maze = GameModel.getMaze();
	Timer timer;
	private int tileSize = GameModel.getTileSize();
	private ArrayList<DementorModel> dementorList = gameModel.getDementorList();
	private int maxDementor;
	private int timeLeft;

	public GameController(boolean difficulty, int playerType) {
		gameView.setKeyPressHandler(new events());
		gameModel.setPlayerType(playerType);
		gameModel.setAdvanced(difficulty);
		difficultySetting();
		startGame();
	}

	public void startGame() {
		timer = new Timer();

		timer.schedule(new CheckWin(), 0, 1000);
		gameModel.setGameActive(true);

		playerModel = gameModel.createPlayer(gameModel.getPlayerType());

		for (int y = 0; y < GameModel.getRows(); y++) {
			for (int x = 0; x < GameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile, maze[y][x]);
				if (tile.isHasTreasure() && maze[y][x] == 0)
					createMana(x, y);
			}
		}

		playerView = gameView.createPlayer(playerModel); // This is called after jewels and tiles so player display on
															// top

		timer.schedule(new RemindTask(), 0, 10000);

	}

	public void difficultySetting() {
		if (gameModel.isAdvanced()) {
			maxDementor = 10;
			timeLeft = 300;
		} else {
			maxDementor = 20;
			timeLeft = 500;
		}
	}

	class RemindTask extends TimerTask {

		public void run() {
			Platform.runLater(() -> {
				int dementorCount = dementorList.size();

				if (dementorCount < maxDementor) {
					createDementor();
				}

				if (dementorCount >= maxDementor) {
					setGameOver();
				}
			});
		}
	}

	class CheckWin extends TimerTask {

		public void run() {
			Platform.runLater(() -> {
				timeLeft--;
				gameView.updateTime(timeLeft);
				if (timeLeft <= 0) {
					setGameOver();
				}

				if (playerModel.getHealth() <= 0) {
					setGameOver();
				}

				checkDementorCollision();

			});
		}
	}

	public void createPlayer(int playerType) {
		playerModel = gameModel.createPlayer(playerType);
		playerView = gameView.createPlayer(playerModel);
	}

	public void createDementor() {
		DementorModel dementorModel = gameModel.createDementor();
		DementorView dementorView = gameView.createDementor(dementorModel, this);
		DementorController dementorController = new DementorController(dementorModel, dementorView, gameModel, gameView,
				this, playerModel);
	}

	void createMana(int x, int y) {
		ManaModel mana = gameModel.createTreasure(x, y);
		ManaView manaView = gameView.createMana(mana);
		ManaController manaController = new ManaController(mana, manaView, gameModel, gameView, playerModel);
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

			}

			if (maze[playerModel.getY()][playerModel.getX()] == 3 && !gameModel.isSnakeDefeated()) {
				Main.launchSnakeScene(gameModel, gameView);
			} else if (maze[playerModel.getY()][playerModel.getX()] == 4 && !gameModel.isWandRetrieved()) {
				Main.launchWandScene(gameModel, gameView);
			} else if (maze[playerModel.getY()][playerModel.getX()] == 5 && !gameModel.isCodeRetrieved()) {
				Main.launchSafeScene(gameModel, gameView);
			} else if (maze[playerModel.getY()][playerModel.getX()] == 6 && !gameModel.isClearedDementors()) {
				Main.launchSwipeScene(gameModel, gameView);
			} else if (maze[playerModel.getY()][playerModel.getX()] == 7 && !gameModel.isVoldemortDefeated()) {
				Main.launchVoldemortScene(gameModel, gameView);
			} else if (maze[playerModel.getY()][playerModel.getX()] == 8 && gameModel.isVoldemortDefeated()) {
				setGameWon();
			}

			if (maze[playerModel.getY()][playerModel.getX()] == 9) {
				if (!gameModel.isSnakeDefeated() || !gameModel.isWandRetrieved() || !gameModel.isCodeRetrieved()) {
					Main.launchReadyScene(gameModel, gameView);
				}
			}

//			if (maze[playerModel.getY()][playerModel.getX()] == 9 && !gameModel.isSnakeDefeated() || !gameModel.isWandRetrieved() || !gameModel.isCodeRetrieved()) {
//				Main.launchReadyScene(gameModel,gameView);
//			}

			if (gameModel.isSnakeDefeated() && gameModel.isWandRetrieved() && gameModel.isCodeRetrieved()
					&& !gameModel.isShowReady()) {
				gameModel.setShowReady(true);
				gameView.readyForVoldemort();
			}

			// hide snake after it has been defeated
			if (gameModel.isSnakeDefeated()) {
				TileView tileView = gameView.getTileOfType(3);
				tileView.setImage(null);
			}

			// hide wand after it has been retrieved
			if (gameModel.isWandRetrieved()) {
				TileView tileView = gameView.getTileOfType(4);
				tileView.setImage(null);
			}

			// hide book after code has been retrieved
			if (gameModel.isCodeRetrieved()) {
				TileView tileView = gameView.getTileOfType(5);
				tileView.setImage(null);
			}

			// hallows turns yellow when player is ready to defeat voldemort
			if (gameModel.isSnakeDefeated() && gameModel.isWandRetrieved() && gameModel.isCodeRetrieved()) {
				TileView tileView = gameView.getTileOfType(9);
				Image hallowsActivatedImage = new Image("file:img/hallows_activated.png");
				tileView.setImage(hallowsActivatedImage);
			}

			// hide Voldemort after it has been defeated
			if (gameModel.isVoldemortDefeated()) {
				TileView tileView = gameView.getTileOfType(7);
				tileView.setImage(null);
			}

			// decrease health when running into dementor
			checkDementorCollision();

		}
	}

	public void checkDementorCollision() {
		for (int i = 0; i < dementorList.size(); i++) {
			DementorModel dementor = dementorList.get(i);
			if (dementor.getX() == playerModel.getX() && dementor.getY() == playerModel.getY()) {
				if (playerModel.getHealth() > 0 && !playerModel.isDamagedTaken()) {
					playerModel.reduceHealth(5);
					playerModel.setDamagedTaken(true);
					gameView.updatePlayerStats(playerModel);
				} else if (playerModel.getHealth() == 0) {
					setGameOver();
				}
			} else {
				playerModel.setDamagedTaken(false);
			}
		}
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void updateScore(int score) {
		gameModel.increaseGamePointsBy(score);
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
	}
}

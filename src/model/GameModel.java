package model;

import java.util.ArrayList;

import controller.GameController;
import javafx.scene.Parent;

public class GameModel extends Parent {
	private static final int ROWS = 10;
	private static final int COLUMNS = 15;
	private static final int TILE_SIZE = 50;
	private int gamePoints = 0;
	private TileModel[][] grid = new TileModel[COLUMNS][ROWS] ;
	
	private static int[][] MAZE = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 1, 4, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 3, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1 } };

	private int numOfTiles = ROWS * COLUMNS;
	private boolean gameWon = false;
	private boolean gameOver = false;
	private boolean gameActive = false;
	private ArrayList<BeetleModel> beetleList = new ArrayList<BeetleModel>(); 
	
	// microgames
	private boolean snakeDefeated = false;
	private boolean wandRetrieved = false;
	
	public BeetleModel createBeatle() {
		BeetleModel beetle = new BeetleModel(generateRand(), generateRand());
		beetleList.add(beetle);
		return beetle;
	}

	public TreasureModel createTreasure(int x, int y) {
		TreasureModel treasure = new TreasureModel(x, y);
		return treasure;
	}
	
	public TileModel createTile(int x, int y) {
		TileModel tile = new TileModel(x,y,Math.random()<0.2);
		grid[x][y] = tile;
		return tile;
	}

	public static int getRows() {
		return ROWS;
	}

	public static int getColumns() {
		return COLUMNS;
	}

	public static int getTileSize() {
		return TILE_SIZE;
	}

	public int getGamePoints() {
		return gamePoints;
	}

	public void increaseGamePointsBy(int increment) {
		this.gamePoints = this.gamePoints + increment;
	}
	
	
	public int getNumberOfTiles() {
		return numOfTiles;
	}
	
	public void reset() {
		beetleList.clear();
		gamePoints = 0;

		gameWon = false;
		gameOver = false;
		gameActive = false;		
	}
	
	public int generateRand() {
		int random = 2 + (int) (Math.random() * ((7 - 2) + 1));
		return random;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList getBeetleList() {
		return beetleList;
	}

	public void removeFromBeetleList(BeetleModel beetleModel) {
		beetleList.remove(beetleModel);
	}

	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	public static int[][] getMaze() {
		return MAZE;
	}

	public boolean isSnakeDefeated() {
		return snakeDefeated;
	}

	public void setSnakeDefeated(boolean snakeDefeated) {
		this.snakeDefeated = snakeDefeated;
	}

	public boolean isWandRetrieved() {
		return wandRetrieved;
	}

	public void setWandRetrieved(boolean wandRetrieved) {
		this.wandRetrieved = wandRetrieved;
	}


}

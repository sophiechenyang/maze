package model;

public class PlayerModel{
	protected int x = 1;
	protected int y = 9;
	private int playerIndex;
	protected int mana;
	private String playerURL;
	protected String[] houseList = {"Gryfindor", "Ravenclaw", "Hufflepuff", "Slytherin"};
	protected String house;
	protected String[] charList = {"Harry Potter", "Ron Weasley", "Hermione Granger", "Draco Malfoy"};
	protected String name;
	protected int health;
	
	public PlayerModel(int playerIndex) {
		this.setPlayerIndex(playerIndex);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getPlayerURL() {
		return playerURL;
	}

	public void setPlayerURL(String playerURL) {
		this.playerURL = playerURL;
	}
	
	public String getPlayerName() {
		return name;
	}
	
	public String getHouse() {
		return house;
	}
	
	public int getMana() {
		return mana;
	}

	public int getHealth() {
		return health;
	}
	
}

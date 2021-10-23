package model;

public class PlayerModel{
	protected int x = 1;
	protected int y = 9;
	private int playerIndex;
	protected int mana;
	private String playerURL;
	protected String[] houseList = {"Gryfindor", "Ravenclaw", "Hufflepuff", "Slytherin", "unknown"};
	protected String house;
	protected String[] charList = {"Harry Potter", "Ron Weasley", "Hermione Granger", "Draco Malfoy", "Dobby"};
	protected String name;
	protected int health;
	private boolean damagedTaken = false;
	
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
	
	public void increaseMana(int num) {
		mana = mana + num;
	}
	
	public void decreaseMana(int num) {
		mana = mana - num;
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
	
	public void reduceHealth(int num) {
		health = health - num;
	}

	public boolean isDamagedTaken() {
		return damagedTaken;
	}

	public void setDamagedTaken(boolean damagedTaken) {
		this.damagedTaken = damagedTaken;
	}
	
}

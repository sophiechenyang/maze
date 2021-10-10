package model;

public class PlayerModel{
	private int x = 1;
	private int y = 9;
	private int playerIndex;
	
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

	
}

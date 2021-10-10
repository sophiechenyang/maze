package model;

public class TileModel extends GameModel {
	private int x,y;
	private static final int TILE_SIZE = 40;
	private boolean hasTreasure;
	private boolean isOpen;
	
	public TileModel(int x, int y, boolean hasTreasure) {
		this.x = x;
		this.y = y;
		this.setHasTreasure(hasTreasure);
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

	public boolean isHasTreasure() {
		return hasTreasure;
	}

	public void setHasTreasure(boolean hasTreasure) {
		this.hasTreasure = hasTreasure;
	}
}

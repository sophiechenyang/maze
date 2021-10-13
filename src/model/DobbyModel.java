package model;

public class DobbyModel extends PlayerModel {

	public DobbyModel(int playerIndex) {
		super(playerIndex);
		mana = 0;
		setPlayerURL("file:img/dobby.png");
		house = houseList[4];
		name = charList[4];
		health = 60;
	}


}

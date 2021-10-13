package model;

public class RonModel extends PlayerModel {

	public RonModel(int playerIndex) {
		super(playerIndex);
		mana = 5;
		setPlayerURL("file:img/ron.png");
		house = houseList[0];
		name = charList[1];
		health = 70;
	}


}

package model;

public class HarryModel extends PlayerModel {

	public HarryModel(int playerIndex) {
		super(playerIndex);
		mana = 10;
		setPlayerURL("file:img/harry.png");
		house = houseList[0];
		name = charList[0];
	}


}

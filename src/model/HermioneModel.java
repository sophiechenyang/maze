package model;

public class HermioneModel extends PlayerModel {

	public HermioneModel(int playerIndex) {
		super(playerIndex);
		mana = 20;
		setPlayerURL("file:img/hermione.png");
		house = houseList[0];
		name = charList[2];
	}


}

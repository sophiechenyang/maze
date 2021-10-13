package model;

public class DracoModel extends PlayerModel {

	public DracoModel(int playerIndex) {
		super(playerIndex);
		mana = 15;
		setPlayerURL("file:img/draco.png");
		house = houseList[3];
		name = charList[3];
		health = 60;
	}


}

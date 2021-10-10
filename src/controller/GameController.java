package controller;

import model.GameModel;
import view.GameView;

public class GameController {
	GameView gameView = new GameView();
	GameModel gameModel = new GameModel();
	
	public GameController() {

	}
	
	public GameView getGameView() {
		return gameView;
	}

}

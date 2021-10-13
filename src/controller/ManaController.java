package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GameModel;
import model.ManaModel;
import model.PlayerModel;
import view.GameView;
import view.ManaView;

public class ManaController {
	private ManaModel manaModel;
	private ManaView manaView;
	private GameModel gameModel;
	private GameView gameView;
	private PlayerModel playerModel;

	public ManaController(ManaModel manaModel, ManaView manaView, GameModel gameModel, GameView gameView, PlayerModel playerModel) {
		this.manaModel = manaModel;
		this.manaView = manaView;
		this.gameModel = gameModel;
		this.gameView = gameView;
		this.playerModel = playerModel;
		this.manaView.setPlayerHandler(new detectDrag());
	}

	class detectDrag implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if (gameModel.isGameActive() == true) {
				manaView.setCursor(Cursor.CLOSED_HAND);
				manaView.collectTreasure();
				//gameModel.increaseGamePointsBy(50);
				//gameView.updateScore(gameModel);
				playerModel.increaseMana(5);
				gameView.updatePlayerStats(playerModel);
			}
		}
	}
}

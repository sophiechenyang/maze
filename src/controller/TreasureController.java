package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GameModel;
import model.TreasureModel;
import view.GameView;
import view.TreasureView;

public class TreasureController {
	private TreasureModel treasureModel;
	private TreasureView treasureView;
	private GameModel gameModel;
	private GameView gameView;

	public TreasureController(TreasureModel treasureModel, TreasureView treasureView, GameModel gameModel, GameView gameView) {
		this.treasureModel = treasureModel;
		this.treasureView = treasureView;
		this.gameModel = gameModel;
		this.gameView = gameView;
		this.treasureView.setPlayerHandler(new detectDrag());
	}

	class detectDrag implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if (gameModel.isGameActive() == true) {
				treasureView.setCursor(Cursor.CLOSED_HAND);
				treasureView.collectTreasure();
				gameModel.increaseGamePointsBy(50);
				gameView.updateScore(gameModel);
			}
		}
	}
}

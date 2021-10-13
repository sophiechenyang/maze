package controller;

import view.DementorView;
import view.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.DementorModel;
import model.GameModel;
import model.PlayerModel;

public class DementorController {
	private DementorView dementorView;
	private DementorModel dementorModel;
	private GameModel gameModel;
	private GameController gameController;
	private GameView gameView;
	private PlayerModel playerModel;

	public DementorController(DementorModel dementorModel, DementorView dementorView, GameModel gameModel, GameView gameView, GameController gameController, PlayerModel playerModel) {
		this.dementorView = dementorView;
		this.dementorModel = dementorModel;
		this.gameModel = gameModel;
		this.gameView = gameView;
		this.gameController = gameController;
		this.playerModel = playerModel;
		this.dementorView.setPlayerHandler(new clickBeetle());
		this.dementorView.setHoverHandler(new hoverDementor());

	}

	class clickBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			if (e.getClickCount() > 2) {
				//System.out.println(e);
				
				if (dementorModel.isDead())
					return;
				
				killDementor();
				
			} else if (e.getButton() == MouseButton.SECONDARY){
				dementorView.stopBeetle();
			}
		}
	}
	
	class hoverDementor implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
				dementorView.stopBeetle();
			//System.out.println(e);
			
		}
	}
	
	private void killDementor() {
		//beetleView.setImage(null);
		
		if (gameModel.isGameActive() && playerModel.getMana()> 0) {
			dementorView.fadeBeetle(this.dementorView);
			dementorModel.setDead(true);
			gameModel.removeFromBeetleList(dementorModel);
			gameController.updateBeetleScore();
			playerModel.decreaseMana(15);
			gameView.updatePlayerStats(playerModel);
		}
	}
	

}

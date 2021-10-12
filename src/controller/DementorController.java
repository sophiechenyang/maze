package controller;

import view.DementorView;
import view.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.DementorModel;
import model.GameModel;

public class DementorController {
	private DementorView dementorView;
	private DementorModel dementorModel;
	private GameModel gameModel;
	private GameController gameController;

	public DementorController(DementorModel dementorModel, DementorView dementorView, GameModel gameModel, GameController gameController) {
		this.dementorView = dementorView;
		this.dementorModel = dementorModel;
		this.gameModel = gameModel;
		this.gameController = gameController;
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
				
				killBeetle();
				
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
	
	private void killBeetle() {
		//beetleView.setImage(null);
		
		if (gameModel.isGameActive()) {
			dementorView.fadeBeetle(this.dementorView);
			dementorModel.setDead(true);
			gameModel.removeFromBeetleList(dementorModel);
			gameController.updateBeetleScore();
		}
	}
	

}

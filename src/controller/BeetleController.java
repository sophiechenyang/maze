package controller;

import view.BeetleView;
import view.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.BeetleModel;
import model.GameModel;

public class BeetleController {
	private BeetleView beetleView;
	private BeetleModel beetleModel;
	private GameModel gameModel;
	private GameController gameController;

	public BeetleController(BeetleModel beetleModel, BeetleView beetleView, GameModel gameModel, GameController gameController) {
		this.beetleView = beetleView;
		this.beetleModel = beetleModel;
		this.gameModel = gameModel;
		this.gameController = gameController;
		this.beetleView.setPlayerHandler(new clickBeetle());
		this.beetleView.setHoverHandler(new hoverBeetle());

	}

	class clickBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			if (e.getClickCount() > 2) {
				//System.out.println(e);
				
				if (beetleModel.isDead())
					return;
				
				killBeetle();
				
			} else if (e.getButton() == MouseButton.SECONDARY){
				beetleView.stopBeetle();
			}
		}
	}
	
	class hoverBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
				beetleView.stopBeetle();
				gameController.setGameOver();

			//System.out.println(e);
			
		}
	}
	
	private void killBeetle() {
		//beetleView.setImage(null);
		
		if (gameModel.isGameActive()) {
			beetleView.fadeBeetle(this.beetleView);
			beetleModel.setDead(true);
			gameModel.removeFromBeetleList(beetleModel);
			gameController.updateBeetleScore();
		}
	}
	

}

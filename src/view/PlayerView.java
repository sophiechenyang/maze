package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import model.PlayerModel;

public class PlayerView extends ImageView{
	private int tileSize = GameModel.getTileSize();

	public PlayerView(PlayerModel playerModel) {
//		if (playerModel.getPlayerIndex() == 1) {
//			this.setImage(harry);
//			
//		}	
		
		Image harry = new Image("file:img/harry.png");
		this.setImage(harry);
		this.setFitWidth(tileSize);
		this.setFitHeight(tileSize);
		
		setFocusTraversable(true);

		setTranslateX(playerModel.getX() * tileSize);
		setTranslateY(playerModel.getY() * tileSize);	
	}
	
	public void setPlayerHandler(EventHandler<KeyEvent> listenForKey) {
		this.setOnKeyPressed(listenForKey);
	}
	
	public void moveX(int x) {
		setTranslateX(x);
	}
	
	public void moveY(int y) {
		setTranslateY(y);
	}
}

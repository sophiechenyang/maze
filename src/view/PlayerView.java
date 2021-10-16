package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import model.PlayerModel;

public class PlayerView extends ImageView{
	private int tileSize = GameModel.getTileSize();
	Image playerImg;
	private String url;

	public PlayerView(PlayerModel playerModel) {
		
		url = playerModel.getPlayerURL();
		playerImg = new Image(url);
		
		this.setImage(playerImg);
				
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

package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.PlayerModel;

public class PlayerView extends ImageView{
	private Image harry = new Image("file:src/img/harry.png");
	
	public PlayerView(PlayerModel player) {
		this.setImage(harry);
		this.setFitWidth(30);
		this.setFitHeight(30);
		setFocusTraversable(true);

		setTranslateX(player.getX() * 30);
		setTranslateY(player.getY() * 30);
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

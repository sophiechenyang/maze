package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.WandModel;

public class WandView extends ImageView{
	private Image wand = new Image("file:src/img/wand.png");
	
	public WandView(WandModel wandmodel) {
		this.setImage(wand);
		this.setFitWidth(30);
		this.setFitHeight(30);

		setTranslateX(wandmodel.getX() * 30);
		setTranslateY(wandmodel.getY() * 30);
	}
	
	public void moveX(int x) {
		setTranslateX(x);
	}
	
	public void moveY(int y) {
		setTranslateY(y);
	}
}

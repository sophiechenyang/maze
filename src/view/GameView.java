package view;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameView extends Parent{
	
	public GameView() {
		BorderPane root = new BorderPane();
		Text hi = new Text("hello world");
		root.getChildren().add(hi);
		
		this.getChildren().add(root);
	}
	
}

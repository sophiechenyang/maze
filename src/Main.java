import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GameView;

public class Main extends Application{
	GameView view = new GameView();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene scene = new Scene(view.createContent(), 600, 500);
		primaryStage.setScene(scene);			
		primaryStage.setTitle("Harry Potter & the Magical Maze");
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}

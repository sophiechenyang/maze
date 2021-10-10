package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameModel;
import scene.WandScene;
import scene.SnakeScene;
import controller.GameController;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
        Pane root = new Pane();
        GameController controller = new GameController();

        root.getChildren().add(controller.getGameView());

        primaryStage.setTitle("HP Maze");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        
        // stop application on window close
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
	
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public static void launchSnakeScene(GameModel gameModel) {
		SnakeScene snakeView = new SnakeScene(gameModel);
		Scene snakeScene = new Scene(snakeView.makeSnakeScene(), 800, 600);
		Stage snakeStage = new Stage();
		snakeStage.setTitle("Fight Snake");
		snakeStage.setScene(snakeScene);
		snakeStage.show();
		
	}
	
	public static void launchWandScene(GameModel gameModel) {
		WandScene wand = new WandScene(gameModel);
		Scene wandScene = new Scene(wand.makeWandScene(), 800, 600);
		Stage wandStage = new Stage();
		wandStage.setTitle("Retrieve Elder Wand");
		wandStage.setScene(wandScene);
		wandStage.show();
		
	}
}

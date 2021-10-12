package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameModel;
import setup.setUpApp;
import scene.WandScene;
import scene.SnakeScene;
import controller.GameController;

public class Main extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
	    Pane root = new Pane();
        setUpApp app = new setUpApp();
        app.setLayoutX(120);
        app.setPadding(new Insets(100,100,100,100));
        root.getChildren().add(app);
        
        Scene scene = new Scene(root, 1080, 1080);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("HP Maze");
        primaryStage.show();
        
        launchGame(true,0);
        
        // stop application on window close
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
	
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public void launchGame(boolean difficulty, int playerType) {
		Pane root = new Pane();
        GameController controller = new GameController(difficulty, playerType);
        root.getChildren().add(controller.getGameView());
        Scene gameScene = new Scene(root, 800, 600);
        Stage gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("HP Maze");
        gameStage.show();
        
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

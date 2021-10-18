package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameModel;
import setup.setUpApp;
import view.GameView;
import scene.WandScene;
import scene.ReadyScene;
import scene.RotateScene;
import scene.SnakeScene;
import scene.SwipeScene;
import scene.VoldemortScene;
import controller.GameController;

public class Main extends Application{
	GameModel gameModel;
	GameView gameView;
	static Stage primaryStage = new Stage();
	static String CSS;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CSS = getClass().getResource("style.css").toExternalForm();
	    //startSelection();
	    
        launchGame(true,2);
        //launchSwipeScene(gameModel);
        //launchVoldemortScene(gameModel);
        //launchEndScene(gameModel);
        //launchSafeScene(gameModel);
        //launchSnakeScene(gameModel);
        //launchWandScene(gameModel, gameView);
        //launchReadyScene(gameModel,gameView);
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public static void startSelection() {
		Pane root = new Pane();
        setUpApp app = new setUpApp();
        app.setLayoutX(120);
        app.setPadding(new Insets(100,100,100,100));
        root.getChildren().add(app);
        
        Scene scene = new Scene(root, 1080, 1080);
        scene.getStylesheets().add(CSS);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("HP Maze");
        primaryStage.show();
        
        // stop application on window close
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
	}
	
	public static void launchGame(boolean difficulty, int playerType) {
		Pane root = new Pane();
        GameController controller = new GameController(difficulty, playerType);
        root.getChildren().add(controller.getGameView());
        Scene gameScene = new Scene(root, 1050, 670);
        Stage gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("HP Maze");
        gameStage.show();
        
        gameStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
	}
	
	public static void launchSnakeScene(GameModel gameModel, GameView gameView) {
		SnakeScene snakeView = new SnakeScene(gameModel, gameView);
		Scene snakeScene = new Scene(snakeView.makeSnakeScene(), 850, 480);
		Stage snakeStage = new Stage();
		snakeStage.setTitle("Fight Snake");
		snakeStage.setScene(snakeScene);
		snakeStage.show();
		
	}
	
	public static void launchWandScene(GameModel gameModel, GameView gameView) {
		WandScene wand = new WandScene(gameModel, gameView);
		Scene wandScene = new Scene(wand.makeWandScene(), 698, 480);
		Stage wandStage = new Stage();
		wandStage.setTitle("Retrieve Elder Wand");
		wandStage.setScene(wandScene);
		wandStage.show();
		
	}
	
	public static void launchSafeScene(GameModel gameModel, GameView gameView) {
		RotateScene safe = new RotateScene(gameModel, gameView);
		Scene safeScene = new Scene(safe.makeSafeScene(), 800, 410);
		Stage safeStage = new Stage();
		safeStage.setTitle("Retrieve Secret Code");
		safeStage.setScene(safeScene);
		safeStage.show();	
	}
	
	public static void launchSwipeScene(GameModel gameModel, GameView gameView) {
		SwipeScene swipe = new SwipeScene(gameModel, gameView);
		Scene safeScene = new Scene(swipe.makeSwipeScene(), 800, 450);
		Stage swipeStage = new Stage();
		swipeStage.setTitle("Swipe away trees");
		swipeStage.setScene(safeScene);
		swipeStage.show();	
	}
	
	public static void launchReadyScene(GameModel gameModel, GameView gameView) {
		ReadyScene ready = new ReadyScene(gameModel);
		Scene readyScene = new Scene(ready.makeReadyScene(), 800, 450);
		Stage readyStage = new Stage();
		readyStage.setTitle("Not ready for Voldemort");
		readyStage.setScene(readyScene);
		readyStage.show();	
	}
	
	public static void launchVoldemortScene(GameModel gameModel, GameView gameView) {
		VoldemortScene voldemort = new VoldemortScene(gameModel, gameView);
		Scene voldemortScene = new Scene(voldemort.makeVoldemortScene(), 800, 450);
		Stage voldemortStage = new Stage();
		voldemortStage.setTitle("Defeat Voldemort");
		voldemortStage.setScene(voldemortScene);
		voldemortStage.show();	
	}
}

package application;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane();
		
		Text text = new Text("testing");
		root.getChildren().add(text);
		
        GameController controller = new GameController();

        root.getChildren().add(controller.getGameView());
        System.out.println(root);

        primaryStage.setTitle("HP Maze");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        
        // stop application on window close
//        primaryStage.setOnCloseRequest(e -> {
//            Platform.exit();
//            System.exit(0);
//        });
	
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}

package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        App app = new App() ;
        root.getChildren().add(app);
        
        Scene scene = new Scene(root, 550, 500);
        


        primaryStage.setTitle("Harry Potter Maze");
        
        primaryStage.setScene(scene);
        
        

        
        primaryStage.show();
    }


	public static void main(String[] args) {
		launch();
	}

}

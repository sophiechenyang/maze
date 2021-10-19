package setup;

import java.awt.Color;
import java.io.FileInputStream;

import controller.GesturesController;
import setup.draggableMaker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MenuResult;

public class selectCharacterScene {
	
	   private int ROLE;
	   GesturesController gestureController = new GesturesController();
	   MenuResult resultModel = new MenuResult();
	   Stage window = new Stage();
	   
	   
	   
	   
	   
	   public selectCharacterScene() {
		   
	   
	   }
	   
	   
	   
	   
//	   
//	  public void setRole(int role) {
//		  this.ROLE = role;
//	  }
//	  
//	  
//	   
//	   public  int getRole() {
//		   return ROLE;
//	   }
//	   
//	   
//	   
	
	

	public void makeCharacterScene()  {
		
		
		
		
		window.setTitle("Choose a Character");
		window.initModality(Modality.APPLICATION_MODAL);
		

		

		VBox left = new VBox();
		left.setAlignment(Pos.CENTER);

		// left top show the selection of the character
		VBox selectedCharacter = new VBox();
		selectedCharacter.setPrefWidth(400);
		selectedCharacter.setPrefHeight(400);
		selectedCharacter.setAlignment(Pos.CENTER);
		selectedCharacter.setStyle("-fx-background-color: #000;");

		VBox characters = new VBox();

		characters.setPrefWidth(200);
		characters.setPrefHeight(300);
		characters.setAlignment(Pos.CENTER);
		characters.setStyle("-fx-border-color: white;" + "-fx-border-width: 2;" + "-fx-border-style: solid;"
				+ "-fx-border-radius: 24;");

		
		
		
		// Draggable characters
		gestureController.insertImage(new Image("img/harry-potter-144.png"), characters, 0);
		gestureController.insertImage(new Image("img/draco-malfoy-144.png"), characters, 3);
		gestureController.insertImage(new Image("img/hermione-144.png"), characters, 2);
		gestureController.insertImage(new Image("img/ron-144.png"), characters, 1);

		
		
		// Call for the drag target method
		gestureController.setupGestureTarget(selectedCharacter);
		gestureController.setupGestureTarget(characters);

		
		
		
		
		VBox resultsPane = new VBox();
		//Label namecharacter = new Label("You are :"+ gestureController.getRoleResult());
		//namecharacter.setStyle("-fx-text-fill:#ffffff");
		//resultsPane.getChildren().add( namecharacter);
		
		
		

		left.getChildren().addAll(selectedCharacter, resultsPane);
		Label startGameButton = new Label("Save and Go Back by Zoom in");
		startGameButton.setStyle("-fx-text-fill:#ffffff;");
		
		
		
		
		HBox bottom = new HBox(startGameButton);
		bottom.setAlignment(Pos.CENTER);

		
		
		
		BorderPane popUpDialog = new BorderPane();
		popUpDialog.setPadding(new Insets(100, 100, 100, 100));

		popUpDialog.setLeft(left);
		popUpDialog.setRight(characters);
		popUpDialog.setBottom(bottom);
		popUpDialog.setStyle(
				"-fx-background-image: url(\"https://images.ctfassets.net/usf1vwtuqyxm/4Tep5QNlFuGqsIYOC4Cgoa/e994c4226c1a8c151dc9c51afc12dc4b/TriwizardMaze_PM_B4C31M1_GoldenMistInTriwizardMaze_Moment.jpg\");"
				+"-fx-text-fill:white;"
				
				);
		

		
		
		Scene scene = new Scene(popUpDialog, 800, 800);
		

		window.setScene(scene);
		window.show();
		
		
		gestureController.ZoomEvent(popUpDialog, window);

	}
	
	
	
	
	
	public void closeWindow() {
		this.window.close();
		
	}
	
	
	
	
	
	
	
	

}

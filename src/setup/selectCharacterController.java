package setup;

import java.awt.Color;
import java.io.FileInputStream;

import setup.draggableMaker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class selectCharacterController {

	public static void selectCharacterDialg() throws Exception {
		Stage window = new Stage();
		window.setTitle("Choose a Character");
		window.initModality(Modality.APPLICATION_MODAL);
		draggableMaker dragController = new draggableMaker();

//		HBox characterPane = new HBox();

		VBox left = new VBox();
		left.setAlignment(Pos.CENTER);

		// left top show the selection of the character
		VBox selectedCharacter = new VBox();
		selectedCharacter.setPrefWidth(400);
		selectedCharacter.setPrefHeight(400);
		selectedCharacter.setAlignment(Pos.CENTER);
		selectedCharacter.setStyle("-fx-background-color: #D5FA52;");

		VBox characters = new VBox();

		characters.setPrefWidth(200);
		characters.setPrefHeight(300);
		characters.setAlignment(Pos.CENTER);
		characters.setStyle("-fx-border-color: white;" + "-fx-border-width: 2;" + "-fx-border-style: solid;"
				+ "-fx-border-radius: 24;");

		// Draggable characters
		dragController.insertImage(new Image("img/harry-potter-144.png"), characters);
		dragController.insertImage(new Image("img/draco-malfoy-144.png"), characters);
		dragController.insertImage(new Image("img/hermione-144.png"), characters);
		dragController.insertImage(new Image("img/dobby-144.png"), characters);

		// Call for the drag target method
		dragController.setupGestureTarget(selectedCharacter);
		dragController.setupGestureTarget(characters);

		// Show the selections by texts in the left bottom
		VBox resultsPane = new VBox();
		Text difficultylevel = new Text("Level:");
		Text namecharacter = new Text("You are :");
		resultsPane.getChildren().addAll(difficultylevel, namecharacter);

		left.getChildren().addAll(selectedCharacter, resultsPane);

		Button startGameButton = new Button("Start Game");

		HBox bottom = new HBox(startGameButton);

		bottom.setAlignment(Pos.CENTER);

		BorderPane popUpDialog = new BorderPane();
		popUpDialog.setPadding(new Insets(100, 100, 100, 100));

		popUpDialog.setLeft(left);
		popUpDialog.setRight(characters);
		popUpDialog.setBottom(bottom);

		Scene scene = new Scene(popUpDialog, 800, 800);

		window.setScene(scene);
		window.show();

		return;

	}

}

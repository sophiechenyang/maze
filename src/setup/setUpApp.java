package setup;

import java.awt.Insets;


import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import setup.selectCharacterController;




public class setUpApp extends BorderPane{
	private Rectangle character;
	ImageView iv;
	int curseur = 0;
	int nomcharacters = 4; 
    int nomselection = 0; 
    
    Text difficultylevel;
    Text namecharacter;
	
    selectCharacterController popupController = new selectCharacterController();
    
	public setUpApp() {
		
		
		initUI();
	}
	
	
	private void initUI() {
		
		
		
		
		HBox header = new HBox();
		ImageView title = new ImageView();
		Image titleImage = new Image("img/header.png");
		title.setImage(titleImage);
		header.getChildren().add(title);
		header.setAlignment(Pos.CENTER);
		
		
		
		
		
		//Left Box containing levels
		Text levelTitle = new Text("Choose a difficulty Level");
		//TODO: add actions to the buttons
		Button btn1 = new Button("Beginner");
		Button btn2 = new Button("Middle");
		Button btn3 = new Button("Advanced");
		
		VBox levelPane = new VBox(levelTitle, btn1,btn2,btn3);
		levelPane.setAlignment(Pos.CENTER);
		

		
		
		
		
		
		
		
		
		//Right side: character selection  characters
		
		Text rightText = new Text("Choose a Role");
		//TODO: add actions to the buttons
		Button btn4 = new Button("start");
		btn4.setOnAction(e -> {
			try {
				popupController.selectCharacterDialg();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		//Alternative Hover action
//		btn4.addEventHandler(MouseEvent.MOUSE_ENTERED,
//		        new EventHandler<MouseEvent>() {
//	          @Override
//	          public void handle(MouseEvent e) {
//	            try {
//					popupController.selectCharacterDialg();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	          }
//	        });
		
		VBox rightSide = new VBox();
		rightSide.getChildren().addAll(rightText,btn4);
		

		Button startGameButton = new Button("Start Game");
		
		HBox bottom = new HBox(startGameButton);
		
		bottom.setAlignment(Pos.CENTER);
		


		
		this.setTop(header);
		this.setLeft(levelPane);
		this.setRight(rightSide);
		this.setBottom(bottom);
		this.setStyle(
				"-fx-background-color: #FF5F0F;"
				);
		this.heightProperty().lessThan(200);
		
		
		
		
		
		
	}
	

	
	



}

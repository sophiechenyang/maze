package setup;



import controller.GameController;
import controller.GesturesController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.MenuResult;


public class setUpApp extends Parent{
	
	
	
	private Rectangle character;
	ImageView iv;
	
   
    
    private int ROLE;
    private boolean isAdvance = false;

    GesturesController gestureController = new GesturesController();
    
    selectCharacterScene selectCharacter = new selectCharacterScene();
    draggableMaker d = new draggableMaker();
    
    private Image beginerWand = new Image("img/stick.png");
	private Image beginerBtn = new Image("img/beginner-button.png");
	private Image advanceWand = new Image("img/stick.png");
	private Image advanceBtn = new Image("img/advanced-level.png");
	
	private ImageView wand1 = new ImageView(beginerWand);
	private ImageView beginerButton = new ImageView(beginerBtn);
	private ImageView wand2 = new ImageView(advanceWand);
	private ImageView advanceButton = new ImageView(advanceBtn);
	
	private Button StartGame = new Button("Start");
	
	private Image key = new Image("img/key.png");
	private Image lock = new Image("img/role-select.png");
	private ImageView Key = new ImageView(key);
	private ImageView Lock = new ImageView(lock);
	
	MenuResult resultModel = new MenuResult();
  
    
	public setUpApp() {
		
		
		createMenu();
		
		
		
	}
	
	
	
	public boolean getResultDifficulty() {
		return gestureController.getDifficultyResult();
	}
	
	public int getResultRole() {
		
		return gestureController.getRoleResult();
	}
	
	
	
	
	private BorderPane createMenu() {
		
		
		
		BorderPane root = new BorderPane();
		HBox header = new HBox();
		ImageView title = new ImageView();
		Image titleImage = new Image("img/setup-title.png");
		title.setImage(titleImage);
		header.getChildren().add(title);	
		header.setAlignment(Pos.CENTER);

		
		//Level selection using rotate
		
		Label Instruction = new Label("Instruction: rotate a magic wand to point at your prefered level. Then, put your cursor on the face of the Key, scroll and rotate to open the Lock to go to Roles.");
		Instruction.setId("instruction-text");
		Instruction.setStyle("-fx-text-fill: white;");
		Instruction.setPrefWidth(700);
		
	
		
		VBox levelWrapper1 = new VBox();
		HBox levelPane1 = new HBox();
		levelPane1.setPrefHeight(100);
		beginerButton.setOpacity(0.6);
		levelPane1.getChildren().addAll(wand1, beginerButton);
		levelPane1.setSpacing(0);
		
		
		
		HBox levelPane2 = new HBox();
		advanceButton.setOpacity(0.6);
		levelPane2.getChildren().addAll(wand2, advanceButton);
		levelPane2.setSpacing(0);
		
		
		gestureController.RotateEvent(wand1, beginerButton, false);
		gestureController.RotateEvent(wand2, advanceButton, true);
		

		
		
		levelWrapper1.getChildren().addAll(Instruction,levelPane1, levelPane2);
		levelWrapper1.setSpacing(0);
		
		
	
		
		Button StartGame = new Button("Start Game");
		gestureController.SwipeEvent(StartGame);
		

		
		gestureController.ScrollEvent(Key,Lock);
		
		VBox rightCenter = new VBox();
		Lock.setOpacity(0.5);
		HBox right = new HBox(Key,Lock);
		right.setSpacing(0);
		right.setAlignment(Pos.CENTER_LEFT);
		Label t1 = new Label("You selected Role is:"+ ROLE);
		Label t2 = new Label("You selected is Advanced? "+ gestureController.getDifficultyResult());
		
		rightCenter.getChildren().addAll(right, t1,t2);

	
		root.setTop(header);
		root.setLeft(levelWrapper1);
		root.setCenter(rightCenter);
		root.setBottom(StartGame);
		root.setPrefHeight(900);
		
		root.setMaxWidth(1440);
		
		root.getStylesheets().add(getClass().getResource("../application/style.css").toExternalForm());
//		this.setAlignment(header, Pos.TOP_CENTER);
//		this.setAlignment(bottom, Pos.CENTER_LEFT);
		root.setAlignment(StartGame,Pos.TOP_CENTER);
		root.setPadding(new Insets(10,100,200,200));
		
		root.setStyle(
				"-fx-background-image: url(\"https://images.ctfassets.net/usf1vwtuqyxm/4Tep5QNlFuGqsIYOC4Cgoa/e994c4226c1a8c151dc9c51afc12dc4b/TriwizardMaze_PM_B4C31M1_GoldenMistInTriwizardMaze_Moment.jpg\");");
		
		
		
		this.getChildren().add(root);
		
		return null;
				
	}
	
	
	
	
	
	public void setDifficulty(boolean isAdvanced) {
		this.isAdvance = isAdvanced;
	}
	
	public boolean isAdvanced() {
		return isAdvance;
		
	}
	

	public void setRole(int Role) {
		this.ROLE = Role;
	}
	
	
	public int getRole() {
		return ROLE;
	}
	
	

	



}

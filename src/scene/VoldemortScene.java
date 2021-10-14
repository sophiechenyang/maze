package scene;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;

public class VoldemortScene {
	private GameModel gameModel;
	Pane root = new Pane();
	private Button closeButton = new Button("Return to the Maze");
	
	public VoldemortScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public Parent makeVoldemortScene() {
		//TO DO: add in scenes for when wand or spells are missing

		attack();
		return root;
	}
	
	public void attack() {
    	
    	Image introImg = new Image("file:img/voldemort_intro.png");
    	ImageView introView = new ImageView(introImg);
    	introView.setFitWidth(800);
    	introView.setPreserveRatio(true);
    	introView.setOnMouseClicked(e -> introView.setImage(null));
    	
    	Image introImg2 = new Image("file:img/voldemort_intro2.png");
    	ImageView introView2 = new ImageView(introImg2);
    	introView2.setFitWidth(800);
    	introView2.setPreserveRatio(true);
    	introView2.setOnMouseClicked(e -> introView2.setImage(null));
    	
    	Image battle = new Image("file:img/battle.png");
    	ImageView battleView = new ImageView(battle);
    	
    	Image defeat = new Image("file:img/defeat.png");
    	ImageView defeatView = new ImageView(defeat);
    	
		closeButton.setLayoutX(350);
		closeButton.setLayoutY(370);
		closeButton.setOnMouseClicked(e -> closeWindow());
    	
    	battleView.setOnMouseClicked(e -> {
    		battleView.setImage(null);
    		root.getChildren().addAll(defeatView, closeButton);
    	});
    	
    	StackPane stackPane = new StackPane();
    	TextField spell = new TextField();
    	spell.setMaxWidth(200);
    	spell.setOnKeyPressed(event -> {
    		if (event.getCode().equals(KeyCode.ENTER)) {
    			String value = spell.getText();
    			String correctSpell = "Expelliarmus";
    			if (value.equalsIgnoreCase(correctSpell)) {
    				root.getChildren().clear();
    				root.getChildren().add(battleView);
    				System.out.println("yay");
    			}
    		}
    		
    	});
    	
    	Image casting = new Image("file:img/voldemort_casting.png");
    	ImageView castingView = new ImageView(casting);
    	castingView.setFitWidth(800);
    	castingView.setPreserveRatio(true);
    	//castingView.setOnMouseClicked(e -> castingView.setImage(null));
    	
    	stackPane.getChildren().addAll(castingView, spell);
    	
		root.getChildren().addAll(stackPane, introView2, introView);
	}
	
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}

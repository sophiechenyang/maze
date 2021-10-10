package view;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.BeetleModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.TreasureModel;

public class GameView extends Parent{	
	
	int rows = GameModel.getRows();
	int columns = GameModel.getColumns();
	int tileSize = GameModel.getTileSize();
	Text gameScore = new Text("0");
	Text jewelScore = new Text("0");
	Pane pane = new Pane();
	private ArrayList<BeetleView> beetleViewList = new ArrayList<BeetleView>();
	
	public GameView() {
		BorderPane root = new BorderPane();

		root.setTop(createHeader());
		root.setCenter(pane);
		root.setRight(createRightPanel());
		
		this.getChildren().add(root);
	}
	
	public TileView createTile(TileModel tileModel, int tileType) {
		TileView tileView = new TileView(tileModel, tileType);
		pane.getChildren().add(tileView);
		return tileView;	
	}
	
	public PlayerView createPlayer(PlayerModel player) {
		PlayerView playerView = new PlayerView(player);	
		pane.getChildren().addAll(playerView);
		return playerView;
	}
	
	public BeetleView createBeatle(BeetleModel beetlemodel, GameModel gameModel) {
		BeetleView beetleView = new BeetleView(beetlemodel);
		pane.getChildren().add(beetleView);
		beetleViewList.add(beetleView);
		return beetleView;
	}
	
	public TreasureView createTreasure(TreasureModel treasureModel) {
		TreasureView treasureView = new TreasureView(treasureModel);
		pane.getChildren().add(treasureView);
		return treasureView;
	}

	public void updateScore (GameModel gameModel) {
		gameScore.setText(Integer.toString(gameModel.getGamePoints())); ;
	}
	
	// Game Title
	public StackPane createHeader() {
		StackPane header = new StackPane();
		
		Image topPaneImg = new Image("file:img/title.png");
		ImageView topPaneView = new ImageView(topPaneImg);
		topPaneView.setFitWidth(800);
		topPaneView.setFitHeight(100);
		
		//header.getStyleClass().add("header");
		
		header.getChildren().addAll(topPaneView);
		return header;
		
	}
	
	public StackPane createLeftPanel() {
		StackPane leftcontainer = new StackPane();
				
		Image leftPaneImg = new Image("file:img/leftPane.png");
		ImageView leftPaneView = new ImageView(leftPaneImg);
		
		Image fire = new Image("file:img/fire.gif");
		ImageView fireView = new ImageView(fire);
		fireView.setFitWidth(70);
		fireView.setPreserveRatio(true);
		
		leftPaneView.setFitWidth(400);
		leftPaneView.setFitHeight(460);
		
		leftcontainer.getChildren().addAll(leftPaneView, fireView);
		return leftcontainer;
	}
	

	public VBox createRightPanel() {
		VBox rightcontainer = new VBox();
		
		rightcontainer.getChildren().addAll(gameScore, jewelScore);
		return rightcontainer;
	}
	
	public Pane createBottomPane() {
		HBox bottomcontainer = new HBox();
		Image bottomPaneImg = new Image("file:img/bottomPane.png");
		ImageView bottomPaneView = new ImageView(bottomPaneImg);
		bottomPaneView.setFitWidth(1160);
		bottomPaneView.setFitHeight(100);
		
		bottomcontainer.getChildren().add(bottomPaneView);
		return bottomcontainer;
	}
	
	public void setWinView() {
		Image winImg = new Image("file:img/harry.png");
		ImageView winView = new ImageView(winImg);
		// Button playAgain = new Button("Play Again");
		pane.getChildren().clear();
		pane.getChildren().addAll(winView);
	}
	
	public void setLostView() {
		Image lostImg = new Image("file:img/harry.png");
		
		beetleViewList.forEach(beetle -> beetle.stopBeetle());
	}
	
	public void reset() {
		pane.getChildren().clear();
		gameScore.setText("0");
		jewelScore.setText("0");
	}
	
	public void clickResetButton(Button button, EventHandler<MouseEvent> startGame) {
		button.setOnMouseClicked(startGame);
	}
	
	public void setKeyPressHandler(EventHandler<KeyEvent> activateAmulet) {
		this.setOnKeyPressed(activateAmulet);
	}
	
	public void setKeyReleaseHandler(EventHandler<KeyEvent> deactivateAmulet) {
		this.setOnKeyReleased(deactivateAmulet);
	}


}

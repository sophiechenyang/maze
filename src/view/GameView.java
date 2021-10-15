package view;

import java.util.ArrayList;

import controller.GameController;
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
import model.DementorModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.ManaModel;

public class GameView extends Parent{	
	
	private int rows = GameModel.getRows();
	private int columns = GameModel.getColumns();
	private int tileSize = GameModel.getTileSize();
	private int sidePaneWidth = 150;
	private Text gameScore = new Text("0");
	private Text playerName = new Text("");
	private Text playerHouse = new Text("");	
	private String name;
	private String house;
	private Text manaCount = new Text("0");
	private int mana;
	private Text healthCount = new Text("0");
	private int health;
	private VBox inventory = new VBox();
	
	Pane pane = new Pane();
	private ArrayList<DementorView> dementorViewList = new ArrayList<DementorView>();
	private ArrayList<TileView> tileViewList = new ArrayList<TileView>();
	
	public GameView() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: green;");
		root.setPrefWidth(sidePaneWidth *2 + columns * tileSize);
		root.setPrefHeight(900);
		pane.setPrefSize(600, 200);
		pane.setStyle("-fx-background-color: #324530;");

		root.setTop(createHeader());
		root.setCenter(pane);
		root.setRight(createRightPanel());
		root.setLeft(createLeftPanel());
		
		this.getChildren().add(root);
	}
	
	public TileView createTile(TileModel tileModel, int tileType) {
		TileView tileView = new TileView(tileModel, tileType);
		pane.getChildren().add(tileView);
		tileViewList.add(tileView);
		return tileView;	
	}
	
	public PlayerView createPlayer(PlayerModel player) {
		PlayerView playerView = new PlayerView(player);
		name = player.getPlayerName();
		house = player.getHouse();
		mana = player.getMana();
		health = player.getHealth();
		
		playerName.setText(name);
		playerHouse.setText(house);
		manaCount.setText(Integer.toString(mana));
		healthCount.setText(Integer.toString(health));
		
		pane.getChildren().addAll(playerView);
		return playerView;
	}
	
	public DementorView createDementor(DementorModel dementorModel, GameController gameController) {
		DementorView dementorView = new DementorView(dementorModel, gameController);
		pane.getChildren().add(dementorView);
		dementorViewList.add(dementorView);
		return dementorView;
	}
	
	public ManaView createTreasure(ManaModel manaModel) {
		ManaView manaView = new ManaView(manaModel);
		pane.getChildren().add(manaView);
		return manaView;
	}

	public void updateScore (GameModel gameModel) {
		gameScore.setText(Integer.toString(gameModel.getGamePoints())); ;
	}
	
	public void updatePlayerStats(PlayerModel playerModel) {
		manaCount.setText(Integer.toString(playerModel.getMana()));
		healthCount.setText(Integer.toString(playerModel.getHealth()));
	}
	// Game Title
	public Pane createHeader() {
		Pane header = new Pane();
		header.setStyle("-fx-background-color: green;");
		
		Image topPaneImg = new Image("file:img/title.png");
		ImageView topPaneView = new ImageView(topPaneImg);
		topPaneView.setFitWidth(750);
		topPaneView.setFitHeight(100);
		
		//header.getStyleClass().add("header");
		
		header.getChildren().addAll(topPaneView);
		return header;
		
	}
	
	public Pane createLeftPanel() {
		Pane leftcontainer = new Pane();
		leftcontainer.setStyle("-fx-background-color: green;");
		leftcontainer.setPrefWidth(sidePaneWidth);
				
		Image leftPaneImg = new Image("file:img/leftPane.png");
		ImageView leftPaneView = new ImageView(leftPaneImg);
		
		leftPaneView.setFitWidth(100);
		leftPaneView.setFitHeight(500);
		
		//leftcontainer.getChildren().addAll(leftPaneView);
		return leftcontainer;
	}
	

	public VBox createRightPanel() {
		VBox rightcontainer = new VBox();
		rightcontainer.setStyle("-fx-background-color: yellow;");
		rightcontainer.setPrefWidth(sidePaneWidth);
		rightcontainer.setPadding(new Insets(15, 12, 15, 12));
		rightcontainer.setSpacing(10);
		
		VBox characterBox = new VBox();
		//Text charName = new Text("Name:");
		
		HBox houseGroup = new HBox();
		Text houseText = new Text("House: ");
		houseGroup.getChildren().addAll(houseText, playerHouse);
		characterBox.getChildren().addAll(playerName, houseGroup);
		
		VBox charStats = new VBox();
		HBox manaGroup = new HBox();
		Text manaText = new Text("mana: ");
		HBox healthGroup = new HBox();
		Text healthText = new Text("health: ");
		healthGroup.getChildren().addAll(healthText, healthCount);
		manaGroup.getChildren().addAll(manaText, manaCount);
		
		charStats.getChildren().addAll(manaGroup, healthGroup);
		
		//VBox inventory = new VBox();
		Text inventoryText = new Text("Inventory");
		
		inventory.getChildren().addAll(inventoryText);
		
		rightcontainer.getChildren().addAll(characterBox, charStats, inventory);
		return rightcontainer;
	}
	
	public Pane createBottomPane() {
		HBox bottomcontainer = new HBox();
		bottomcontainer.setPrefHeight(200);
		bottomcontainer.setStyle("-fx-background-color: green;");
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
		
		dementorViewList.forEach(dementor -> dementor.stopDementor());
	}
	
	public void reset() {
		pane.getChildren().clear();
		gameScore.setText("0");
		manaCount.setText("0");
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
	
	public ArrayList<TileView> getTileViewList() {
		return tileViewList;
	}
	
	public void addBookToInventory() {
		
		Image book = new Image("file:img/book.png");
		ImageView bookView = new ImageView(book);
		inventory.getChildren().add(bookView);
	}
	
	public void addWandToInventory() {
		
		Image wand = new Image("file:img/wand.png");
		
		ImageView wandView = new ImageView(wand);
		wandView.setFitWidth(40);
		wandView.setPreserveRatio(true);
		inventory.getChildren().add(wandView);
	}

	public void readyForVoldemort() {
		
		Text readyText = new Text("You are now ready to face Voldemort!");
		//inventory.getChildren().add(readyText);
	}

}

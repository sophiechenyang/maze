package view;

import java.util.ArrayList;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DementorModel;
import model.GameModel;
import model.PlayerModel;
import model.TileModel;
import model.ManaModel;
import application.Main;

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
	private Text remainingTime = new Text("300");
	private VBox inventory = new VBox();
	private Image playerImg = new Image("file:img/harry.png");
	private ImageView playerImgView = new ImageView(playerImg);
	private Button resetBttn = new Button("New Game");
	
	Pane pane = new Pane();
	private ArrayList<DementorView> dementorViewList = new ArrayList<DementorView>();
	private ArrayList<TileView> tileViewList = new ArrayList<TileView>();
	
	public GameView() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #003714;");
		root.setPrefWidth(sidePaneWidth *2 + columns * tileSize);
		root.setPrefHeight(400);
		//pane.setMaxHeight(500);
		pane.setMaxWidth(750);
		pane.setPrefHeight(500);
		pane.setStyle("-fx-background-color: #28482D;");

		root.setTop(createHeader());
		root.setCenter(pane);
		root.setRight(createRightPanel());
		root.setLeft(createLeftPanel());
		//root.setBottom(createBottomPane());
		
		this.getChildren().add(root);
	}
	
	public TileView createTile(TileModel tileModel, int tileType) {
		TileView tileView = new TileView(tileModel, tileType);
		pane.getChildren().add(tileView);
		tileViewList.add(tileView);
		return tileView;	
	}
	
	public TileView getTileOfType(int tileType) {
		for(int i=0; i<tileViewList.size(); i++) {
			TileView tileView = tileViewList.get(i);
			if (tileView.getTileType()==tileType) {
				return tileView;
			}
		}
		return null;
	}
	
	public PlayerView createPlayer(PlayerModel player) {
		PlayerView playerView = new PlayerView(player);
		name = player.getPlayerName();
		house = player.getHouse();
		mana = player.getMana();
		health = player.getHealth();
		
		String url = player.getPlayerURL();
		Image playerImg = new Image(url);
		playerImgView.setImage(playerImg);
		
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
	
	public ManaView createMana(ManaModel manaModel) {
		ManaView manaView = new ManaView(manaModel);
		pane.getChildren().add(manaView);
		return manaView;
	}

	public void updateScore (GameModel gameModel) {
		gameScore.setText(Integer.toString(gameModel.getGamePoints())); ;
	}
	
	public void updateTime (int timeLeft) {
		remainingTime.setText(Integer.toString(timeLeft));
	}
	
	public void updatePlayerStats(PlayerModel playerModel) {
		manaCount.setText(Integer.toString(playerModel.getMana()));
		healthCount.setText(Integer.toString(playerModel.getHealth()));
	}
	
	// Game Title
	public Pane createHeader() {
		HBox header = new HBox();
		header.setMinHeight(120);
		header.setAlignment(Pos.CENTER);
		
		Image topPaneImg = new Image("file:img/title.png");
		ImageView topPaneView = new ImageView(topPaneImg);
		topPaneView.setFitHeight(65);
		topPaneView.setPreserveRatio(true);
		
		//header.getStyleClass().add("header");
		
		header.getChildren().addAll(topPaneView);
		return header;
		
	}
	
	public Pane createLeftPanel() {
		VBox leftcontainer = new VBox();
		leftcontainer.setPrefWidth(sidePaneWidth);
		leftcontainer.setPadding(new Insets(15, 12, 15, 12));
		leftcontainer.setSpacing(10);
		
		Text timerText = new Text("Time Remaining:");
		timerText.setFill(Color.ANTIQUEWHITE);
		remainingTime.setFill(Color.ANTIQUEWHITE);
		
		Text pointsText = new Text("Points: ");
		pointsText.setFill(Color.FLORALWHITE);
		gameScore.setFill(Color.FLORALWHITE);
		
		Image leftPaneImg = new Image("file:img/death_eaters.png");
		ImageView leftPaneView = new ImageView(leftPaneImg);
		
		leftPaneView.setFitWidth(110);
		leftPaneView.setFitHeight(440);
		leftPaneView.setPreserveRatio(true);
		leftPaneView.setY(200);
		leftPaneView.setX(20);
		
		leftcontainer.getChildren().addAll(timerText, remainingTime, pointsText, gameScore, leftPaneView);
		return leftcontainer;
	}
	

	public VBox createRightPanel() {
		VBox rightcontainer = new VBox();
		
		Image rightPaneImg = new Image("file:img/death_eaters.png");
		ImageView rightPaneView = new ImageView(rightPaneImg);
		
		rightPaneView.setFitWidth(110);
		rightPaneView.setFitHeight(440);
		rightPaneView.setPreserveRatio(true);
		rightPaneView.setX(20);
		
		rightcontainer.setPrefWidth(sidePaneWidth);
		rightcontainer.setPadding(new Insets(15, 12, 15, 12));
		rightcontainer.setSpacing(10);
		
		playerImgView.setFitWidth(96);
		playerImgView.setPreserveRatio(true);
		
		VBox characterBox = new VBox();
		
		HBox houseGroup = new HBox();
		Text houseText = new Text("House: ");
		houseText.setFill(Color.GOLDENROD);
		playerHouse.setFill(Color.GOLDENROD);
		playerName.setFill(Color.GOLD);
		houseGroup.getChildren().addAll(houseText, playerHouse);
		characterBox.getChildren().addAll(playerImgView, playerName, houseGroup);
		
		VBox charStats = new VBox();
		HBox manaGroup = new HBox();
		Text manaText = new Text("mana: ");
		manaText.setFill(Color.LIGHTBLUE);
		manaCount.setFill(Color.LIGHTBLUE);
		
		HBox healthGroup = new HBox();
		Text healthText = new Text("health: ");
		healthText.setFill(Color.LIGHTGREEN);
		healthCount.setFill(Color.LIGHTGREEN);
		
		healthGroup.getChildren().addAll(healthText, healthCount);
		manaGroup.getChildren().addAll(manaText, manaCount);
		
		charStats.getChildren().addAll(manaGroup, healthGroup);
		
		Text inventoryText = new Text("Inventory");
		inventoryText.setFill(Color.GHOSTWHITE);
		
		inventory.getChildren().addAll(inventoryText);
		
		rightcontainer.getChildren().addAll(characterBox, charStats, inventory, rightPaneView);
		return rightcontainer;
	}
	
	public Pane createBottomPane() {
		HBox bottomcontainer = new HBox();
		bottomcontainer.setPrefHeight(200);
		bottomcontainer.setStyle("-fx-background-color: yellow;");
		Image bottomPaneImg = new Image("file:img/bottomPane.png");
		ImageView bottomPaneView = new ImageView(bottomPaneImg);
		bottomPaneView.setFitWidth(1160);
		bottomPaneView.setFitHeight(100);
		
		bottomcontainer.getChildren().add(bottomPaneView);
		return bottomcontainer;
	}
	
	public void setWinView() {
		Image winImg = new Image("file:img/successImg.png");
		ImageView winView = new ImageView(winImg);
		winView.setX(80);
		winView.setY(110);
		
		resetBttn.setLayoutX(350);
		resetBttn.setLayoutY(340);
		
		resetBttn.setOnMouseClicked(e -> {
			Main.startSelection();
			Stage stage = (Stage) resetBttn.getScene().getWindow();
			stage.close();
		});
		
		//pane.getChildren().clear();
		pane.getChildren().addAll(winView, resetBttn);
		dementorViewList.forEach(dementor -> dementor.stopDementor());
	}
	
	public void setLostView() {
		Image lostImg = new Image("file:img/game_over.png");
		ImageView lostView = new ImageView(lostImg);
		lostView.setX(210);
		lostView.setY(150);
		
		resetBttn.setLayoutX(350);
		resetBttn.setLayoutY(280);
		
		resetBttn.setOnMouseClicked(e -> {
			Main.startSelection();
			Stage stage = (Stage) resetBttn.getScene().getWindow();
			stage.close();
		});
		pane.getChildren().addAll(lostView, resetBttn);
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
		
		Text readyText = new Text("Ready for Voldemort");
		readyText.setFill(Color.FLORALWHITE);
		inventory.getChildren().add(readyText);
	}

}

package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;
import view.GameView;

public class WandScene {
	private GameModel gameModel;
	private GameView gameView;

	public WandScene(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
	}

	public Parent makeWandScene() {
		Pane root = new Pane();

		Image instructImg = new Image("file:img/scroll_instruct.png");
		ImageView instructView = new ImageView(instructImg);

		Button closeButton = new Button("Put Wand in Inventory");

		Image wandImg = new Image("file:img/elder-wand.png");
		ImageView wandView = new ImageView(wandImg);
		wandView.setFitWidth(120);
		wandView.setX(560);
		wandView.setY(20);
		wandView.setPreserveRatio(true);

		Image harryImg = new Image("file:img/harry_broom.png");
		ImageView harryView = new ImageView(harryImg);
		harryView.setFitWidth(80);
		harryView.setX(20);
		harryView.setY(380);
		harryView.setPreserveRatio(true);

		Image wandObtained = new Image("file:img/wand_obtained.png");

		closeButton.setLayoutX(300);
		closeButton.setLayoutY(265);

		harryView.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				harryView.setTranslateX(harryView.getTranslateX() + event.getDeltaX());
				harryView.setTranslateY(harryView.getTranslateY() + event.getDeltaY());
				// event.consume();

				if (harryView.getTranslateX() > 500 && harryView.getTranslateY() < -100) {
					if (!gameModel.isWandRetrieved()) {
						wandView.setImage(null);
						harryView.setImage(null);
						instructView.setImage(wandObtained);
						gameModel.setWandRetrieved(true);
						gameModel.increaseGamePointsBy(2000);
						gameView.updateScore(gameModel);
						closeButton.setOnMouseClicked(e -> closeWindow());
						root.getChildren().addAll(closeButton);
					}
				}

				System.out.println(harryView.getTranslateX());
				System.out.println(harryView.getTranslateY());
			}

			private void closeWindow() {
				gameView.addWandToInventory();
				Stage stage = (Stage) closeButton.getScene().getWindow();
				stage.close();
			}
		});

		root.getChildren().addAll(instructView, wandView, harryView);
		return root;
	}
}

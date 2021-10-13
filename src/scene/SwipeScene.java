package scene;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;

public class SwipeScene {
	private GameModel gameModel;
	private boolean isDLeftOutofBounds = false;
	private boolean isDRightOutofBounds = false;
	private boolean isDCenterOutofBounds = false;
	private Button closeButton = new Button("Proceed");
	private Image instruction = new Image("file:img/swipe_instruction.png");
	private ImageView instructView = new ImageView(instruction);
	private Image clear = new Image("file:img/swipe_done.png");
	private ImageView doneView = new ImageView(clear);
	private Pane root = new Pane();

	public SwipeScene(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public Parent makeSwipeScene() {
		
		Image backgroundImg = new Image("file:img/hedge.jpeg");
		ImageView bgView = new ImageView(backgroundImg);
		bgView.setFitWidth(800);
		bgView.setPreserveRatio(true);

		Image dementorLeft = new Image("file:img/dementor_left.png");
		ImageView dLeftView = new ImageView(dementorLeft);
		dLeftView.setX(100);
		dLeftView.setY(120);

		Image dementorRight = new Image("file:img/dementor_right.png");
		ImageView dRightView = new ImageView(dementorRight);
		dRightView.setX(450);
		dRightView.setY(120);
		
		Image dementorCenter = new Image("file:img/dementor_up.png");
		ImageView dCenterView = new ImageView(dementorCenter);
		dCenterView.setX(350);
		dCenterView.setY(75);

		closeButton.setLayoutX(400);
		closeButton.setLayoutY(260);
		closeButton.setOnMouseClicked(e -> closeWindow());
		
		instructView.setX(250);
		instructView.setY(300);
		
		doneView.setX(320);
		doneView.setY(220);

		dLeftView.setOnScrollFinished(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {

				if (event.getTotalDeltaX() > 100) {
					System.out.println("swiped right");
					dLeftView.setX(500);
					isDLeftOutofBounds = true;

				} else if (event.getTotalDeltaX() < -100) {
					dLeftView.setX(-100);
					isDLeftOutofBounds = true;

				} else if (event.getTotalDeltaY() < -100) {
					dLeftView.setY(-120);
					isDLeftOutofBounds = true;
				} else if (event.getTotalDeltaY() > 100) {
					dLeftView.setY(320);
					isDLeftOutofBounds = true;
				}
				
				if (isDLeftOutofBounds && isDCenterOutofBounds && isDRightOutofBounds) {
					challengeWon();
				}

				System.out.println("DLeft: " + isDLeftOutofBounds);
			}
		});

		dRightView.setOnScrollFinished(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {

				if (event.getTotalDeltaX() > 100) {
					System.out.println("swiped right");
					dRightView.setX(650);
					isDRightOutofBounds = true;

				} else if (event.getTotalDeltaX() < -100) {
					dRightView.setX(-100);
					isDRightOutofBounds = true;

				} else if (event.getTotalDeltaY() < -100) {
					dRightView.setY(-120);
					isDRightOutofBounds = true;
				} else if (event.getTotalDeltaY() > 100) {
					dRightView.setY(320);
					isDRightOutofBounds = true;
				}

				if (isDLeftOutofBounds && isDCenterOutofBounds && isDRightOutofBounds) {
					challengeWon();
				}
				System.out.println("DRight :" + isDRightOutofBounds);
			}
		});
		
		dCenterView.setOnScrollFinished(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {

				if (event.getTotalDeltaX() > 100) {
					dCenterView.setX(650);
					isDCenterOutofBounds = true;

				} else if (event.getTotalDeltaX() < -100) {
					dCenterView.setX(0);
					isDCenterOutofBounds = true;

				} else if (event.getTotalDeltaY() < -100) {
					dCenterView.setY(-210);
					isDCenterOutofBounds = true;
				} else if (event.getTotalDeltaY() > 100) {
					dCenterView.setY(320);
					isDCenterOutofBounds = true;
				}
				
				if (isDLeftOutofBounds && isDCenterOutofBounds && isDRightOutofBounds) {
					challengeWon();
				}

				System.out.println("DCenter :" + isDCenterOutofBounds);
			}
		});

		root.getChildren().addAll(bgView, dLeftView, dRightView, dCenterView, instructView);
		return root;
	}
	
	public void challengeWon() {
		instructView.setImage(null);
		root.getChildren().addAll(doneView, closeButton);
		gameModel.setClearedDementors(true);
		System.out.println("challenge won!");
	}
	
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

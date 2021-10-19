package controller;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.GameModel;

import model.MenuResult;
import scene.SnakeScene;
import scene.SwipeScene;
import scene.WandScene;
import setup.selectCharacterScene;
import setup.setUpApp;

public class GesturesController {

	private boolean isAdvanced = false;
	static selectCharacterScene RoleController = new selectCharacterScene();
	static MenuResult resultModel = new MenuResult();

	setUpApp menu;
	private Rectangle character;
	ImageView iv;
	int curseur = 0;
	int nomcharacters = 4;
	int nomselection = 0;
	private int selectedRole = 0;
	GameModel gameModel;

	public GesturesController() {

	}

	public boolean getDifficultyResult() {
		return resultModel.getDifficulty();
	}

	public int getRoleResult() {
		return resultModel.getRole();
	}

	public static void launchGame(boolean difficulty, int playerType) {
		Pane root = new Pane();
		GameController controller = new GameController(difficulty, playerType);
		root.getChildren().add(controller.getGameView());
		Scene gameScene = new Scene(root, 1040, 700);
		Stage gameStage = new Stage();
		gameStage.setScene(gameScene);
		gameStage.setTitle("HP Maze");
		gameStage.show();

	}

	public void RotateEvent(ImageView wand, ImageView button, boolean isAdv) {

		wand.setOnRotate(new EventHandler<RotateEvent>() {
			@Override
			public void handle(RotateEvent event) {
				// System.out.print("Rotating:"+img.getRotate()+ "Angel:"+event.getAngle());

				if (wand.getRotate() < 110) {
					wand.setRotate(wand.getRotate() + event.getAngle());
				}

				if (wand.getRotate() >= 110 && isAdv) {

					button.setOpacity(1);
					setAdvanced(true);
					resultModel.setDifficulty(true);

				}
				if (wand.getRotate() >= 110 && isAdv != true) {
					button.setOpacity(1);

				}

				System.out.print("This is the result:" + isAdvanced + resultModel.getDifficulty());
				event.consume();
			}

		});

	}

	public void setAdvanced(boolean isAdvanced) {

		this.isAdvanced = isAdvanced;
	}

	public boolean getLevel() {
		return isAdvanced;
	}

	public static void SwipeEvent(Button shape) {

		shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				try {

					launchGame(resultModel.getDifficulty(), resultModel.getRole());

				} catch (Exception e) {

					e.printStackTrace();
				}
				event.consume();
			}
		});

	}

	public static void ScrollEvent(ImageView key, ImageView lock) {

		key.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (!event.isInertia()) {

					System.out.print("Scrolling:" + key.getTranslateX());

					if (key.getTranslateX() < 193) {

						key.setTranslateX(key.getTranslateX() + event.getDeltaX());
						key.setTranslateY(key.getTranslateY() + event.getDeltaY());

					} else {
						lock.setOpacity(1);
						try {
							RoleController.makeCharacterScene();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});
	}

	public static void ZoomEvent(Pane shape, Stage window) {

		shape.setOnZoom(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {

				System.out.print("Scrolling:" + shape.getScaleX());
				if (shape.getScaleX() < 0.5) {
					shape.setScaleX(shape.getScaleX() * event.getZoomFactor());
					shape.setScaleY(shape.getScaleY() * event.getZoomFactor());
				} else {
					;
					RoleController.closeWindow();
					window.close();

				}

				event.consume();
			}
		});

		shape.setOnZoomStarted(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
				// inc(shape);

				// log("Rectangle: Zoom event started");
				event.consume();
			}
		});

		shape.setOnZoomFinished(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
				// dec(shape);
				// log("Rectangle: Zoom event finished");
				event.consume();
			}
		});
	}

	// Insert picture
	public void insertImage(Image i, VBox vb1, int ID) {

		iv = new ImageView();
		iv.setImage(i);

		setupGestureSource(iv, ID);
		vb1.getChildren().add(iv);
	}

	public void setupGestureSource(final ImageView source, int ID) {

		source.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();

				Image sourceImage = source.getImage();

				content.putImage(sourceImage);
				db.setContent(content);

				iv = source;

				resultModel.setRole(ID);

				System.out.print("You have selected:" + resultModel.getRole());
				event.consume();
			}
		});

		source.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				source.setCursor(Cursor.HAND);
//	                    System.out.println("e...: "+e.getSceneX());

				curseur = (int) e.getSceneX();
			}
		});
	}

	public void setupGestureTarget(final VBox selectedCharacter) {

		selectedCharacter.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {

				Dragboard db = event.getDragboard();

				if (db.hasImage()) {
					event.acceptTransferModes(TransferMode.MOVE);

				}

				event.consume();
			}
		});

		selectedCharacter.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {

				Dragboard db = event.getDragboard();

				if (db.hasImage()) {

					iv.setImage(db.getImage());
					Point2D localPoint = selectedCharacter
							.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));

					selectedCharacter.getChildren().remove(iv);
					iv.setX((int) (localPoint.getX() - iv.getBoundsInLocal().getWidth() / 2));
					iv.setY((int) (localPoint.getY() - iv.getBoundsInLocal().getHeight() / 2));

					selectedCharacter.getChildren().add(iv);

					if (curseur > 600 && event.getSceneX() > 600) {
						nomcharacters = nomcharacters + 0;
					} else if (curseur > 600 && event.getSceneX() < 600) {
						nomcharacters--;
						nomselection++;

					} else if (curseur < 600 && event.getSceneX() < 600) {
						nomselection = nomselection + 0;
					} else if (curseur < 600 && event.getSceneX() > 600) {
						nomcharacters++;
						nomselection--;

					}
					event.setDropCompleted(true);

					event.setDropCompleted(false);
				}
				event.consume();
			}

		});

	}

}

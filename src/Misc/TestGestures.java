package Misc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestGestures extends Application {
	 public static void main(String[] args) {
	        Application.launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Hello World");
	        
	        FlowPane root = new FlowPane();
	        root.setAlignment(Pos.CENTER);
	        
	        Rectangle rect = new Rectangle(200,200);
			
	        Button btn = new Button("Hello World");
	        
	        btn.setOnRotate(new EventHandler<RotateEvent>() {
	        	@Override
	            public void handle(RotateEvent event) {
	                System.out.println(event);
	            }
	        });
	        
//	        rect.setOnRotate(new EventHandler<RotateEvent>() {
//	            @Override public void handle(RotateEvent event) {
//	                rect.setRotate(rect.getRotate() + event.getAngle());
//	                System.out.println(event);
//	               // event.consume();
//	            }
//	        });

	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	            public void handle(ActionEvent event) {
	                System.out.println(event);
	            }
	        });
	        
	    	Image pharaohImg = new Image("file:img/pharaoh.png");
	    	ImageView pharaohView = new ImageView(pharaohImg);
	    	
	    	
//	        pharaohView.setOnRotate(new EventHandler<RotateEvent>() {
//	            @Override public void handle(RotateEvent event) {
//	                rect.setRotate(rect.getRotate() + event.getAngle());
//	                System.out.println(event);
//	                event.consume();
//	            }
//	        });
	        
//	        pharaohView.setOnZoom(new EventHandler<ZoomEvent>()){
//	        	
//	        }
	        
	        pharaohView.setOnZoom(new EventHandler<ZoomEvent>() {
	            @Override public void handle(ZoomEvent event) {
	            	pharaohView.setScaleX(pharaohView.getScaleX() * event.getZoomFactor());
	            	pharaohView.setScaleY(pharaohView.getScaleY() * event.getZoomFactor());
	            	System.out.println(event);
	            }
	        });
	    	
	        Scene scene = new Scene(root, 1200, 800);
	        root.getChildren().addAll(btn, rect, pharaohView);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
}

package setup;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.MenuResult;

public class draggableMaker {
	private Rectangle character;
	ImageView iv;
	int curseur = 0;
	int nomcharacters = 4; 
    int nomselection = 0; 
    private int selectedRole = 0;  
    MenuResult resultModel = new MenuResult();
    
	
    
    
    
public void setRole(int selectedRole) {
	
	this.selectedRole = selectedRole;
}
    
   
 public int getRole()   {
	 
	 return selectedRole;
	 
 }
	
	
	
 
 
//Insert picture	
public void insertImage(Image i, VBox vb1, int ID){
        
        iv = new ImageView();
        iv.setImage(i);
         
        
        setupGestureSource(iv, ID);
        vb1.getChildren().add(iv);
    }
	



	
public void setupGestureSource(final ImageView source, int ID){
        
	
	
        source.setOnDragDetected(new EventHandler <MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {

             
               Dragboard db = source.startDragAndDrop(TransferMode.MOVE);           
               ClipboardContent content = new ClipboardContent();
               
               Image sourceImage = source.getImage();
               
               content.putImage(sourceImage);
               db.setContent(content);

               iv = source;               
               
               resultModel.setRole(ID);
               setRole(ID);
               
              
               
               System.out.print("You have selected:"+ resultModel.getRole());
               event.consume();
           }
       });
        
            source.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    source.setCursor(Cursor.HAND);
//                    System.out.println("e...: "+e.getSceneX());
                    
                    curseur = (int) e.getSceneX();
                }
            });
    }
	
	
	
	
public void setupGestureTarget(final VBox selectedCharacter){
	
	
	
	
        
        selectedCharacter.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
 
                Dragboard db = event.getDragboard();
                
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.MOVE);
                    
                }
                 
                event.consume();
            }
        });
 
        selectedCharacter.setOnDragDropped(new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {
  
                Dragboard db = event.getDragboard();
 
                if(db.hasImage()){
 
                    iv.setImage(db.getImage());
                    

                    Point2D localPoint = selectedCharacter.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));

//                    System.out.println("event.getSceneX : "+event.getSceneX());
//                    System.out.println("localPoint.getX : "+localPoint.getX());
//                    System.out.println("********");
 
                    selectedCharacter.getChildren().remove(iv);

                    iv.setX((int)(localPoint.getX() - iv.getBoundsInLocal().getWidth()  / 2)  );
                    iv.setY((int)(localPoint.getY() - iv.getBoundsInLocal().getHeight() / 2) );
                    
                    
                    

                    selectedCharacter.getChildren().add(iv);
                    
                    
                    
                if(curseur > 600 && event.getSceneX() > 600){
                	nomcharacters = nomcharacters+0;  
                 }else if(curseur > 600 && event.getSceneX() < 600){
                	 nomcharacters--;
                    nomselection++;
                    
                 }else if(curseur < 600 && event.getSceneX() < 600){
                     nomselection = nomselection +0; 
                 }else if(curseur < 600 && event.getSceneX() >600){
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

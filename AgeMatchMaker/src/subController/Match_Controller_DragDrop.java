package subController;


import mainController.Match_Controller;
import match.Match_Zuweisung;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import database.Load_Database;

public class Match_Controller_DragDrop extends Match_Controller{
	
	private Match_Controller controller;
	
	
	public Match_Controller_DragDrop(Match_Controller controller){
		this.controller = controller;
	}
	
	public void dragDropPlayersItems(DragEvent event){
		
		Load_Database db = new Load_Database();
		Dragboard dboard = event.getDragboard () ;
        Button but = new Button();
        boolean success = false ;
        boolean noDoubleName=true;
        
        but = (Button)event.getGestureTarget();
        String players[]=new String[8];
        setStringArrayPlayers(players);
        
        for(int i=0;i<players.length;i++){
        	if(dboard.getString().equals(players[i])){
        		noDoubleName = false;
        	}
        }
        
        for(int i=0;i<db.getLoadPlayers().size();i++){
        	if(noDoubleName && db.getLoadPlayers().get(i).getName().equals(dboard.getString())){
            	but.setText(dboard.getString());
            	success = true ;
            }
        }
        
        setStringArrayPlayers(players);
        
        
        event.setDropCompleted(success);
        event.consume();
        
        createMatch();
	}

	private void setStringArrayPlayers(String array[]){
		for(int i=0,j=0;i<array.length;i++,j++){
        	if(i<=3){
        		array[i]=controller.getBtList_TeamA().get(j).getText();
        		if(i==3){
        			j=-1;
        		}
        	}else{
        		array[i]=controller.getBtList_TeamB().get(j).getText();
        	}
        }
	}
	
	public void dragDropMapsItems(DragEvent event){
		
		Load_Database db = new Load_Database();
		Dragboard dboard = event.getDragboard() ;
		Button but = new Button();
        boolean success = false ;
        
        but= (Button)event.getGestureTarget();
        for(int i=0;i<db.getLoadMaps().size();i++){
        	if(dboard.hasString() && dboard.getString().equals(db.getLoadMaps().get(i).getName())){
        		chosenMap = dboard.getString();
        		but.setText(dboard.getString());
             	success = true ;
            }
        }
        
        
        event.setDropCompleted(success);
        event.consume();
        
        createMatch();
	}
	
	public void dragDropNationsItems(DragEvent event){
		
		Load_Database db = new Load_Database();
		Dragboard dboard = event.getDragboard () ;
        Button but = new Button();
        boolean success = false ;
        
        but = (Button)event.getGestureTarget();
        String nations[]=new String[8];
        setStringArrayNations(nations);
        
        
        //For Schleife nötig damit nur die Nationen-Namen erscheinen und keine Oberbegriffe wie "Non-Paladine" oder Paladine
        for(int i=0;i<db.getLoadNations().size();i++){
        	if(dboard.hasString() && dboard.getString().equals(db.getLoadNations().get(i).getName())){
        		but.setText(dboard.getString());
             	success = true ;
            }
        }
        setStringArrayNations(nations);
        
        event.setDropCompleted(success);
        event.consume();
        
        createMatch();
        
	}
	
	private void setStringArrayNations(String array[]){
		for(int i=0,j=0;i<array.length;i++,j++){
        	if(i<=3){
        		array[i]=controller.getBtList_NationA().get(j).getText();
        		if(i==3){
        			j=-1;
        		}
        	}else{
        		array[i]=controller.getBtList_NationB().get(j).getText();
        	}
        }
	}
	
	private void createMatch(){
		Match_Zuweisung zuweisung = new Match_Zuweisung(controller);
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_1(), controller.getNation_Slot_1(), controller.getMap_Slot(),"teamA");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_2(), controller.getNation_Slot_2(), controller.getMap_Slot(),"teamA");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_3(), controller.getNation_Slot_3(), controller.getMap_Slot(),"teamA");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_4(), controller.getNation_Slot_4(), controller.getMap_Slot(),"teamA");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_5(), controller.getNation_Slot_5(), controller.getMap_Slot(),"teamB");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_6(), controller.getNation_Slot_6(), controller.getMap_Slot(),"teamB");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_7(), controller.getNation_Slot_7(), controller.getMap_Slot(),"teamB");
        zuweisung.setAssignmentToPlayers(controller.getPlayer_Slot_8(), controller.getNation_Slot_8(), controller.getMap_Slot(),"teamB");
    }
	
	

}

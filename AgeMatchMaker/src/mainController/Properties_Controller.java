package mainController;

import dialogWindow.DialogWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class Properties_Controller {
	
	private Match_Controller tabMatch_Controller;
	
	@FXML private ToggleButton drawingMatch;
	@FXML private ToggleButton doubleNation;
	@FXML private ToggleButton mirrorNation;
	@FXML private Button newPlayer;
	@FXML private Button newNation;
	@FXML private Button newMap;
	
	
	private static boolean isDoubleNation = true, isMirrorNation, isDrawing;
	
	
	@FXML
	public void isDoubleNations(MouseEvent event){
		
		if(doubleNation.isSelected()==false){
			doubleNation.setText("ON");
			isDoubleNation = true;
		}else{
			doubleNation.setText("OFF");
			isDoubleNation = false;
		}
	}
	
	@FXML
	public void isMirrorNations(MouseEvent event){
		
		if(mirrorNation.isSelected()==false){
			mirrorNation.setText("ON");
			isMirrorNation = true;
		}else{
			mirrorNation.setText("OFF");
			isMirrorNation = false;
		}
	}
	
	@FXML
	public void isDrawingMatch(){
		
		if(drawingMatch.isSelected()==false){
			isDrawing = false;
			drawingMatch.setText("ON");
			tabMatch_Controller.getDrawing().setDisable(false);
			tabMatch_Controller.getReset().setVisible(false);
		}else{
			isDrawing = true;
			drawingMatch.setText("OFF");
			tabMatch_Controller.getDrawing().setDisable(true);
			tabMatch_Controller.getReset().setVisible(true);
		}
	}
	
	@FXML
	public void createNewPlayer(){
		DialogWindow dialogWindow = new DialogWindow();
		dialogWindow.dialogWindow_createNewPlayer();
	}
	
	@FXML
	public void createNewNation(){
		DialogWindow dialogWindow = new DialogWindow();
		dialogWindow.dialogWindow_createNewNation();
		
	}
	
	@FXML
	public void createNewMap(){
		DialogWindow dialogWindow = new DialogWindow();
		dialogWindow.dialogWindow_createNewMap();
	}
	
	public static boolean isDoubleNation() {
		return isDoubleNation;
	}

	public static boolean isMirrorNation() {
		return isMirrorNation;
	}

	public void init(Match_Controller tabMatch_Controller) {
		this.tabMatch_Controller = tabMatch_Controller;
	}

	public static boolean isDrawing() {
		return isDrawing;
	}

}

package mainController;

import subController.Match_Controller_MapPreview;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Main_Controller {

	@FXML private TreeView_Controller controlller;

	@FXML private TreeView<Object> treeview_Players;
	@FXML private TreeView<Object> treeview_Maps;
	@FXML private TreeView<Object> treeview_Nations;

	@FXML private Accordion accordion;
	@FXML private TitledPane playersPane;

	@FXML private Properties_Controller properties_Controller;
	@FXML private Match_Controller tabMatch_Controller;
	@FXML private Elo_Controller tabeElo_Controller;
	
	
	public void initialize(){
		TreeView_Controller treeview_Controller = new TreeView_Controller(treeview_Players,treeview_Maps,treeview_Nations);

		accordion.setExpandedPane(playersPane);

		properties_Controller.init(tabMatch_Controller);
		tabMatch_Controller.init(tabeElo_Controller);
		tabMatch_Controller.getSave().setDisable(true);
	}

	@FXML
	public void mapPreview(MouseEvent event){

		//Try&Catch notwendig. Es wird eine Exception geworfen sobald ein Treeview Baum nicht korrekt selektiert wird,
		//dadurch ensteht eine Nullpointerexception
		try{
			if(event.getButton() == MouseButton.SECONDARY ){
				Match_Controller_MapPreview mapPrev = new Match_Controller_MapPreview();
				String chosenMap = treeview_Maps.getSelectionModel().getSelectedItem().getValue().toString();
				mapPrev.mapPreview(chosenMap);
			}
		}
		catch(NullPointerException ex){

		}
	}
}

package mainController;

import java.util.ArrayList;

import org.controlsfx.control.CheckListView;

import database.Load_Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChoosePlayers_Controller {

	@FXML private Button cancelButton;
	@FXML private Button okButton;
	@FXML private CheckListView<String> playerCheckList;
	private ObservableList<String> playerOptions;
	private ArrayList<String> list;
	public static ArrayList<String> pickedPlayers = new ArrayList<String>();;


	public void initialize(){
		if(pickedPlayers.size()>=4){
			pickedPlayers.clear();
		}

		Load_Database dbPlayer = new Load_Database();
		list = new ArrayList<String>();

		for(int i=0;i<dbPlayer.getLoadPlayers().size();i++){
			list.add(dbPlayer.getLoadPlayers().get(i).getName());
		}

		if(Match_Controller.btList_TeamA.size()>0){
			for(int i=0;i<list.size();i++){
				for(int j=0;j<Match_Controller.btList_TeamA.size();j++){
					if(list.get(i).equals(Match_Controller.btList_TeamA.get(j).getText())){
						list.remove(i);
					}
				}
			}
		}


		playerOptions = FXCollections.observableList(list);
		playerCheckList.setItems(playerOptions);

	}

	@FXML
	public void pressedOk(){
		Stage stage = (Stage)okButton.getScene().getWindow();

		pickedPlayers.addAll(playerCheckList.getCheckModel().getCheckedItems());
		int size = pickedPlayers.size();
		for(int i=0;i<size;i++){
			if(i>=4){
				pickedPlayers.remove(pickedPlayers.size()-1);
			}
		}

		stage.close();
	}

	@FXML
	public void pressedCancel(){
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.close();
	}

}

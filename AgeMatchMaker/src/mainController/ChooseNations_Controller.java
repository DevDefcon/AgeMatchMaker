package mainController;

import java.util.ArrayList;
import java.util.Random;

import org.controlsfx.control.CheckListView;

import database.Load_Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class ChooseNations_Controller {

	@FXML private Button cancelButton;
	@FXML private Button playerDialogOkButton;
	@FXML private Button randomButton;
	@FXML private CheckListView<String> nationCheckList;
	@FXML private Spinner<Integer> limitSpinner;
	private ObservableList<String> nationOptions;
	private ArrayList<String> list;
	public static ArrayList<String> pickedNations = new ArrayList<String>();;


	public void initialize(){
		limitSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4));
		if(pickedNations.size()>=4){
			pickedNations.clear();
		}

		Load_Database dbNation = new Load_Database();
		list = new ArrayList<String>();

		for(int i=0;i<dbNation.getLoadNations().size();i++){
			list.add(dbNation.getLoadNations().get(i).getName());
		}
		nationOptions = FXCollections.observableList(list);
		nationCheckList.setItems(nationOptions);

	}

	@FXML
	public void pressedOk(){
		Stage stage = (Stage)playerDialogOkButton.getScene().getWindow();

		pickedNations.addAll(nationCheckList.getCheckModel().getCheckedItems());
		int size = pickedNations.size();
		for(int i=0;i<size;i++){
			if(i>=4){
				pickedNations.remove(pickedNations.size()-1);
			}
		}

		stage.close();
	}

	@FXML
	public void pressedCancel(){
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.close();
	}


	//Dieser Methode wurde eingebaut um eine massive umcodierung des ganzen Programm zu umgehen.
	//Es ist umfangreicher als angenommen fixe Teams zu implementieren mit zufälligen Nationen
	@FXML
	public void pressedRandom(){
		//System.out.println(limitSpinner.getValue());
		pickedNations.clear();
		int limit = limitSpinner.getValue();
		Stage stage = (Stage)randomButton.getScene().getWindow();
		Load_Database dbNation = new Load_Database();
		Random random = new Random();

		if(Properties_Controller.isDoubleNation()==true){
			for(int i=0;i<limit;i++){
				int index = random.nextInt(dbNation.getLoadNations().size());
				pickedNations.add(dbNation.getLoadNations().get(index).getName());
			}
		}else{
			for(int i=0;i<limit;i++){
				int index = random.nextInt(dbNation.getLoadNations().size());
				pickedNations.add(dbNation.getLoadNations().get(index).getName());
				dbNation.getLoadNations().remove(index);
			}
		}


		stage.close();
	}


}

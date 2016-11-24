package dialogWindow;

import java.util.Optional;

import database.CreateNewMap;
import database.CreateNewNation;
import database.CreateNewPlayer;
import database.Load_Database;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DialogWindow {
	
	private ButtonType buttonTeamA, buttonTeamB, buttonCancel;
	private Optional<ButtonType> result;
	
	public void dialogWindow_Alert_Save(){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Resultat des Matches.");
		alert.setHeaderText("Welches Team hat gewonnen?");
		alert.setContentText("Bitte wähle eine Option.");

		buttonTeamA = new ButtonType("Team A");
		buttonTeamB = new ButtonType("Team B");
		buttonCancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTeamA, buttonTeamB, buttonCancel);

		result = alert.showAndWait();
	}

	public void dialogWindow_createNewMap(){
		
		Load_Database db = new Load_Database();
		boolean schalter = true;
		
		String message = "Neue Karte hinzufügen.";
		String alertMessage ="";
		
		do{
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setTitle("Neue Karte hinzufügen.");
			dialog.setHeaderText(message+alertMessage);
			
			ButtonType okButton = new ButtonType("Bestätigen", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField mapName_TextField = new TextField();
			mapName_TextField.setPromptText("Kartenname");
			
			
			ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(
				    "Land", "Wasser", "Fun")
			);
			
			grid.add(new Label("Kartenname:"), 0, 0);
			grid.add(mapName_TextField, 1, 0);
			grid.add(new Label("Kategorie:"), 0, 1);
			grid.add(cb, 1, 1);

			dialog.getDialogPane().setContent(grid);
			
			Optional<ButtonType> result = dialog.showAndWait();
			
			String takenMap = mapName_TextField.getText().trim();
			String mapName = mapName_TextField.getText().trim().toLowerCase();
			String dbMap = "";
			
			if(result.get() == ButtonType.CANCEL){
				System.out.println("Cancel");
				schalter = false;
			}else{
				
				for(int i=0;i<db.getLoadMaps().size();i++){
					if(mapName.equals(db.getLoadMaps().get(i).getName().toLowerCase())){
						dbMap = db.getLoadMaps().get(i).getName().toLowerCase();
					}
				}
				
				if(mapName.matches("\\p{javaWhitespace}*")){

					alertMessage = "\nKartenname besteht nur aus Leerzeichen!";
					
				}else if(cb.getSelectionModel().isEmpty()){

					alertMessage = "\nEs wurde keine Kategorie ausgewählt!";
					
				}else if(mapName.equals(dbMap)){

					alertMessage = "\nKartenname ist schon vorhanden!";

				}else{
					
					CreateNewMap newMap = new CreateNewMap(takenMap, cb.getSelectionModel().getSelectedItem());
					schalter= false;
				}
			}
		}while(schalter);
	}
	
	
	public void dialogWindow_createNewNation(){
		
		Load_Database db = new Load_Database();
		boolean schalter = true;
		
		String message = "Neues Volk hinzufügen.";
		String alertMessage ="";
		
		do{
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setTitle("Neues Volk hinzufügen");
			dialog.setHeaderText(message+alertMessage);
			
			ButtonType okButton = new ButtonType("Bestätigen", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField nationName_TextField = new TextField();
			nationName_TextField.setPromptText("Volkname");
			
			
			ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(
				    "Paladin", "NonPaladin")
			);
			
			grid.add(new Label("Volkname:"), 0, 0);
			grid.add(nationName_TextField, 1, 0);
			grid.add(new Label("Kategorie:"), 0, 1);
			grid.add(cb, 1, 1);

			dialog.getDialogPane().setContent(grid);
			
			Optional<ButtonType> result = dialog.showAndWait();
			
			String takenNation = nationName_TextField.getText().trim();
			String nationName = nationName_TextField.getText().trim().toLowerCase();
			String dbNation = "";
			
			if(result.get() == ButtonType.CANCEL){
				System.out.println("Cancel");
				schalter = false;
			}else{
				
				
				for(int i=0;i<db.getLoadNations().size();i++){
					if(nationName.equals(db.getLoadNations().get(i).getName().toLowerCase())){
						dbNation = db.getLoadNations().get(i).getName().toLowerCase();
					}
				}
				
				if(nationName.matches("\\p{javaWhitespace}*")){
					
					alertMessage = "\nVolkname besteht nur aus Leerzeichen!";
					
				}else if(cb.getSelectionModel().isEmpty()){
					
					alertMessage = "\nEs wurde keine Kategorie ausgewählt!";
					
				}else if(nationName.equals(dbNation)){

					alertMessage = "\nVolkname ist schon vorhanden!";

				}else{
					System.out.println(nationName);
					System.out.println(dbNation);
					CreateNewNation newNation = new CreateNewNation(takenNation, cb.getSelectionModel().getSelectedItem());
					schalter= false;
				}
				
			}
		}while(schalter);
	}
	
	
	public void dialogWindow_createNewPlayer(){
		
		Load_Database db = new Load_Database();
		boolean schalter = true;
		
		String message = "Neuen Spieler hinzufügen.";
		String alertMessage ="";
		
		do{
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setTitle("Neuen Spieler hinzufügen.");
			dialog.setHeaderText(message+alertMessage);
			
			ButtonType okButton = new ButtonType("Bestätigen", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField playerName_TextField = new TextField();
			playerName_TextField.setPromptText("Spielername");
			
			TextField playerRating = new TextField();
			playerRating.setPromptText("Rating des Spielers");
			
			
			grid.add(new Label("Spielername:"), 0, 0);
			grid.add(playerName_TextField, 1, 0);
			grid.add(new Label("Rating:"), 0, 1);
			grid.add(playerRating, 1, 1);

			dialog.getDialogPane().setContent(grid);
			
			Optional<ButtonType> result = dialog.showAndWait();
			
			String takenPlayer = playerName_TextField.getText();
			String playerName = playerName_TextField.getText().trim().toLowerCase();
			String dbPlayer = "";
			
			if(result.get() == ButtonType.CANCEL){
				System.out.println("Cancel");
				schalter = false;
			}else{
				
				
				for(int i=0;i<db.getLoadPlayers().size();i++){
					if(playerName.equals(db.getLoadPlayers().get(i).getName().toLowerCase())){
						dbPlayer = db.getLoadPlayers().get(i).getName().toLowerCase();
					}
				}
				
					
				if(playerName.matches("\\p{javaWhitespace}*")){
					
					alertMessage = "\nName besteht nur aus Leerzeichen!";
					
				}else if(playerRating.getText().isEmpty()){
					
					alertMessage = "\nRating ist leer!";
					
				}else if(playerName.toLowerCase().equals(dbPlayer)){

					alertMessage = "\nDer Name ist schon vorhanden!";

				}else{
					
					int rating = Integer.parseInt(playerRating.getText());
					CreateNewPlayer newPlayer = new CreateNewPlayer(takenPlayer, rating);
					schalter= false;
				}
				
			}
		}while(schalter);
	}
	
	
	public void dialogWindow_Alert_MatchConditions_1(){
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warnung");
		alert.setHeaderText("Hinweis!");
		alert.setContentText("Mehr als 8 Spieler in der Auswahlliste vorhanden.\nMaximal 8 Spieler in die Auswahlliste");

		alert.showAndWait();
	}
	
	public void dialogWindow_Alert_MatchConditions_2(){
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warnung");
		alert.setHeaderText("Hinweis!");
		alert.setContentText("Weniger als 8 Spieler in der Auswahlliste vorhanden.\nDie Mindestanzahl an Spielern beträgt 2 Spieler.");

		alert.showAndWait();
	}
	
	public void dialogWindow_Alert_MatchConditions_3(){
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warnung");
		alert.setHeaderText("Hinweis!");
		alert.setContentText("Weniger als 1 Volk und/oder Karte in der Auswahlliste vorhanden.\nDie Mindestanzahl beträgt 1 Karte/Volk.");

		alert.showAndWait();
	}
	
	public ButtonType getButtonTeamA() {
		return buttonTeamA;
	}

	public ButtonType getButtonTeamB() {
		return buttonTeamB;
	}

	public ButtonType getButtonCancel() {
		return buttonCancel;
	}

	public Optional<ButtonType> getResult() {
		return result;
	}

}

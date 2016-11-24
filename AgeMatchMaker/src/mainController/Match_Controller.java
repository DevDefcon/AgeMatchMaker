package mainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import match.Match_Auslosung;
import database.Load_Database;
import database.Maps;
import database.Nationen;
import database.Players;
import database.Teams;
import dialogWindow.DialogWindow;
import subController.Match_Controller_DragDrop;
import subController.Match_Controller_MapPreview;
import subController.Match_Controller_Save;



public class Match_Controller {

	@FXML private Button player_Slot_1;
	@FXML private Button player_Slot_2;
	@FXML private Button player_Slot_3;
	@FXML private Button player_Slot_4;
	@FXML private Button player_Slot_5;
	@FXML private Button player_Slot_6;
	@FXML private Button player_Slot_7;
	@FXML private Button player_Slot_8;

	@FXML private Button nation_Slot_1;
	@FXML private Button nation_Slot_2;
	@FXML private Button nation_Slot_3;
	@FXML private Button nation_Slot_4;
	@FXML private Button nation_Slot_5;
	@FXML private Button nation_Slot_6;
	@FXML private Button nation_Slot_7;
	@FXML private Button nation_Slot_8;

	@FXML private Button map_Slot;

	@FXML private Button save;
	@FXML private Button drawing;
	@FXML private Button reset;

	@FXML private Button playerDialogOkButton;

	private Elo_Controller tabeElo_Controller;
	private Match_Controller_DragDrop dragDrop;
	private ChooseNations_Controller nationController;
	private boolean isCheck_Conditions;

	public static ArrayList<Button> btList_TeamA, btList_TeamB, btList_NationA, btList_NationB;

	private CheckBoxTreeItem<Object> treeItemCollector, treeItemCollector_Karte, treeItemCollector_Voelker, treeItemCollector_Spieler;

	//ArrayListen welche die ausgew‰hlten(Checkbox Haken) Spieler/Nationen/Karten enthalten
	protected static ArrayList<Maps> matchMapsList 			= new ArrayList<Maps>();
	protected static ArrayList<Nationen> matchNationsList 	= new ArrayList<Nationen>();
	protected static ArrayList<Players> matchPlayersList 	= new ArrayList<Players>();
	protected static String chosenMap;

	//NationDialog Auswahl
	private Stage stage;
	private Parent root;



	//Auslosungs Methode
	@FXML
	public void drawing(MouseEvent event){
		clearArrayLists();
		setMatch();
		tabeElo_Controller.createTables();
	}

	@FXML
	public void save(MouseEvent event){
		Match_Controller_Save saveButton = new Match_Controller_Save();
	}

	@FXML
	public void reset(MouseEvent event){
		tabeElo_Controller.clearTables();
		save.setDisable(true);
		boolean isReset = true;
		clearArrayLists();
		showMatchScreen(isReset);
		map_Slot.setText("map_Slot");
	}


	@FXML
	public void mapPreviewButton(MouseEvent event){
		
		if(chosenMap != null){
			Match_Controller_MapPreview mapPrev = new Match_Controller_MapPreview();
			mapPrev.mapPreview(chosenMap);
		}
		//Wenn Auslosung ausgeschaltet wurde, kann mit einem klick auf dem Map Button eine zuf‰llig generierte Map erstellen.
		if(Properties_Controller.isDrawing()==true){
			Load_Database db = new Load_Database();
			for(int i=0;i<db.getLoadMaps().size();i++){
				kartenfactor(i,db);
			}
			Match_Auslosung onlyMap = new Match_Auslosung(matchMapsList);
			map_Slot.setText(onlyMap.setRandomMapWithoutPlayers());
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////																				/////////////////
/////////////////				Manuel DialogBox to choose Nation/Player						/////////////////


	@FXML
	public void setNationTeamA() throws IOException{
		if(Properties_Controller.isDrawing()==true){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/matchMakerView/MatchMaker_DialogNation.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("W‰hle Vˆlker");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			collectButton();
			checkSaveButton();
			for(int i=0;i<ChooseNations_Controller.pickedNations.size();i++){
				btList_NationA.get(i).setText(ChooseNations_Controller.pickedNations.get(i));
			}
		}
	}

	@FXML
	public void setNationTeamB() throws IOException{
		if(Properties_Controller.isDrawing()==true){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/matchMakerView/MatchMaker_DialogNation.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("W‰hle Vˆlker");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			collectButton();
			checkSaveButton();
			for(int i=0;i<ChooseNations_Controller.pickedNations.size();i++){
				btList_NationB.get(i).setText(ChooseNations_Controller.pickedNations.get(i));
			}
		}
	}

	@FXML
	public void setPlayerTeamA() throws IOException{
		if(Properties_Controller.isDrawing()==true){
			collectButton();
			checkSaveButton();

			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/matchMakerView/MatchMaker_DialogPlayer.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("W‰hle Spieler");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();

			for(int i=0;i<ChoosePlayers_Controller.pickedPlayers.size();i++){
				btList_TeamA.get(i).setText(ChoosePlayers_Controller.pickedPlayers.get(i));
			}
		}
	}

	@FXML
	public void setPlayerTeamB() throws IOException{
		if(Properties_Controller.isDrawing()==true){
			collectButton();
			checkSaveButton();

			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/matchMakerView/MatchMaker_DialogPlayer.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("W‰hle Spieler");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();

			for(int i=0;i<ChoosePlayers_Controller.pickedPlayers.size();i++){
				btList_TeamB.get(i).setText(ChoosePlayers_Controller.pickedPlayers.get(i));
			}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////																				/////////////////
/////////////////									Drag&Drop									/////////////////

	@FXML
	public void dragDropPlayersItems(DragEvent event){
		if(Properties_Controller.isDrawing()==true){
			clearArrayLists();
			setMatch_Controller_DragDrop();
			collectButton();
			dragDrop.dragDropPlayersItems(event);
			checkSaveButton();
		}
	}

	@FXML
	public void dragDropMapsItems(DragEvent event){
		if(Properties_Controller.isDrawing()==true){
			clearArrayLists();
			setMatch_Controller_DragDrop();
			dragDrop.dragDropMapsItems(event);
			checkSaveButton();
		}
	}

	@FXML
	public void dragDropNationsItems(DragEvent event){
		if(Properties_Controller.isDrawing()==true){
			clearArrayLists();
			setMatch_Controller_DragDrop();
			collectButton();
			dragDrop.dragDropNationsItems(event);
			checkSaveButton();
		}
	}

	@FXML
	public void dragOverManualItems(DragEvent event){
		 if(event.getDragboard().hasString()) {
             event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
         }
		 event.consume();
	}



	private void clearArrayLists(){
		matchPlayersList.clear();
		matchMapsList.clear();
		matchNationsList.clear();
		Teams.getTeamA().clear();
		Teams.getTeamB().clear();
		chosenMap = "";
	}

	private void checkSaveButton(){
		if(Teams.getTeamA().size()>=1&&Teams.getTeamB().size()>=1&&chosenMap.isEmpty()==false){
			save.setDisable(false);
		}
	}


	private void setMatch(){
		boolean isReset = false;
		save.setDisable(false);

		treeItemCollector 			= new CheckBoxTreeItem<Object>();
		treeItemCollector_Karte		= new CheckBoxTreeItem<Object>();
		treeItemCollector_Voelker 	= new CheckBoxTreeItem<Object>();
		treeItemCollector_Spieler 	= new CheckBoxTreeItem<Object>();

		treeItemCollector_Spieler 	= collectItems(TreeView_Controller.getSpieler_Root_aktiv());
		treeItemCollector_Spieler 	= collectItems(TreeView_Controller.getSpieler_Root_Inaktiv());
		treeItemCollector_Karte		= collectItems(TreeView_Controller.getKarten_Root_Landkarte());
		treeItemCollector_Karte		= collectItems(TreeView_Controller.getKarten_Root_Wasserkarte());
		treeItemCollector_Karte		= collectItems(TreeView_Controller.getKarten_Root_Funkarten());
		treeItemCollector_Karte		= collectItems(TreeView_Controller.getKarten_Root_AusKarten());
		treeItemCollector_Voelker	= collectItems(TreeView_Controller.getVoelker_Root_Paladin());
		treeItemCollector_Voelker	= collectItems(TreeView_Controller.getVoelker_Root_NonPaladin());

		checkItemIsSelected_Players(treeItemCollector_Spieler);
		checkItemIsSelected_Maps(treeItemCollector_Karte);
		checkItemIsSelected_Nations(treeItemCollector_Voelker);

		isCheck_Conditions = proofMatchConditions();

		if(isCheck_Conditions == true){
			auslosungMatch();
			showMatchScreen(isReset);
		}
	}



	private CheckBoxTreeItem<Object> collectItems(CheckBoxTreeItem<Object> treeItem){

		for(TreeItem<Object> item:treeItem.getChildren()){
			treeItemCollector.getChildren().add(item);
	    }
		return treeItemCollector;
	}

	private void checkItemIsSelected_Players(CheckBoxTreeItem<Object> treeItem){
		Load_Database db = new Load_Database();

		for(int i=0;i<db.getLoadPlayers().size();i++){

			for(TreeItem<Object> item:treeItem.getChildren()){

				if(item.getValue().toString().equals(db.getLoadPlayers().get(i).getName())&&((CheckBoxTreeItem<Object>)item).isSelected()==true){
					//System.out.println(item.getValue().toString());
					matchPlayersList.add(db.getLoadPlayers().get(i));
				}
			}
		}
		//System.out.println();
	}

	private void checkItemIsSelected_Maps(CheckBoxTreeItem<Object> treeItem){
		Load_Database db = new Load_Database();

		for(int i=0;i<db.getLoadMaps().size();i++){

			for(TreeItem<Object> item:treeItem.getChildren()){

				if(item.getValue().toString().equals(db.getLoadMaps().get(i).getName())&&((CheckBoxTreeItem<Object>)item).isSelected()==true){
					//System.out.println(item.getValue().toString());
					kartenfactor(i,db);
				}
			}
		}
		Collections.shuffle(matchMapsList);
		//System.out.println();
	}

	private void checkItemIsSelected_Nations(CheckBoxTreeItem<Object> treeItem){
		Load_Database db = new Load_Database();

		for(int i=0;i<db.getLoadNations().size();i++){

			for(TreeItem<Object> item:treeItem.getChildren()){

				if(item.getValue().toString().equals(db.getLoadNations().get(i).getName())&&((CheckBoxTreeItem<Object>)item).isSelected()==true){
					//System.out.println(item.getValue().toString());
					matchNationsList.add(db.getLoadNations().get(i));
				}
			}
		}
		//System.out.println();
	}

	private void auslosungMatch(){

		Match_Auslosung matchUp = new Match_Auslosung();
		matchUp.setRandomMap();

		map_Slot.setText(chosenMap);

		if(Properties_Controller.isMirrorNation() == false){
//			System.out.println("F Ist doppelt: "+Properties_Controller.isDoubleNation());
			if(Properties_Controller.isDoubleNation() == true){

				matchUp.setDoubleNation();
			}else{

				matchUp.setNoDoubleNation();
			}
		}else{
//			System.out.println("T Ist doppelt: "+Properties_Controller.isDoubleNation());
			if(Properties_Controller.isDoubleNation() == true){
				matchUp.setMirrorNation();
			}else{
				matchUp.setMirrorNationNoDouble();
			}
		}
	}

/*------- Hier werden Methoden aufgerufen welche ausschlieﬂlich benutzt werden -----------------*/
/*------- um den Auslosungsinhalt direkt auf den Screen abzubilden, bzw. auf die Buttons. ------*/

	private void showMatchScreen(boolean isReset){
		collectButton();
		setTeams_ButtonsTeamA(isReset);
		setTeams_ButtonsTeamB(isReset);

	}

	private void collectButton(){

		btList_TeamA 	= new ArrayList<Button>();
		btList_TeamB	= new ArrayList<Button>();
		btList_NationA 	= new ArrayList<Button>();
		btList_NationB	= new ArrayList<Button>();

		btList_TeamA.add(player_Slot_1);
		btList_TeamA.add(player_Slot_2);
		btList_TeamA.add(player_Slot_3);
		btList_TeamA.add(player_Slot_4);
		btList_TeamB.add(player_Slot_5);
		btList_TeamB.add(player_Slot_6);
		btList_TeamB.add(player_Slot_7);
		btList_TeamB.add(player_Slot_8);

		btList_NationA.add(nation_Slot_1);
		btList_NationA.add(nation_Slot_2);
		btList_NationA.add(nation_Slot_3);
		btList_NationA.add(nation_Slot_4);
		btList_NationB.add(nation_Slot_5);
		btList_NationB.add(nation_Slot_6);
		btList_NationB.add(nation_Slot_7);
		btList_NationB.add(nation_Slot_8);
	}

	private void setTeams_ButtonsTeamA(boolean isReset){

		for(int i=0;i<btList_TeamA.size();i++){
			btList_TeamA.get(i).setVisible(true);
			btList_NationA.get(i).setVisible(true);
			btList_TeamA.get(i).setText("player_Slot_"+(i+1));
			btList_NationA.get(i).setText("nation_Slot_"+(i+1));
		}
		if(isReset!=true){
			for(int i=0;i<Teams.getTeamA().size();i++){
				btList_TeamA.get(i).setText(Teams.getTeamA().get(i).toString());
				btList_NationA.get(i).setText(Teams.getTeamA().get(i).getChosenNation_Spieler());
			}

			for(int i=Teams.getTeamA().size();i<btList_TeamB.size();i++){
				btList_TeamA.get(i).setVisible(false);
				btList_NationA.get(i).setVisible(false);
			}
		}

	}

	private void setTeams_ButtonsTeamB(boolean isReset){

		for(int i=0;i<btList_TeamB.size();i++){
			btList_TeamB.get(i).setVisible(true);
			btList_NationB.get(i).setVisible(true);
			btList_TeamB.get(i).setText("player_Slot_"+(i+5));
			btList_NationB.get(i).setText("nation_Slot_"+(i+5));
		}

		if(isReset!=true){
			for(int i=0;i<Teams.getTeamB().size();i++){
				btList_TeamB.get(i).setText(Teams.getTeamB().get(i).toString());
				btList_NationB.get(i).setText(Teams.getTeamB().get(i).getChosenNation_Spieler());
			}
			for(int i=Teams.getTeamB().size();i<btList_TeamB.size();i++){
				btList_TeamB.get(i).setVisible(false);
				btList_NationB.get(i).setVisible(false);
			}
		}

	}

/*------------------ Der Kartenfaktor, hier wird anhand des Faktors bestimmt wie h‰ufig eine Karte in die Liste kommt------------*/

	private void kartenfactor(int index, Load_Database db){
		int factor = db.getLoadMaps().get(index).getFactor();

		for(int i=0;i<factor;i++){
			matchMapsList.add(db.getLoadMaps().get(index));
		}
		System.out.println("Karte: "+db.getLoadMaps().get(index)+"  Faktor: "+db.getLoadMaps().get(index).getFactor());
	}

/*------- Hier werden Methoden aufgerufen welche ausschlieﬂlich benutzt werden ---------------------------------*/
/*------- um zu pr¸fen wieviele Nationen/Spieler/Karten ausgew‰hlt sind. Mindestanzahl oder maximal Zahl! ------*/

	private boolean proofMatchConditions(){
		if(matchPlayersList.size()>8){
			DialogWindow dialog = new DialogWindow();
			dialog.dialogWindow_Alert_MatchConditions_1();
			return false;
		}
		else if(matchPlayersList.size()<2){
			DialogWindow dialog = new DialogWindow();
			dialog.dialogWindow_Alert_MatchConditions_2();
			return false;
		}else if(matchMapsList.size()==0 || matchNationsList.size()==0){
			DialogWindow dialog = new DialogWindow();
			dialog.dialogWindow_Alert_MatchConditions_3();
			return false;
		}
		else{
			return true;
		}
	}


/*---------------------- Getter-Methoden ---------------------------------*/

	public static ArrayList<Players> getMatchPlayersList() {
		return matchPlayersList;
	}

	public static ArrayList<Maps> getMatchMapsList() {
		return matchMapsList;
	}

	public static ArrayList<Nationen> getMatchNationsList() {
		return matchNationsList;
	}

	public static String getChosenMap() {
		return chosenMap;
	}

	public Button getDrawing() {
		return drawing;
	}

	public Button getSave() {
		return save;
	}

	public Button getReset() {
		return reset;
	}

	public Button getMap_Slot() {
		return map_Slot;
	}

	public Button getPlayer_Slot_1() {
		return player_Slot_1;
	}

	public Button getPlayer_Slot_2() {
		return player_Slot_2;
	}

	public Button getPlayer_Slot_3() {
		return player_Slot_3;
	}

	public Button getPlayer_Slot_4() {
		return player_Slot_4;
	}

	public Button getPlayer_Slot_5() {
		return player_Slot_5;
	}

	public Button getPlayer_Slot_6() {
		return player_Slot_6;
	}

	public Button getPlayer_Slot_7() {
		return player_Slot_7;
	}

	public Button getPlayer_Slot_8() {
		return player_Slot_8;
	}

	public Button getNation_Slot_1() {
		return nation_Slot_1;
	}

	public Button getNation_Slot_2() {
		return nation_Slot_2;
	}

	public Button getNation_Slot_3() {
		return nation_Slot_3;
	}

	public Button getNation_Slot_4() {
		return nation_Slot_4;
	}

	public Button getNation_Slot_5() {
		return nation_Slot_5;
	}

	public Button getNation_Slot_6() {
		return nation_Slot_6;
	}

	public Button getNation_Slot_7() {
		return nation_Slot_7;
	}

	public Button getNation_Slot_8() {
		return nation_Slot_8;
	}

	public ArrayList<Button> getBtList_TeamA() {
		return btList_TeamA;
	}

	public ArrayList<Button> getBtList_TeamB() {
		return btList_TeamB;
	}

	public ArrayList<Button> getBtList_NationA() {
		return btList_NationA;
	}

	public ArrayList<Button> getBtList_NationB() {
		return btList_NationB;
	}

/*---------------------- Controller Methoden ---------------------------------*/

	public void init(Elo_Controller tabeElo_Controller) {
		this.tabeElo_Controller = tabeElo_Controller;
	}

	private void setMatch_Controller_DragDrop(){
		dragDrop = new Match_Controller_DragDrop(this);
	}

	public Elo_Controller getTabeElo_Controller() {
		return tabeElo_Controller;
	}

/*---------------------- Einfach Setter Methoden ---------------------------------*/

	public static void setChosenMap(String chosenMap) {
		Match_Controller.chosenMap = chosenMap;
	}













}


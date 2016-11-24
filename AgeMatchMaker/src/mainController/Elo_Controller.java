package mainController;

import java.util.ArrayList;

import database.Players;
import database.Teams;
import eloCalc.Elo_Calculation;
import eloCalc.Elo_Table_Data;
import eloCalc.Elo_Ratings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Elo_Controller {

	@FXML TableView<Elo_Table_Data> ratingTableA;
	@FXML TableView<Elo_Table_Data> ratingTableB;

	@FXML TableView<Elo_Table_Data> eloTableA;
	@FXML TableView<Elo_Table_Data> eloTableB;

	@FXML TableView<Elo_Table_Data> predictionTableA;
	@FXML TableView<Elo_Table_Data> predictionTableB;

	@FXML TableColumn<Elo_Table_Data, String> playersNameA;
	@FXML TableColumn<Elo_Table_Data, String> playeresRatingA;
	@FXML TableColumn<Elo_Table_Data, String> nationRatingA;
	@FXML TableColumn<Elo_Table_Data, String> mapRatingA;

	@FXML TableColumn<Elo_Table_Data, String> allEloA;
	@FXML TableColumn<Elo_Table_Data, String> teamEloA;
	@FXML TableColumn<Elo_Table_Data, String> nationEloA;
	@FXML TableColumn<Elo_Table_Data, String> mapEloA;

	@FXML TableColumn<Elo_Table_Data, String> teamWinA;
	@FXML TableColumn<Elo_Table_Data, String> teamLoseA;
	@FXML TableColumn<Elo_Table_Data, String> nationWinA;
	@FXML TableColumn<Elo_Table_Data, String> nationLoseA;
	@FXML TableColumn<Elo_Table_Data, String> mapWinA;
	@FXML TableColumn<Elo_Table_Data, String> mapLoseA;



	@FXML TableColumn<Elo_Table_Data, String> playersNameB;
	@FXML TableColumn<Elo_Table_Data, String> playersRatingB;
	@FXML TableColumn<Elo_Table_Data, String> nationRatingB;
	@FXML TableColumn<Elo_Table_Data, String> mapRatingB;

	@FXML TableColumn<Elo_Table_Data, String> allEloB;
	@FXML TableColumn<Elo_Table_Data, String> teamEloB;
	@FXML TableColumn<Elo_Table_Data, String> nationEloB;
	@FXML TableColumn<Elo_Table_Data, String> mapEloB;

	@FXML TableColumn<Elo_Table_Data, String> teamWinB;
	@FXML TableColumn<Elo_Table_Data, String> teamLoseB;
	@FXML TableColumn<Elo_Table_Data, String> nationWinB;
	@FXML TableColumn<Elo_Table_Data, String> nationLoseB;
	@FXML TableColumn<Elo_Table_Data, String> mapWinB;
	@FXML TableColumn<Elo_Table_Data, String> mapLoseB;



	public void createTables(){

		ArrayList<Players> teamA = Teams.getTeamA();
		ArrayList<Players> teamB = Teams.getTeamB();

		Elo_Ratings ratingTeams 		= new Elo_Ratings();
		Elo_Ratings ratingNations 		= new Elo_Ratings();
		Elo_Calculation calcTeams 		= new Elo_Calculation(ratingTeams);
		Elo_Calculation calcNations 	= new Elo_Calculation(ratingNations);

		ratingTeams.setTeamRating();
		ratingNations.setNationRating();
		ratingTeams.setMapRating();
		ratingTeams.setTeamRatingAll(ratingNations);

		ratingTeams.setTeamFav(ratingTeams.getAllRating_TeamA(ratingNations), ratingTeams.getAllRating_TeamB(ratingNations));
//		System.out.println("TeamA :"+Teams.isTeamA_Fav());
//		System.out.println("TeamB :"+Teams.isTeamB_Fav());
//		System.out.println("TeamA Nation :"+Teams.isNation_TeamA_Fav());
//		System.out.println("TeamB Nation :"+Teams.isNation_TeamB_Fav());

		ObservableList<Elo_Table_Data> dataRatingTableA			= FXCollections.observableArrayList();
		ObservableList<Elo_Table_Data> dataRatingTableB 		= FXCollections.observableArrayList();
		ObservableList<Elo_Table_Data> dataEloTableA			= FXCollections.observableArrayList();
		ObservableList<Elo_Table_Data> dataEloTableB			= FXCollections.observableArrayList();
		ObservableList<Elo_Table_Data> dataPredictionTableA		= FXCollections.observableArrayList();
		ObservableList<Elo_Table_Data> dataPredictionTableB		= FXCollections.observableArrayList();

		setTables(teamA, dataRatingTableA);
		setTables(teamB, dataRatingTableB);


		dataEloTableA.add(new Elo_Table_Data(	ratingTeams.getAllRating_TeamA(ratingNations), 	ratingTeams.getTeamRating_TeamA(),
												ratingNations.getNationRating_TeamA(), 	ratingTeams.getMapRating_TeamA()));

		dataEloTableB.add(new Elo_Table_Data(	ratingTeams.getAllRating_TeamB(ratingNations), 	ratingTeams.getTeamRating_TeamB(),
												ratingNations.getNationRating_TeamB(), 	ratingTeams.getMapRating_TeamB()));

		dataPredictionTableA.add(new Elo_Table_Data(calcTeams.calcEloRating(Teams.isTeamA_Fav(),true),
													calcTeams.calcEloRating(Teams.isTeamA_Fav(),false),
													calcNations.calcEloRating(Teams.isNation_TeamA_Fav(),true),
													calcNations.calcEloRating(Teams.isNation_TeamA_Fav(),false),
													Elo_Calculation.getErgebnisMap_Win(),
													Elo_Calculation.getErgebnisMap_Lose()));


		dataPredictionTableB.add(new Elo_Table_Data(calcTeams.calcEloRating(Teams.isTeamB_Fav(),true),
													calcTeams.calcEloRating(Teams.isTeamB_Fav(),false),
													calcNations.calcEloRating(Teams.isNation_TeamB_Fav(),true),
													calcNations.calcEloRating(Teams.isNation_TeamB_Fav(),false),
													Elo_Calculation.getErgebnisMap_Win(),
													Elo_Calculation.getErgebnisMap_Lose()));


		ratingTableA.setItems(dataRatingTableA);
		ratingTableB.setItems(dataRatingTableB);

		eloTableA.setItems(dataEloTableA);
		eloTableB.setItems(dataEloTableB);

		predictionTableA.setItems(dataPredictionTableA);
		predictionTableB.setItems(dataPredictionTableB);



		playersNameA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersName"));
		playeresRatingA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersRating"));
		nationRatingA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationRating"));
		mapRatingA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapRating"));

		allEloA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("allElo"));
		teamEloA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersElo"));
		nationEloA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationElo"));
		mapEloA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapElo"));

		teamWinA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("teamWin"));
		teamLoseA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("teamLose"));
		nationWinA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationWin"));
		nationLoseA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationLose"));
		mapWinA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapWin"));
		mapLoseA.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapLose"));



		playersNameB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersName"));
		playersRatingB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersRating"));
		nationRatingB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationRating"));
		mapRatingB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapRating"));

		allEloB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("allElo"));
		teamEloB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("playersElo"));
		nationEloB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationElo"));
		mapEloB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapElo"));

		teamWinB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("teamWin"));
		teamLoseB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("teamLose"));
		nationWinB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationWin"));
		nationLoseB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("nationLose"));
		mapWinB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapWin"));
		mapLoseB.setCellValueFactory(new PropertyValueFactory<Elo_Table_Data, String>("mapLose"));

	}

	private void setTables(ArrayList<Players> list, ObservableList<Elo_Table_Data> ratingTable){
		for(int i=0;i<list.size();i++){
			ratingTable.add(new Elo_Table_Data(list.get(i).toString(), list.get(i).getRating(), list.get(i).getNationRating(), list.get(i).getMapRating()));
		}
	}

	public void clearTables(){
		ratingTableA.getItems().clear();
		ratingTableB.getItems().clear();

		eloTableA.getItems().clear();
		eloTableB.getItems().clear();

		predictionTableA.getItems().clear();
		predictionTableB.getItems().clear();

	}



}

package subController;

import mainController.Match_Controller;
import database.Save_Database;
import database.Teams;
import dialogWindow.DialogWindow;
import eloCalc.Elo_Calculation;
import eloCalc.Elo_Ratings;

public class Match_Controller_Save extends Match_Controller {

	public Match_Controller_Save(){

		DialogWindow dialogWindow = new DialogWindow();
		dialogWindow.dialogWindow_Alert_Save();

		Elo_Ratings ratingTeams 		= new Elo_Ratings();
		Elo_Ratings ratingNations 		= new Elo_Ratings();
		Elo_Calculation calcTeams 		= new Elo_Calculation(ratingTeams);
		Elo_Calculation calcNations 	= new Elo_Calculation(ratingNations);

		ratingTeams.setTeamRating();
		ratingNations.setNationRating();
		ratingTeams.setMapRating();
		ratingTeams.setTeamRatingAll(ratingNations);

		ratingTeams.setTeamFav(ratingTeams.getAllRating_TeamA(ratingNations), ratingTeams.getAllRating_TeamB(ratingNations));

		Teams teamA = new Teams();
		Teams teamB = new Teams();

		teamA.setEnemyTeam(Teams.getTeamB());
		teamB.setEnemyTeam(Teams.getTeamA());

		if (dialogWindow.getResult().get() == dialogWindow.getButtonTeamA()){
			teamA.setTeamWinner(true);
			teamB.setTeamWinner(false);
			teamA.setMapRating(Elo_Calculation.getErgebnisMap_Win());
			teamB.setMapRating(Elo_Calculation.getErgebnisMap_Lose());
		} else if (dialogWindow.getResult().get() == dialogWindow.getButtonTeamB()) {
			teamA.setTeamWinner(false);
			teamB.setTeamWinner(true);
			teamB.setMapRating(Elo_Calculation.getErgebnisMap_Win());
			teamA.setMapRating(Elo_Calculation.getErgebnisMap_Lose());
		}

		if(dialogWindow.getResult().get() != dialogWindow.getButtonCancel()){
			teamA.setTeamRating(calcTeams.calcEloRating(Teams.isTeamA_Fav(),teamA.isTeamWinner()));
			teamA.setNationRating(calcNations.calcEloRating(Teams.isNation_TeamA_Fav(),teamA.isTeamWinner()));

			teamB.setTeamRating(calcTeams.calcEloRating(Teams.isTeamB_Fav(),teamB.isTeamWinner()));
			teamB.setNationRating(calcNations.calcEloRating(Teams.isNation_TeamB_Fav(),teamB.isTeamWinner()));
//			System.out.println("Karte: "+Match_Controller.getChosenMap());
//			System.out.println("Calculation TeamA "+teamA.getTeamRating());
//			System.out.println("Calculation NationA "+teamA.getNationRating());
//			System.out.println("Calculation TeamB "+teamB.getTeamRating());
//			System.out.println("Calculation NationB "+teamB.getNationRating());
//			System.out.println("Map TeamA "+teamA.getMapRating());
//			System.out.println("Map TeamB "+teamB.getMapRating());
//			System.out.println("Player ID TeamA"+Teams.getTeamA().get(0).getId());
//			System.out.println("Team A ist winner?: "+teamA.isTeamWinner());
//			System.out.println("Team B ist winner?: "+teamB.isTeamWinner());
//			System.out.println();
//			System.out.println("TeamA");
			for(int i=0;i<Teams.getTeamA().size();i++){
				System.out.println(Teams.getTeamA().get(i).getName()+"    "+Teams.getTeamA().get(i).getChosenNation_Spieler());
			}
			System.out.println();
			System.out.println("TeamB");
			for(int i=0;i<Teams.getTeamB().size();i++){
				System.out.println(Teams.getTeamB().get(i).getName()+"    "+Teams.getTeamB().get(i).getChosenNation_Spieler());
			}

			Save_Database save = new Save_Database();

			save.writeData_Spieler(Teams.getTeamA(), teamA);
			save.writeData_Spieler(Teams.getTeamB(), teamB);

			save.writeData_Spieler_Stats(Teams.getTeamA(), teamA);
			save.writeData_Spieler_Stats(Teams.getTeamB(), teamB);

			save.writeData_Völker_Stats(Teams.getTeamA(), teamA);
			save.writeData_Völker_Stats(Teams.getTeamB(), teamB);

			save.writeData_Karten_Stats(Teams.getTeamA(), teamA);
			save.writeData_Karten_Stats(Teams.getTeamB(), teamB);

			save.writeData_Karten_Volk_Stats(Teams.getTeamA(), teamA);
			save.writeData_Karten_Volk_Stats(Teams.getTeamB(), teamB);

			save.writeData_Karten_Factor();

			save.writeData_Match(teamA, teamB);
		}


	}

}

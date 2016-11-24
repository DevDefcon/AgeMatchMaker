package eloCalc;

import java.util.ArrayList;

import mainController.Match_Controller;
import database.Players;
import database.Teams;


public class Elo_Ratings {
	
	
	private ArrayList<Players> matchPlayersList;
	
	private int allRating_TeamA;
	private int allRating_TeamB;
	
	private int teamRating_TeamA;
	private int teamRating_TeamB;
	
	private int nationRating_TeamA;
	private int nationRating_TeamB;
	
	private int mapRating_TeamA;
	private int mapRating_TeamB;
	
	private int eloPunkteHoch;
	private int eloPunkteTief;
	
	private double erwartungswert;
	
	public Elo_Ratings(){
		
		matchPlayersList 	= Match_Controller.getMatchPlayersList();
	}
	
	public void setTeamRating(){
		
		for(int i=0;i<Teams.getTeamA().size();i++){
			this.teamRating_TeamA = this.teamRating_TeamA+Teams.getTeamA().get(i).getRating();
		}
		
		for(int i=0;i<Teams.getTeamB().size();i++){
			this.teamRating_TeamB = this.teamRating_TeamB+Teams.getTeamB().get(i).getRating();
		}
		
	}
	
	//Sollte erst aufgerufen werden wenn setTeamRating und setNationRating aufgerufen wurden.
	public void setTeamRatingAll(Elo_Ratings nations){
		allRating_TeamA = teamRating_TeamA+nations.nationRating_TeamA+mapRating_TeamA;
		allRating_TeamB = teamRating_TeamB+nations.nationRating_TeamB+mapRating_TeamB;
		sortRating(allRating_TeamA, allRating_TeamB);
	}
	
	
	public void setNationRating(){
		
		
		for(int i=0;i<Teams.getTeamA().size();i++){
			this.nationRating_TeamA = this.nationRating_TeamA+Teams.getTeamA().get(i).getNationRating();
		}
		
		for(int i=0;i<Teams.getTeamB().size();i++){
			this.nationRating_TeamB = this.nationRating_TeamB+Teams.getTeamB().get(i).getNationRating();
		}
		
		sortRating(nationRating_TeamA, nationRating_TeamB);
		setNationFav(nationRating_TeamA, nationRating_TeamB);
	}
	
	
	public void setMapRating(){
		
		for(int i=0;i<Teams.getTeamA().size();i++){
			this.mapRating_TeamA = this.mapRating_TeamA+Teams.getTeamA().get(i).getMapRating();
		}
		
		for(int i=0;i<Teams.getTeamB().size();i++){
			this.mapRating_TeamB = this.mapRating_TeamB+Teams.getTeamB().get(i).getMapRating();
		}
	}
	
	
	private void sortRating(int ratingTeamA, int ratingTeamB){
		
		if(ratingTeamB < ratingTeamA){
			this.eloPunkteHoch = ratingTeamA;
			this.eloPunkteTief = ratingTeamB;
		}
		else if(ratingTeamB > teamRating_TeamA){
			this.eloPunkteHoch = ratingTeamB;
			this.eloPunkteTief = ratingTeamA;
		}
		else{
			this.eloPunkteHoch = ratingTeamA;
			this.eloPunkteTief = ratingTeamB;
		}
		
		eloErwartungswert();
	}
	
	
	public void setTeamFav(int ratingTeamA, int ratingTeamB){
		
		if(ratingTeamB < ratingTeamA){
			Teams.setTeamA_Fav(true);
			Teams.setTeamB_Fav(false);
		}
		else if(ratingTeamB > ratingTeamA){
			Teams.setTeamB_Fav(true);
			Teams.setTeamA_Fav(false);
		}
		else{
			Teams.setTeamA_Fav(true);
			Teams.setTeamB_Fav(true);
		}
	}
	
	
	private void setNationFav(int ratingTeamA, int ratingTeamB){
		
		for(int i=0;i<matchPlayersList.size();i++){
			if(ratingTeamB < ratingTeamA){
				Teams.setNation_TeamA_Fav(true);
				Teams.setNation_TeamB_Fav(false);
			}
			else if(ratingTeamB > ratingTeamA){
				Teams.setNation_TeamB_Fav(true);
				Teams.setNation_TeamA_Fav(false);
			}
			else{
				Teams.setNation_TeamA_Fav(true);
				Teams.setNation_TeamB_Fav(true);
			}
		}
		
	}
	
	
	private void eloErwartungswert(){
		
		double erg;
		
		erg = eloPunkteTief-eloPunkteHoch;
		erg = erg/400;
		erg = Math.pow(10.0,erg);
		erg = 1+erg;
		erwartungswert = 1/erg;
		erwartungswert = Math.round(10.0*erwartungswert)/10.0;
		erwartungswert = erwartungswert*(-1);
	}
	
	//////////////////////////////GETTER METHODEN//////////////////////////////
	
	public int getEloPunkteHoch() {
		return eloPunkteHoch;
	}

	
	public int getEloPunkteTief() {
		return eloPunkteTief;
	}
	
	
	public int getTeamRating_TeamA() {
		return teamRating_TeamA;
	}


	public int getTeamRating_TeamB() {
		return teamRating_TeamB;
	}


	public int getNationRating_TeamA() {
		return nationRating_TeamA;
	}


	public int getNationRating_TeamB() {
		return nationRating_TeamB;
	}


	public int getMapRating_TeamA() {
		return mapRating_TeamA;
	}


	public int getMapRating_TeamB() {
		return mapRating_TeamB;
	}


	public int getAllRating_TeamA(Elo_Ratings nations) {
		allRating_TeamA = teamRating_TeamA+nations.nationRating_TeamA+mapRating_TeamA;
		return allRating_TeamA;
	}


	public int getAllRating_TeamB(Elo_Ratings nations) {
		allRating_TeamB = teamRating_TeamB+nations.nationRating_TeamB+mapRating_TeamB;
		return allRating_TeamB;
	}

	public double getErwartungswert() {
		return erwartungswert;
	}
}

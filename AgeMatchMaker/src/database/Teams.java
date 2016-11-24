package database;

import java.util.ArrayList;

public class Teams {
	
	private static ArrayList<Players> teamA = new ArrayList<Players>();
	private static ArrayList<Players> teamB = new ArrayList<Players>();
	
	private ArrayList<Players> enemyTeam = new ArrayList<Players>();
	
	private static boolean teamA_Fav;
	private static boolean teamB_Fav;
	
	private static boolean nation_TeamA_Fav;
	private static boolean nation_TeamB_Fav;
	
	private boolean isTeamWinner;
	
	private int nationRating;
	private int teamRating;
	private int mapRating;
	
	
	
	public static ArrayList<Players> getTeamA() {
		return teamA;
	}
	
	
	public static void setTeamA(Players player) {
		teamA.add(player);
	}
	
	
	public static ArrayList<Players> getTeamB() {
		return teamB;
	}
	
	
	public static void setTeamB(Players player) {
		teamB.add(player);
	}


	public static boolean isTeamA_Fav() {
		return teamA_Fav;
	}


	public static void setTeamA_Fav(boolean teamA_Fav) {
		Teams.teamA_Fav = teamA_Fav;
	}


	public static boolean isTeamB_Fav() {
		return teamB_Fav;
	}


	public static void setTeamB_Fav(boolean teamB_Fav) {
		Teams.teamB_Fav = teamB_Fav;
	}


	public static boolean isNation_TeamA_Fav() {
		return nation_TeamA_Fav;
	}


	public static void setNation_TeamA_Fav(boolean nation_TeamA_Fav) {
		Teams.nation_TeamA_Fav = nation_TeamA_Fav;
	}


	public static boolean isNation_TeamB_Fav() {
		return nation_TeamB_Fav;
	}


	public static void setNation_TeamB_Fav(boolean nation_TeamB_Fav) {
		Teams.nation_TeamB_Fav = nation_TeamB_Fav;
	}

	public boolean isTeamWinner() {
		return isTeamWinner;
	}

	public void setTeamWinner(boolean isTeamWinner) {
		this.isTeamWinner = isTeamWinner;
	}

	public int getNationRating() {
		return nationRating;
	}

	public void setNationRating(int nationRating) {
		this.nationRating = nationRating;
	}

	public int getTeamRating() {
		return teamRating;
	}

	public void setTeamRating(int teamRating) {
		this.teamRating = teamRating;
	}

	public int getMapRating() {
		return mapRating;
	}

	public void setMapRating(int mapRating) {
		this.mapRating = mapRating;
	}


	public ArrayList<Players> getEnemyTeam() {
		return enemyTeam;
	}


	public void setEnemyTeam(ArrayList<Players> enemyTeam) {
		this.enemyTeam = enemyTeam;
	}
	
	

}

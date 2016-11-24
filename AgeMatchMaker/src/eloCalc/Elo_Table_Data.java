package eloCalc;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Elo_Table_Data {

	private SimpleStringProperty 	playersName;
    private SimpleIntegerProperty	playersRating;
    private SimpleIntegerProperty 	nationRating;
    private SimpleIntegerProperty 	mapRating;
    
    private SimpleIntegerProperty	playersElo;
    private SimpleIntegerProperty 	nationElo;
    private SimpleIntegerProperty 	mapElo;
    private SimpleIntegerProperty 	allElo;
    
    private SimpleIntegerProperty 	teamWin; 
    private SimpleIntegerProperty 	teamLose; 
    private SimpleIntegerProperty 	nationWin; 
    private SimpleIntegerProperty 	nationLose; 
    private SimpleIntegerProperty 	mapWin;
    private SimpleIntegerProperty 	mapLose;
    
    
    public Elo_Table_Data(String playersName, int playersRating, int nationRating, int mapRating){
    	this.playersName 		= new SimpleStringProperty(playersName);
    	this.playersRating 		= new SimpleIntegerProperty(playersRating);
    	this.nationRating 		= new SimpleIntegerProperty(nationRating);
    	this.mapRating 			= new SimpleIntegerProperty(mapRating);
    }
    
    public Elo_Table_Data(int allElo, int playersElo, int nationElo, int mapElo){
    	this.allElo				= new SimpleIntegerProperty(allElo);
    	this.playersElo			= new SimpleIntegerProperty(playersElo);
    	this.nationElo			= new SimpleIntegerProperty(nationElo);
    	this.mapElo				= new SimpleIntegerProperty(mapElo);
    }
    
    public Elo_Table_Data(int teamWin, int teamLose, int nationWin, int nationLose, int mapWin, int mapLose){
    	this.teamWin			= new SimpleIntegerProperty(teamWin);
    	this.teamLose			= new SimpleIntegerProperty(teamLose);
    	this.nationWin			= new SimpleIntegerProperty(nationWin);
    	this.nationLose			= new SimpleIntegerProperty(nationLose);
    	this.mapWin				= new SimpleIntegerProperty(mapWin);
    	this.mapLose			= new SimpleIntegerProperty(mapLose);
    }

	public String getPlayersName() {
		return playersName.get();
	}

	public int getPlayersRating() {
		return playersRating.get();
	}

	public int getNationRating() {
		return nationRating.get();
	}

	public int getMapRating() {
		return mapRating.get();
	}

	public int getPlayersElo() {
		return playersElo.get();
	}

	public int getNationElo() {
		return nationElo.get();
	}

	public int getMapElo() {
		return mapElo.get();
	}

	public int getAllElo() {
		return allElo.get();
	}

	public int getTeamWin() {
		return teamWin.get();
	}

	public int getTeamLose() {
		return teamLose.get();
	}

	public int getNationWin() {
		return nationWin.get();
	}

	public int getNationLose() {
		return nationLose.get();
	}

	public int getMapWin() {
		return mapWin.get();
	}

	public int getMapLose() {
		return mapLose.get();
	}

	

}

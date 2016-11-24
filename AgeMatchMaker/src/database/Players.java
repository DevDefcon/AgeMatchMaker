package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Players implements Comparable<Players>{
	
	
	private String chosenNation_Spieler;
	
	private int mapRating;
	private int nationRating;
	private int eloTotalRating;
	
	private int map_id;
	private int nation_id;
	
	//Members für den Konstruktor
	private int id;
	private String name;
	private int rating;
	private int siege;
	private int niederlagen;
	private boolean isAktiv;
	
	
	
	public Players(int id,String name,int eloRating, int siege, int niederlagen, boolean isAktiv ){
		this.id				= id;
		this.name 			= name;
		this.rating 		= eloRating;
		this.siege 			= siege;
		this.niederlagen	= niederlagen;
		this.isAktiv		= isAktiv;
		
		// totalRating wird in der Methode compareSpieler aufgerufen. In zwei Methoden mirrorNations muss totalRating vorher schon einen Wert besitzen, 
		// ohne auf nation- und mapRating zu warten
		this.eloTotalRating = eloRating; 	
	}
	
//--------------Getter Methoden für Konstruktoren Members--------------//
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRating() {
		return rating;
	}
	
	public int getSiege() {
		return siege;
	}

	public int getNiederlagen() {
		return niederlagen;
	}
	
	public boolean isAktiv() {
		return isAktiv;
	}

	
//--------------Getter Methoden-------------------------//	
	
	public int getMapRating() {
		return mapRating;
	}

	public int getNationRating() {
		return nationRating;
	}
	
	public int getEloTotalRating() {
		this.eloTotalRating = this.mapRating+this.nationRating+this.rating;
		return eloTotalRating;
	}
	
	public String getChosenNation_Spieler() {
		return chosenNation_Spieler;
	}
	
	public int getMap_id() {
		return map_id;
	}

	public int getNation_id() {
		return nation_id;
	}

	
	public void setSpielerRating_Spieler(int id){
		try {
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();
		
			ResultSet table_01 = statement.executeQuery("SELECT * FROM \"Spieler\" WHERE \"ID\"="+id+"");
			
			while(table_01.next()){
				this.rating = table_01.getInt("Rating");
			}
			statement.close();
			con.close();
		}
	    catch (SQLException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		     ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setMapRating_Spieler(int players_id, int map_id){
		this.map_id = map_id;
		try {
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();
		
			ResultSet table_01 = statement.executeQuery("SELECT * FROM \"Karten_Stats\" WHERE \"Spieler_ID\"="+players_id+" AND \"Karten_ID\"="+map_id+"");
										
			
			while(table_01.next()){
				this.mapRating = table_01.getInt("Rating");
			}
			statement.close();
			con.close();
		}
	    catch (SQLException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		     ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setChosenNation_Spieler(String chosenNation_Spieler, int players_id, int nation_id) {
		this.chosenNation_Spieler = chosenNation_Spieler;
		this.nation_id = nation_id;
		setNationRating_Spieler(players_id, nation_id);
	}
	
	private void setNationRating_Spieler(int players_id, int nation_id){
		
		try {
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();
		
			ResultSet table_01 = statement.executeQuery("SELECT * FROM \"Völker_Stats\" WHERE \"Spieler_ID\"="+players_id+" AND \"Völker_ID\"="+nation_id+"");
											
			
			while(table_01.next()){
				this.nationRating = table_01.getInt("Rating");
			}
			statement.close();
			con.close();
		}
	    catch (SQLException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		     ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
		     Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	@Override
	public int compareTo(Players spieler) {
		if(this.getEloTotalRating() < spieler.getEloTotalRating()) {
		      return 1;
		   }
		   if(this.getEloTotalRating() > spieler.getEloTotalRating()) {
		      return -1;
		   }
		   return 0;
	}
	
	@Override
	public String toString(){
        return String.format("%s", name);
    }

	
	

}

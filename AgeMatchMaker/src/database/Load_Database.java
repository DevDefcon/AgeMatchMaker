package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Load_Database {


	private ArrayList<Maps> 	loadMaps;
	private ArrayList<Nationen> loadNations;
	private ArrayList<Players> 	loadPlayers;

	public static String dbPfad(){
		//C:\\Users\\marco\\Desktop\\Source DB Stand 28.12.15\\ageDB
		//ageDB    Für die Jar außerhalb Eclipse
		String pfad = "ageDB";
		return pfad;
	}

	public Load_Database(){

		loadPlayers = new ArrayList<Players>();
		loadMaps = new ArrayList<Maps>();
		loadNations = new ArrayList<Nationen>();

		try{


			String db_file_name_prefix = dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"Sa",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();


			ResultSet tabel_01 = statement.executeQuery("SELECT * FROM \"Spieler\" ORDER BY \"Rating\" DESC");

			while(tabel_01.next()){
			    Players player = new Players(tabel_01.getInt("ID"),tabel_01.getString("Name"), tabel_01.getInt("Rating"),
			    		tabel_01.getInt("Siege"), tabel_01.getInt("Niederlagen"), tabel_01.getBoolean("Aktiv"));
				loadPlayers.add(player);
			}


			tabel_01 = statement.executeQuery("SELECT * FROM \"Karten\" ORDER BY \"Name\"");

		    while(tabel_01.next()){
				Maps map = new Maps(tabel_01.getInt("ID"),tabel_01.getString("Name"),tabel_01.getString("Kategorie"),tabel_01.getInt("Factor"));
				loadMaps.add(map);
			}


		    tabel_01 = statement.executeQuery("SELECT * FROM \"Völker\" ORDER BY \"Name\"");

			while(tabel_01.next()){
				Nationen nation = new Nationen(tabel_01.getInt("ID"),tabel_01.getString("Name"),tabel_01.getBoolean("isPala"));
				loadNations.add(nation);
			}

			statement.close();
		    con.close();

		   }
		catch (SQLException ex){
		     Logger.getLogger(Load_Database.class.getName()).log(Level.SEVERE, null, ex);
		     ex.printStackTrace();
		   }
		catch (ClassNotFoundException ex){
		     Logger.getLogger(Load_Database.class.getName()).log(Level.SEVERE, null, ex);
		   }
	}

	public ArrayList<Maps> getLoadMaps() {
		return loadMaps;
	}

	public ArrayList<Nationen> getLoadNations() {
		return loadNations;
	}

	public ArrayList<Players> getLoadPlayers() {
		return loadPlayers;
	}




}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.CheckBoxTreeItem;
import mainController.TreeView_Controller;

public class CreateNewPlayer {
	
	public CreateNewPlayer(String newPlayerName, int rating){
		
		int avgRating = 0;
		String createNewPlayer;
		
		try{
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();

			
			
			//-----------------------------------------Tabelle Spieler------------------------------------------------------//
			
			//Erstellt eine neue Zeile in der Tabelle "Spieler"
			createNewPlayer = "INSERT INTO \"Spieler\"(\"Name\",\"Rating\",\"Siege\",\"Niederlagen\", \"Aktiv\") "
								+ " VALUES (\'"+newPlayerName+"\',\'"+rating+"\', \'"+0+"\', \'"+0+"\', \'"+true+"\')";
			
			statement.execute(createNewPlayer);
			
			Load_Database db = new Load_Database();
			
			//Erstellt eine neue Zeile in der Tabelle "Spieler_Stats"
			//Für den neuen Spieler werden alle Bezüge zu den anderen alten Spieler hergestellt
			for(int spieler_ID=1;spieler_ID<=db.getLoadPlayers().size();spieler_ID++){
				createNewPlayer = "INSERT INTO \"Spieler_Stats\"(\"ID_Win\", \"ID_Lose\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+db.getLoadPlayers().size()+"\',"
								+ "\'"+spieler_ID+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";
								
				statement.execute(createNewPlayer);
			}
			
			
			//Hier wird der neue Spieler ID mit den alten verknüpft.
			for(int spieler_ID=1;spieler_ID<=db.getLoadPlayers().size();spieler_ID++){
				createNewPlayer = "INSERT INTO \"Spieler_Stats\"(\"ID_Win\", \"ID_Lose\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+spieler_ID+"\',"
								+ "\'"+db.getLoadPlayers().size()+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";
								
				statement.execute(createNewPlayer);
			}
			
			//-----------------------------------------Tabelle Völker_Stats------------------------------------------------------//
			
			//Geht die Tabelle in der Spalte Rating durch und errechnet die Durchschnittswert für das neue Volk, welcher für jeden Spieler gilt.
			ResultSet avgNationRating = statement.executeQuery
					("SELECT AVG(\"Rating\") AS NationAverage FROM \"Völker_Stats\"");
			
			while(avgNationRating.next()){
				avgRating = (avgNationRating.getInt("NationAverage"));
			}
			
			
			for(int volk_ID=1;volk_ID<=db.getLoadNations().size();volk_ID++){
				createNewPlayer = "INSERT INTO \"Völker_Stats\"(\"Spieler_ID\", \"Völker_ID\", \"Rating\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+db.getLoadPlayers().size()+"\',"
								+ "\'"+volk_ID+"\',"
								+ "\'"+avgRating+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";

				statement.execute(createNewPlayer);
			}
			
			
			
			//-----------------------------------------Tabelle Völker_Stats------------------------------------------------------//
			
			for(int karten_ID=1;karten_ID<=db.getLoadMaps().size();karten_ID++){
				createNewPlayer = "INSERT INTO \"Karten_Stats\"(\"Spieler_ID\", \"Karten_ID\", \"Rating\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+db.getLoadPlayers().size()+"\',"
								+ "\'"+karten_ID+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";

				statement.execute(createNewPlayer);
			}
			
			statement.close();
			con.close();
		}
		catch (SQLException ex){
			Logger.getLogger(CreateNewPlayer.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
			Logger.getLogger(CreateNewPlayer.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (NumberFormatException ex){
			System.err.println("Ilegal input");
			Logger.getLogger(CreateNewPlayer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Hier wird der neue Spieler in TreeBaum aufgenommen als String Object.
		includeNewPlayer(newPlayerName);
		
	}
	
	private void includeNewPlayer(String newPlayerName){
		
		CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(newPlayerName);
		TreeView_Controller.getSpieler_Root_aktiv().getChildren().add(box);
	}

}

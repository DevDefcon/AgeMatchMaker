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





public class CreateNewNation {
	
	public CreateNewNation(String newNationName, String category ){
		
		int avgRating = 0;
		String createNewNation;
		boolean isPala = whichCategory(category);
		
		try{
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();
			
			
			//Erstellt eine neue Zeile in der Tabelle "Karten"
			createNewNation = "INSERT INTO \"Völker\"(\"Name\", \"isPala\") "
					+ "VALUES (\'"+newNationName+"\',"
							+ "\'"+isPala+"\')";

			statement.execute(createNewNation);
			
			//Geht die Tabelle in der Spalte Rating durch und errechnet die Durchschnittswert für das neue Volk, welcher für jeden Spieler gilt.
			ResultSet avgNationRating = statement.executeQuery
					("SELECT AVG(\"Rating\") AS NationAverage FROM \"Völker_Stats\"");
			
			while(avgNationRating.next()){
				avgRating = (avgNationRating.getInt("NationAverage"));
			}
			
			Load_Database db = new Load_Database();
			
			for(int spieler_ID=1;spieler_ID<=db.getLoadPlayers().size();spieler_ID++){
				createNewNation = "INSERT INTO \"Völker_Stats\"(\"Spieler_ID\", \"Völker_ID\", \"Rating\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+spieler_ID+"\',"
								+ "\'"+db.getLoadNations().size()+"\',"
								+ "\'"+avgRating+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";

				statement.execute(createNewNation);
			}
			
			statement.close();
			con.close();
		}
		catch (SQLException ex){
			Logger.getLogger(CreateNewNation.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
			Logger.getLogger(CreateNewNation.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Hier wird das neue Volk in den TreeBaum aufgenommen als String Object.
		includeNewNation(newNationName,isPala);
	}
	
	private boolean whichCategory(String category){
		if(category.equals("Paladin")){
			return true;
		}else{
			return false;
		}
	}
	
	private void includeNewNation(String newNationName, boolean isPala){
		
		CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(newNationName);
		if(isPala){
			TreeView_Controller.getVoelker_Root_Paladin().getChildren().add(box);
		}else{
			TreeView_Controller.getVoelker_Root_NonPaladin().getChildren().add(box);
		}
	}

}

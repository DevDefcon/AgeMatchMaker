package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import mainController.TreeView_Controller;
import javafx.scene.control.CheckBoxTreeItem;


public class CreateNewMap {
		
	public CreateNewMap(String newMapName, String category){

		String createNewMap;
		
		try{
			String db_file_name_prefix = Load_Database.dbPfad();
			Connection con = null;
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
			Statement statement = con.createStatement();

			//Erstellt eine neue Zeile in der Tabelle "Karten"
			createNewMap = "INSERT INTO \"Karten\"(\"Name\", \"Kategorie\") "
					+ "VALUES (\'"+newMapName+"\',"
							+ "\'"+category+"\')";

			statement.execute(createNewMap);

			Load_Database db = new Load_Database();
			
			for(int spieler_ID=1;spieler_ID<=db.getLoadPlayers().size();spieler_ID++){
				createNewMap = "INSERT INTO \"Karten_Stats\"(\"Spieler_ID\", \"Karten_ID\", \"Rating\", \"Siege\", \"Niederlagen\") "
						+ "VALUES (\'"+spieler_ID+"\',"
								+ "\'"+db.getLoadMaps().size()+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\',"
								+ "\'"+0+"\')";

				statement.execute(createNewMap);
			}
			
			statement.close();
			con.close();
		}
		catch (SQLException ex){
			Logger.getLogger(CreateNewMap.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException ex){
			Logger.getLogger(CreateNewMap.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Hier wird die neue Karte in TreeBaum aufgenommen als String Object.
		includeNewMap(newMapName, category);
		
	}
	
	private void includeNewMap(String newMapName, String category){
		
		CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(newMapName);
		if(category.equals("Land")){
			TreeView_Controller.getKarten_Root_Landkarte().getChildren().add(box);
		}else if(category.equals("Wasser")){
			TreeView_Controller.getKarten_Root_Wasserkarte().getChildren().add(box);
		}else if(category.equals("Fun")){
			TreeView_Controller.getKarten_Root_Funkarten().getChildren().add(box);
		}
	}
}

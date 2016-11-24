package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import mainController.Match_Controller;

public class Save_Database {


	public void writeData_Spieler(ArrayList<Players> playerList, Teams team){

			String updateSpielerTable;

			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				for(int i=0;i<playerList.size();i++){

					updateSpielerTable = "UPDATE \"Spieler\" "
										+ "SET \"Rating\" = \"Rating\" + "+ team.getTeamRating() +" "
										+ "WHERE \"ID\"=\'" + playerList.get(i).getId() +"\'";
					statement.execute(updateSpielerTable);
				}

				if(team.isTeamWinner()==true){
					for(int i=0;i<playerList.size();i++){

						updateSpielerTable = "UPDATE \"Spieler\" "
											+ "SET \"Siege\" = \"Siege\" + "+ 1 +" "
											+ "WHERE \"ID\"=\'" + playerList.get(i).getId() +"\'";
						statement.execute(updateSpielerTable);
					}
				}else{
					for(int i=0;i<playerList.size();i++){

						updateSpielerTable = "UPDATE \"Spieler\" "
											+ "SET \"Niederlagen\" = \"Niederlagen\" + "+ 1 +" "
											+ "WHERE \"ID\"=\'" + playerList.get(i).getId() +"\'";
						statement.execute(updateSpielerTable);
					}
				}

				statement.close();
			    con.close();

			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}


		public void writeData_Spieler_Stats(ArrayList<Players> teamList, Teams team){

			String updateSpielerTable;


			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				if(team.isTeamWinner()==true){
					for(int i=0;i<teamList.size();i++){

						for(int j=0;j<team.getEnemyTeam().size();j++){
							updateSpielerTable = "UPDATE \"Spieler_Stats\" "
												+ "SET \"Siege\" = \"Siege\" + "+ 1 +" "
												+ "WHERE \"ID_Win\"=\'" + teamList.get(i).getId() +"\' "
												+ "AND \"ID_Lose\"=\'" + team.getEnemyTeam().get(j).getId() +"\'";
							statement.execute(updateSpielerTable);
						}
					}
				}else{
					for(int i=0;i<teamList.size();i++){

						for(int j=0;j<team.getEnemyTeam().size();j++){
							updateSpielerTable = "UPDATE \"Spieler_Stats\" "
												+ "SET \"Niederlagen\" = \"Niederlagen\" + "+ 1 +" "
												+ "WHERE \"ID_Win\"=\'" + teamList.get(i).getId() +"\' "
												+ "AND \"ID_Lose\"=\'" + team.getEnemyTeam().get(j).getId() +"\'";
							statement.execute(updateSpielerTable);
						}
					}
				}

				statement.close();
			    con.close();

			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}


		public void writeData_Völker_Stats(ArrayList<Players> playerList, Teams team){

			String updateNationTable;

			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				for(int i=0;i<playerList.size();i++){

					updateNationTable = "UPDATE \"Völker_Stats\" "
										+ "SET \"Rating\" = \"Rating\" + "+ team.getNationRating() +" "
										+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\' "
										+ "AND \"Völker_ID\"=\'" + playerList.get(i).getNation_id() +"\' ";
					statement.execute(updateNationTable);
				}

				if(team.isTeamWinner()==true){
					for(int i=0;i<playerList.size();i++){

						updateNationTable = "UPDATE \"Völker_Stats\" "
											+ "SET \"Siege\" = \"Siege\" + "+ 1 +" "
											+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\'"
											+ "AND \"Völker_ID\"=\'" + playerList.get(i).getNation_id() +"\'";
						statement.execute(updateNationTable);
					}
				}else{
					for(int i=0;i<playerList.size();i++){

						updateNationTable = "UPDATE \"Völker_Stats\" "
											+ "SET \"Niederlagen\" = \"Niederlagen\" + "+ 1 +" "
											+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\'"
											+ "AND \"Völker_ID\"=\'" + playerList.get(i).getNation_id() +"\'";
						statement.execute(updateNationTable);
					}
				}

				statement.close();
			    con.close();
			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}

		public void writeData_Karten_Stats(ArrayList<Players> playerList, Teams team){

			String updateMapTable;

			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				for(int i=0;i<playerList.size();i++){

					updateMapTable = "UPDATE \"Karten_Stats\" "
							+ "SET \"Rating\" = \"Rating\" + "+  team.getMapRating()+" "
							+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\' "
							+ "AND \"Karten_ID\"=\'" + playerList.get(i).getMap_id() +"\' ";
					statement.execute(updateMapTable);
				}

				if(team.isTeamWinner()==true){
					for(int i=0;i<playerList.size();i++){

						updateMapTable = "UPDATE \"Karten_Stats\" "
								+ "SET \"Siege\" = \"Siege\" + "+ 1 +" "
								+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\'"
								+ "AND \"Karten_ID\"=\'" + playerList.get(i).getMap_id() +"\'";
						statement.execute(updateMapTable);
					}
				}else{
					for(int i=0;i<playerList.size();i++){

						updateMapTable = "UPDATE \"Karten_Stats\" "
								+ "SET \"Niederlagen\" = \"Niederlagen\" + "+ 1 +" "
								+ "WHERE \"Spieler_ID\"=\'" + playerList.get(i).getId() +"\'"
								+ "AND \"Karten_ID\"=\'" + playerList.get(i).getMap_id() +"\'";
						statement.execute(updateMapTable);
					}
				}

				statement.close();
			    con.close();

			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}

		public void writeData_Karten_Volk_Stats(ArrayList<Players> playerList, Teams team){

			String updateTable;


			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				if(team.isTeamWinner()==true){
					for(int i=0;i<playerList.size();i++){

						updateTable ="UPDATE \"Karte_Volk_Stats\" "
										+ "SET \"Siege\" = \"Siege\" + 1"
										+ "WHERE \"ID_Karte\"=\'" + playerList.get(i).getMap_id() +"\'"
										+ "AND \"ID_Volk\"=\'" + playerList.get(i).getNation_id() +"\'";
				    	statement.execute(updateTable);

				    }
				}else{
					for(int i=0;i<playerList.size();i++){

						updateTable ="UPDATE \"Karte_Volk_Stats\" "
										+ "SET \"Niederlagen\" = \"Niederlagen\" + 1"
										+ "WHERE \"ID_Karte\"=\'" + playerList.get(i).getMap_id() +"\'"
										+ "AND \"ID_Volk\"=\'" + playerList.get(i).getNation_id() +"\'";
				    	statement.execute(updateTable);

				    }
				}



				statement.close();
			    con.close();

			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}


		public void writeData_Karten_Factor(){

			String updateMapTable;
			Load_Database dbMap = new Load_Database();

			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();

				int faktorMaxSize = dbMap.getLoadMaps().size();

				for(int i=0;i<dbMap.getLoadMaps().size();i++){

					if(dbMap.getLoadMaps().get(i).getFactor()<faktorMaxSize){
						System.out.println(dbMap.getLoadMaps().get(i).getId());
						updateMapTable = "UPDATE \"Karten\" "
								+ "SET \"Factor\" = \"Factor\" + "+ 1 +" "
								+ "WHERE \"ID\"=\'" + dbMap.getLoadMaps().get(i).getId() +"\' ";
						statement.execute(updateMapTable);
					}
				}

				updateMapTable = "UPDATE \"Karten\" "
						+ "SET \"Factor\" = "+ 1 +" "
						+ "WHERE \"Name\"=\'" + Match_Controller.getChosenMap() +"\' ";
				statement.execute(updateMapTable);


				statement.close();
			    con.close();

			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}

		public void writeData_Match(Teams teamA, Teams teamB){


			String updateMatch_SpielerTables;
			String query;
			String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
			int id=0;


			try{

				String db_file_name_prefix = Load_Database.dbPfad();
				Connection con = null;
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:file:" + db_file_name_prefix,"SA",""); //SA Username : kein Passwort
				Statement statement = con.createStatement();


				// die Tabelle Match wird erst mal beschrieben. Erst anhand der ID kann der Rest bearbeitet werden.
				updateMatch_SpielerTables = "INSERT INTO \"Match\" (\"Datum\", \"Karte\") Values (\'"+date+"\',\'"+Match_Controller.getChosenMap()+"\')";
				statement.execute(updateMatch_SpielerTables);

				//Ermittlung der höchsten ID, die wird benutzt um Match_ID zu bestimmen in der Tabelle Match_Spieler
				query = "SELECT TOP 1 * FROM \"Match\" ORDER BY \"ID\" DESC";
				ResultSet table_01 = statement.executeQuery(query);

				while(table_01.next()){
					id = table_01.getInt("ID");
				}

				//Match daten der jeweiligen Spieler werden jetzt in Match Spieler eingetragen

				for(int i=0;i<Teams.getTeamA().size();i++){

					updateMatch_SpielerTables = "INSERT INTO \"Match_Stats\" (\"Spieler_ID\", \"Volk\", \"Punkte\", \"Sieg/Niederlage\", \"Match_ID\") "
							+ "VALUES (\'"+Teams.getTeamA().get(i).getName()+"\',"
							+ "\'"+Teams.getTeamA().get(i).getChosenNation_Spieler()+"\',"
							+ "\'"+teamA.getTeamRating() +"\' ,"
							+ "\'"+teamA.isTeamWinner()+"\',"
							+ "\'"+id+"\')";
					statement.execute(updateMatch_SpielerTables);
				}

				for(int i=0;i<Teams.getTeamB().size();i++){

					updateMatch_SpielerTables = "INSERT INTO \"Match_Stats\" (\"Spieler_ID\", \"Volk\", \"Punkte\", \"Sieg/Niederlage\", \"Match_ID\") "
							+ "VALUES (\'"+Teams.getTeamB().get(i).getName()+"\',"
							+ "\'"+Teams.getTeamB().get(i).getChosenNation_Spieler()+"\',"
							+ "\'"+teamB.getTeamRating() +"\' ,"
							+ "\'"+teamB.isTeamWinner()+"\',"
							+ "\'"+id+"\')";
					statement.execute(updateMatch_SpielerTables);
				}



				statement.close();
			    con.close();
			   }
			catch (SQLException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			     ex.printStackTrace();
			   }
			catch (ClassNotFoundException ex){
			     Logger.getLogger(Save_Database.class.getName()).log(Level.SEVERE, null, ex);
			   }
		}


	///---------------------------------------------------------------------////



}

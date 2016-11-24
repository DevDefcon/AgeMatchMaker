package match;

import mainController.Match_Controller;
import database.Load_Database;
import database.Teams;
import javafx.scene.control.Button;

public class Match_Zuweisung {
	
	private String nationName, team;
	private int playerIndex, nationIndex, mapIndex;
	private Match_Controller controller;
	
	public Match_Zuweisung(Match_Controller controller) {
		this.controller = controller;
	}

	public void setAssignmentToPlayers(Button player, Button nation, Button map, String team){
		this.team = team;
		boolean includePlayer = checkPlayer(player);
		boolean includeNation = checkNation(nation);
		checkMap(map);
		setMatchPlayer(includePlayer,includeNation);
	}
	
	private boolean checkPlayer(Button player){
		Load_Database db = new Load_Database();
		String playersName = player.getText();
		
		for(int i=0;i<db.getLoadPlayers().size();i++){
			if(db.getLoadPlayers().get(i).getName().equals(playersName)){
				playerIndex = i;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkNation(Button nation){
		Load_Database db = new Load_Database();
		nationName = nation.getText();
		
		for(int i=0;i<db.getLoadNations().size();i++){
			if(db.getLoadNations().get(i).getName().equals(nationName)){
				nationIndex = i;
				nationName = db.getLoadNations().get(i).getName();
				return true;
			}
		}
		return false;
	}
	
	private void checkMap(Button map){
		Load_Database db = new Load_Database();
		String mapName = map.getText();
		
		for(int i=0;i<db.getLoadMaps().size();i++){
			if(db.getLoadMaps().get(i).getName().equals(mapName)){
				mapIndex = db.getLoadMaps().get(i).getId();
				Match_Controller.setChosenMap(mapName);
			}
		}
	}
	
	private void setMatchPlayer(boolean includePlayer, boolean includeNation){
		
		if(includePlayer == true && includeNation == true){
			Load_Database db = new Load_Database();
			
			Match_Controller.getMatchPlayersList().add(db.getLoadPlayers().get(playerIndex));
			int index = Match_Controller.getMatchPlayersList().size()-1;
			setTeam(index);
			
			Match_Controller.getMatchPlayersList().get(index).setChosenNation_Spieler(nationName, 
					db.getLoadPlayers().get(playerIndex).getId(),
					db.getLoadNations().get(nationIndex).getId());
			
			Match_Controller.getMatchPlayersList().get(index).setMapRating_Spieler(db.getLoadPlayers().get(playerIndex).getId(), mapIndex);
			
//			for(int i=0;i<Match_Controller.getMatchPlayersList().size();i++){
//				System.out.println(Match_Controller.getMatchPlayersList().get(i).getName());
//				System.out.println(Match_Controller.getMatchPlayersList().get(i).getChosenNation_Spieler());
//				System.out.println(Match_Controller.getChosenMap());
//				System.out.println(Match_Controller.getMatchPlayersList().get(i).getMapRating());
//			}
			
			controller.getTabeElo_Controller().createTables();
			
		}
	}
	
	private void setTeam(int index){
		
		if(team.equals("teamA")){
			Teams.setTeamA(Match_Controller.getMatchPlayersList().get(index));
			
		}else if(team.equals("teamB")){
			Teams.setTeamB(Match_Controller.getMatchPlayersList().get(index));
		}
	}
	
}

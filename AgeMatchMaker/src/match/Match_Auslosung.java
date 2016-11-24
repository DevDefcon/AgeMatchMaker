package match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import mainController.Match_Controller;
import database.Players;
import database.Maps;
import database.Nationen;
import database.Teams;



public class Match_Auslosung {

	private String chosenNation;
	private String chosenMap;

	private ArrayList<Maps> matchMapsList;
	private ArrayList<Nationen> matchNationsList;
	private ArrayList<Players> matchPlayersList;

	private int index;
	private Random zufallszahl01 = new Random();


	public Match_Auslosung(){
		matchPlayersList 	= Match_Controller.getMatchPlayersList();
		matchMapsList		= Match_Controller.getMatchMapsList();
		matchNationsList	= Match_Controller.getMatchNationsList();
	}

	//Konstruktor f�r zuf�llig Map aus einem nicht zuf�llig generierten Match, wenn Zufall ausgeschaltet wurde um alles manuel zu setzen
	public Match_Auslosung(ArrayList<Maps> mapList){
		this.matchMapsList = mapList;
	}

	/*---------------- Auslosung der Karten welche sich in der matchMapsList ----------------*/

	public void setRandomMap(){

		index 			= zufallszahl01.nextInt(matchMapsList.size());
	    chosenMap 		= matchMapsList.get(index).toString();
	    int map_id		= matchMapsList.get(index).getId();

	    Match_Controller.setChosenMap(chosenMap);

	    for(int i=0;i<matchPlayersList.size();i++){
	    	int players_id 	= matchPlayersList.get(i).getId();
	    	matchPlayersList.get(i).setMapRating_Spieler(players_id, map_id);
		}
	}
	
	//Methode zum zuf�lligen w�hlen einer Map mit Kartenfakor ohne Spieler. Wird genutzt wenn Auslosung ausgeschaltet wurde
	public String setRandomMapWithoutPlayers(){
		index 			= zufallszahl01.nextInt(matchMapsList.size());
		chosenMap 		= matchMapsList.get(index).toString();
		return chosenMap;
	}

	public void setNoDoubleNation(){

		for(int i=0;i<matchPlayersList.size();i++){
			index			= zufallszahl01.nextInt(matchNationsList.size());
			chosenNation 	= matchNationsList.get(index).toString();
			int nation_id	= ((Nationen)matchNationsList.get(index)).getId();

			matchNationsList.remove(index);
			int players_id = matchPlayersList.get(i).getId();

			//Wird auch das Rating des jeweiligen Volkes gesetzt
			matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);
		}
		//Die Spieler im Array werden nach Gr��e des gesamten Rating sortiert(eloRating+mapRating+nationenRating)
		Collections.sort(matchPlayersList);
		writeMatch();
	}


	public void setDoubleNation(){

		for(int i=0;i<matchPlayersList.size();i++){
			index = zufallszahl01.nextInt(matchNationsList.size());
			chosenNation = matchNationsList.get(index).toString();
			int nation_id	= ((Nationen)matchNationsList.get(index)).getId();
			int players_id = matchPlayersList.get(i).getId();

			//Wird auch das Rating des jeweiligen Volkes gesetzt
			matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);
		}
		//Die Spieler im Array werden nach Gr��e des gesamten Rating sortiert(eloRating+mapRating+nationenRating)
		Collections.sort(matchPlayersList);
		writeMatch();
	}

	public void setMirrorNation(){

		// Wichtig zu beachten, hier wird die Sortierung schon vor der Vergabe des NationsRating erledigt. Sonst sind keine Spiegelv�lker gew�hrleistet.
		// Wenn erst nach der Vergabe der V�lker und der V�lkerRatings die Sortierung stattfindet, k�nnten die Spiegelv�lker im selben Team landen.
		Collections.sort(matchPlayersList);

		for(int i=0;i<matchPlayersList.size();i++){
			index = zufallszahl01.nextInt(matchNationsList.size());
			chosenNation = matchNationsList.get(index).toString();
			int nation_id	= ((Nationen)matchNationsList.get(index)).getId();
			int players_id = matchPlayersList.get(i).getId();

			matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);

			//i wird erh�ht damit direkt der n�chste Spieler das selbe Volk bekommt.
			i++;
			if(i<matchNationsList.size()){
				players_id = matchPlayersList.get(i).getId();
				matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);
			}
		}

		writeMatch();
	}

	public void setMirrorNationNoDouble(){

		// Wichtig zu beachten, hier wird die Sortierung schon vor der Vergabe des NationsRating erledigt. Sonst sind keine Spiegelv�lker gew�hrleistet.
		// Wenn erst nach der Vergabe der V�lker und der V�lkerRatings die Sortierung stattfindet, k�nnten die Spiegelv�lker im selben Team landen.
		Collections.sort(matchPlayersList);

		for(int i=0;i<matchPlayersList.size();i++){
			index = zufallszahl01.nextInt(matchNationsList.size());
			chosenNation = matchNationsList.get(index).toString();
			int players_id = matchPlayersList.get(i).getId();
			int nation_id	= ((Nationen)matchNationsList.get(index)).getId();

			matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);

			//i wird erh�ht damit direkt der n�chste Spieler das selbe Volk bekommt.
			i++;
			if(i<matchNationsList.size()){
				players_id = matchPlayersList.get(i).getId();
				matchPlayersList.get(i).setChosenNation_Spieler(chosenNation, players_id, nation_id);
				matchNationsList.remove(index);
			}
		}

		writeMatch();
	}



	private void writeMatch(){

		index = zufallszahl01.nextInt(2);

		int zufallsspieler;
		int ungerade;
		int weakest;


		// der zwei schw�chsten Spieler werden zuf�llig in die Teams geschoben
		if(index==1){
			zufallsspieler=7;
		}
		else{
			zufallsspieler=6;
		}

		//Hier werden bei einer ungerade Anzahl an Spieler die zwei st�rksten Spieler in der Auswahl in ein Team geschoben, dazu kommt der schw�chste Spieler.
		if(matchPlayersList.size() % 2 == 0 || matchPlayersList.size() == 3){
			ungerade= 3;
			weakest = 4;
		}
		else if(matchPlayersList.size() == 5){
			ungerade=1;
			weakest = 10; // wird nie erreicht, soll verhindern dass im zweier Team ein dritter dazu kommt
		}else{
			ungerade=1;
			weakest = matchPlayersList.size()-1;
		}

		for(int i=0;i<matchPlayersList.size();i++){
			if(i==0||i==ungerade||i==weakest||i==zufallsspieler){
				Teams.setTeamA(matchPlayersList.get(i));
			}
			else{
				Teams.setTeamB(matchPlayersList.get(i));
			}
		}

		Collections.shuffle(Teams.getTeamA());
		Collections.shuffle(Teams.getTeamB());
	}


}

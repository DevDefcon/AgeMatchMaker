package eloCalc;




public class Elo_Calculation {

	private int ergebnisRating; //Gültig für Spieler Rating und Nationen Rating
	
	private static int ergebnisMap_Win 		=  10;
	private static int ergebnisMap_Lose 	= -10;
	private Elo_Ratings team;
	
	public Elo_Calculation(Elo_Ratings team){
		this.team = team;
	}
	
	public int calcEloRating(boolean fav_team, boolean isWinner){
		int parameter = setParameter(isWinner);
		double ergDouble;
		
		if(fav_team==true){
			ergDouble = 10*(team.getErwartungswert()+parameter);
			ergDouble = (team.getEloPunkteHoch())+ergDouble;
			ergebnisRating = (int) ergDouble;
			ergebnisRating = ergebnisRating-team.getEloPunkteHoch();
			
			return ergebnisRating;
		}else{
			ergDouble = 10*(isWinner_NonFavTeam(isWinner)+parameter);
			ergDouble = (team.getEloPunkteTief())+ergDouble;
			ergebnisRating = (int) ergDouble;
			ergebnisRating = ergebnisRating-team.getEloPunkteTief();
			
			return ergebnisRating;
		}
	}
	private int setParameter(boolean isWinner){
		int parameter;
		if(isWinner==true){
			parameter	=1;
			return parameter;
		}else{
			parameter	=0;
			return parameter;
		}
	}
	
	private double isWinner_NonFavTeam(boolean isWinner){
		double erwartungswert;
		if(isWinner==true){
			erwartungswert=1+team.getErwartungswert();
			erwartungswert = erwartungswert*(-1);
			return erwartungswert;
		}else{
			erwartungswert=1+team.getErwartungswert();
			erwartungswert = erwartungswert*(-1);
			return erwartungswert;
		}
	}
	
	
	
	public int getErgebnisRating() {
		return ergebnisRating;
	}

	public static int getErgebnisMap_Win() {
		return ergebnisMap_Win;
	}

	public static int getErgebnisMap_Lose() {
		return ergebnisMap_Lose;
	}

}

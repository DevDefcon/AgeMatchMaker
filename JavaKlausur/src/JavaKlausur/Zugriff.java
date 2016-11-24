package JavaKlausur;

public class Zugriff{
	
	private int zahl = 7;
	
	public int zugriffZahl(){
		int zahl = this.zahl;
		return zahl;
	}
	
	public int parseInt(String s) throws MyException{    
		if(!s.startsWith("0")){
			throw new MyException("Startet mit null");
		}
		return zahl;
	}
	
	public Zugriff(int zahl){
		this.zahl = zahl;
	}

	public int getZahl() {
		return zahl;
	}

}

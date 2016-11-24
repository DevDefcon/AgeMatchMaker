package database;

public class Maps {

	private int id, factor;
	private String name;
	private String kategorie;


	public Maps(int id, String name, String kategorie, int factor){
		this.id			= id;
		this.name 		= name;
		this.kategorie 	= kategorie;
		this.factor 	= factor;
	}

	public String getName() {
		return name;
	}

	public String getKategorie() {
		return kategorie;
	}

	public int getId() {
		return id;
	}

	public int getFactor() {
		return factor;
	}

	@Override
	public String toString(){
        return String.format("%s", name);
    }

}

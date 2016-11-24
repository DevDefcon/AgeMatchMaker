package database;

public class Nationen {
	
	private int id;
	private String name;
	private boolean isPala;
	
	public Nationen(int id, String name, boolean isPala){
		
		this.id 	= id;
		this.name 	= name;
		this.isPala = isPala;
	}

	public String getName() {
		return name;
	}

	public boolean isPala() {
		return isPala;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString(){
        return String.format("%s", name);
    }


}

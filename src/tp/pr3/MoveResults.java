package tp.pr3;

	// devol del resultado de la suma, y el "max" como mucho
	// 2 opcion mod del tablero y mod del valor obten
public class MoveResults {
	private boolean moved;
	private int points;
	//private int maxToken;
	
	public MoveResults (boolean mo, int po){
		this.moved = mo;
		this.points = po;
	}
	
// metodos getter y setters
	
	public boolean getMoved(){	
		return this.moved;
	}
	
	
	public int getPoints(){	
		return this.points;
	}
	/*
	public int getMaxToken(){	
		return this.maxToken;
	}*/
	
	
}

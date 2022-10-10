package tp.pr3;

public class Cell {
	private int valor;
	
	public Cell(){
		this.valor = 0; // inicializamos siempre a 0
		
	}
	
// metodos getter y setter para la variable valor
	
	public void setValor(int v){
		this.valor = v;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	
// metodo que comprueba que la celda esta vacia
	
	public boolean isEmtpy(){ 
		return this.valor == 0;
	}
	

	
	public int doMerge(Cell neighbour, GameRules gr){ 
		return gr.merge(this, neighbour);
	}
	
	
}

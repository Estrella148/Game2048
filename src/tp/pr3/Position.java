package tp.pr3;

public class Position {
// coordenadas solo, no el valor que hay en esa pos
	private int fila;
	private int columna;
	
/*	Puesto que nos pasan la fila y la columna, en el siguiente metodo creamos el constructor dandoles el 
 * valor de las parametros f y c que nos pasan
 */
	public Position(int f, int c){ 
		this.fila = f;
		this.columna = c;
	}
	
// Metodos getter y setters
	
	public void setFila(int f){
		this.fila = f;
	}
	public int getFila(){	
		return this.fila;
	}
	
	public void setColumna(int c){
		this.columna = c;
	}
	public int getColumna(){	
		return this.columna;
	}
	
}

package tp.pr3;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class AlmacenEstados {
	
	private static final int CAPACITY = 20;
	
	private  int puntero = 0; // static
	
	private static ArrayList<GameState> listadoEstados = new ArrayList<GameState>(CAPACITY);
	
	public GameState pop(boolean unredo){ //static 
		
		if(isEmpty()){
			throw new EmptyStackException();
		}
		
		
		if(puntero>0 && unredo==true){
			puntero--;
		}else if(puntero<listadoEstados.size() && unredo==false){
			puntero++;
		}
		GameState aux = null;
		if(puntero-1 >=0){
			aux =  listadoEstados.get(puntero-1);
		}else{
			aux =  listadoEstados.get(puntero);
		}
		
		return aux;
		
	}
	
	public  void push(GameState state) {		// static
		
		if(listadoEstados.size() != CAPACITY){
			listadoEstados.add(state);
			puntero++;
		}else{
			for(int i=1; i<listadoEstados.size();i++){
				listadoEstados.set(i-1, listadoEstados.get(i));
			}
			listadoEstados.remove(CAPACITY-1);
			listadoEstados.add(state);
		}
		
		if(isEmpty()){
			throw new EmptyStackException();
		}
		
	}
	public  boolean isEmpty(){ // static
		return this.puntero==1;
		
	}
	public boolean isEmptyRedo(){ // static
		return this.puntero<this.listadoEstados.size();
		
	}
	
	public  void borrarElementos(){ //static
		for(int i=puntero+1; i<listadoEstados.size();i++){
			listadoEstados.remove(i);
		}
	}
	
	public void removeAll(){ //static
		puntero = 0;
		listadoEstados.clear();
	}
	
	public GameState lastState(){
		return this.listadoEstados.get(puntero-1);
	}

}

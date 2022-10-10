package tp.pr3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import tp.pr3.enums.Direction;
import tp.pr3.enums.GameType;
import tp.pr3.excepciones.CommandException;
import tp.pr3.utils.MyStringUtils;

public class Board {
	private Cell [][] _board;
	private int _boardSize; // atributo de la clase board  y quitamos el static
	
	private  Random random;
	
	public GameRules mGr;
	
	
	// Creamos constructor del board
	
	public Board(int tam, Random r, GameRules gr){
		this._board = new Cell[tam][tam]; // creamos el tablero de dimension tam*tam
		this._boardSize = tam; // damos el valor del tamanio del tablero 
		for(int i=0;i<this._boardSize;i++){
			for(int j=0;j<this._boardSize;j++){
				this._board[i][j] = new Cell();
			}
		}
		this.random = r;
		this.mGr = gr;
	}
	
	public void setGr(GameRules gr){
		this.mGr = gr;
	}
		
	public int getTotalEmptyCells(){
		int result = 0;
		for(int i=0;i<this._boardSize;i++){
			for(int j=0;j<this._boardSize;j++){
				if(_board[i][j].isEmtpy()){
					result++;
				}				
			}
		}
		return result;
	}
	
	
	public int getBoardSize(){
		return this._boardSize;
	}
	
	public void setCell(Position pos, int value){
		this._board[pos.getFila()][pos.getColumna()].setValor(value);
	}
	
	public int getCell(Position pos){
		return  this._board[pos.getFila()][pos.getColumna()].getValor();
	}
	
	
	public String toString(){
		//int cellSize =this._boardSize;
		int cellSize =_boardSize;
		String space =" "; 
		String vDelimiter= "|";
		String hDelimiter = "-";
		String cadena = "";
		for(int fila = 0; fila < cellSize; fila++){
			
			cadena = cadena + space;
			cadena = cadena + (MyStringUtils.repeat(hDelimiter, cellSize*8));
			cadena = cadena + "\n";
			
			for(int columna = 0; columna < cellSize; columna++){
				cadena = cadena + vDelimiter;
				if(this._board[fila][columna].getValor() != 0){
					cadena = cadena + (MyStringUtils.centre(String.valueOf(this._board[fila][columna].getValor()),7));					
				}else{
					cadena = cadena + (MyStringUtils.centre("",7));
				}
				
			}
			cadena = cadena + (vDelimiter);
			cadena = cadena + "\n";
			
		}
		cadena = cadena + (space);
		
		cadena = cadena + (MyStringUtils.repeat(hDelimiter, cellSize*8));
		
		cadena = cadena + "\n";		
		
		
		
		
		return cadena;
	}
	
	
	public int getHighest(){
		return mGr.getHighest(this);
	}
	
	
	
	public MoveResults executeMove (Direction dir, boolean simulator, GameRules rules){
		MoveResults r = null;
		
		if(dir == Direction.DOWN){
			r = this.moveDown(simulator);
		}else if(dir == Direction.UP){
			r = this.moveUp(simulator);
		}else if(dir == Direction.LEFT){
			r = this.moveLeft(simulator);
		}else if(dir == Direction.RIGHT){
			r = this.moveRight(simulator);
		}
		
	
		
		
		if(r.getMoved() == true){	
			
			int number = mGr.getNumberAleatorio(this.random);
			
			if(!simulator){
				//rellenamos el tablero con un valor nuevo
				int contador = 0;
				int contadorTotal = 0;
				while(contador<1 && contadorTotal<(_boardSize*2)+1){
					int x = this.random.nextInt(_boardSize);
					int y = this.random.nextInt(_boardSize);
					contadorTotal++;
					if(_board[x][y].isEmtpy()){
						setCell(new Position(x,y),number);
						contador++;
					}
				}
			}
			
		}
			
		
		
		return r;
	}
	
	
	
	int ultimaColumnaVacia(int f, int c){
		int pos = 0;
		boolean encontrado = false;	
		int j = this._boardSize-1;
		
		while(j>c && encontrado == false ){
			if(this._board[f][j].isEmtpy()){
				pos = j;
				encontrado = true;
			}
			j--;
		}
		return pos;
		
	}
	
	int ultimaColumnaVaciaLeft(int f, int c){
		int pos = -1;
		boolean encontrado = false;	
		int j = 0;
		
		while(j<c && encontrado == false ){
			if(this._board[f][j].isEmtpy()){
				pos = j;
				encontrado = true;
			}
			j++;
		}
		return pos;
		
	}
	
	
	int ultimaFilaVaciaUp(int f, int c){
		int pos = -1;
		boolean encontrado = false;	
		int i = 0;
		
		while(i<f && encontrado == false ){
			if(this._board[i][c].isEmtpy()){
				pos = i;
				encontrado = true;
			}
			i++;
		}
		return pos;		
	}
	
	int ultimaFilaVaciaDown(int f, int c){
		int pos = 0;
		boolean encontrado = false;	
		int i = this._boardSize-1;
		
		while(i>f && encontrado == false ){
			if(this._board[i][c].isEmtpy()){
				pos = i;
				encontrado = true;
			}
			i--;
		}
		return pos;	
	}
	
	private boolean calcularLastPosRight(boolean simulator){
		boolean movimiento = false;
		
		for(int i = this._boardSize-1; i >=0; i--){
			for(int j = this._boardSize-1; j >=0; j--){
				if(!this._board[i][j].isEmtpy()  && j!=this._boardSize-1){
					int pos  = ultimaColumnaVacia(i,j);
					if(pos!=0){
						if(!simulator){
							setCell(new Position(i,pos),this._board[i][j].getValor());
							setCell(new Position(i,j),0);
						}
						movimiento = true;
					}
				}				
			}
		}
		
		return movimiento;		
	}
	
	private boolean calcularLastPosLeft(boolean simulator){
		boolean movimiento = false;
		for(int i = 0; i < this._boardSize ; i++){
			for(int j = 0; j < this._boardSize ; j++){
				if(!this._board[i][j].isEmtpy()  && j!=0){
					int pos  = ultimaColumnaVaciaLeft(i,j);
					if(pos!= -1){
						if(!simulator){
							setCell(new Position(i,pos),this._board[i][j].getValor());
							setCell(new Position(i,j),0);
						}	
						
						movimiento = true;
					}
				}				
			}
		}
		return movimiento;
	}
	
	private boolean calcularLastPosUp(boolean simulator){
		boolean movimiento = false;
		for(int i = 0; i < this._boardSize ; i++){
			for(int j = 0; j < this._boardSize ; j++){
				if(!this._board[i][j].isEmtpy()  && i!=0){
					int pos  = ultimaFilaVaciaUp(i,j);
					if(pos!= -1){
						if(!simulator){
							setCell(new Position(pos,j),this._board[i][j].getValor());
							setCell(new Position(i,j),0);
						}							
						movimiento = true;
					}
				}				
			}
		}
		return movimiento;
	}
	
	private boolean calcularLastPosDown(boolean simulator){
		boolean movimiento = false;
		for(int i = this._boardSize-1; i >=0; i--){
			for(int j = this._boardSize-1; j >=0; j--){
				if(!this._board[i][j].isEmtpy()  && i!=this._boardSize-1){
					int pos  = ultimaFilaVaciaDown(i,j);
					if(pos!=0){
						if(!simulator){
							setCell(new Position(pos,j),this._board[i][j].getValor());
							setCell(new Position(i,j),0);
						}						
						movimiento = true;
					}
				}				
			}
		}
		return movimiento;
	}
	
	private MoveResults moveRight(boolean simulator){
		MoveResults r = null;
		boolean movimiento = calcularLastPosRight(simulator);	
		int merge = 0;
		int mergeconseguido = 0;
		for(int i = this._boardSize-1; i >=0; i--){
			for(int j = this._boardSize-1; j >0; j--){
				merge = this._board[i][j].doMerge(this._board[i][j-1],mGr);	
				if(merge!=0){
					if(simulator == false){
						setCell(new Position(i,j),merge);
						setCell(new Position(i,j-1),0);
					}					
					//guardamos puntuacion si hay fusion
					mergeconseguido = mGr.getValorMergeConseguido(merge);
					//mergeconseguido = merge;				
				}else{					
					if(simulator == false){
						setCell(new Position(i,j),this._board[i][j].getValor());
					}
				
				}				
			}
		}
		r = new MoveResults((movimiento || mergeconseguido !=0),mergeconseguido);
		calcularLastPosRight(simulator);		
		return r;
		//crearTrans(boradOrig)
 	}
	
	
	
	private MoveResults moveLeft(boolean simulator){
		MoveResults r = null;
		boolean movimiento = calcularLastPosLeft(simulator);	
		int merge = 0;
		int mergeconseguido = 0;
		for(int i = 0; i < this._boardSize ; i++){
			for(int j = 0; j<this._boardSize-1; j++){
				merge = this._board[i][j].doMerge(this._board[i][j+1],mGr);	
				if(merge!=0){
					if(simulator == false){
						setCell(new Position(i,j),merge);
						setCell(new Position(i,j+1),0);
					}					
					//guardamos puntuacion si hay fusion
					mergeconseguido = mGr.getValorMergeConseguido(merge);
					//mergeconseguido = merge;				
				}else{
					
					if(simulator == false){
						setCell(new Position(i,j),this._board[i][j].getValor());
					}
				}				
			}
		}
		r = new MoveResults((movimiento || mergeconseguido !=0),mergeconseguido);
		calcularLastPosLeft(simulator);		
		return r;
 	}
	
	
	private MoveResults moveUp(boolean simulator){
		MoveResults r = null;		
		boolean movimiento = calcularLastPosUp(simulator);	
		int merge = 0;
		int mergeconseguido = 0;
		for(int i = this._boardSize-1; i >0; i--){
			for(int j = this._boardSize-1; j >=0; j--){
				merge = this._board[i][j].doMerge(this._board[i-1][j],mGr);	
				if(merge!=0){
					if(simulator == false){
						setCell(new Position(i,j),merge);
						setCell(new Position(i-1,j),0);
					}
					
					//guardamos puntuacion si hay fusion
					mergeconseguido = mGr.getValorMergeConseguido(merge);
					//mergeconseguido = merge;				
				}else{
					if(simulator == false){
						setCell(new Position(i,j),this._board[i][j].getValor());
					}
					
				}				
			}
		}
		r = new MoveResults((movimiento || mergeconseguido !=0),mergeconseguido);	
		calcularLastPosUp(simulator);		
		return r;
 	}
	
	private MoveResults moveDown(boolean simulator){
		MoveResults r = null;		
		boolean movimiento = calcularLastPosDown(simulator);		
		int merge = 0;
		int mergeconseguido = 0;
		for(int i = 0; i< this._boardSize-1; i++){
			for(int j = 0; j< this._boardSize; j++){
				merge = this._board[i][j].doMerge(this._board[i+1][j],mGr);	
				if(merge!=0){
					if(simulator == false){
						setCell(new Position(i,j),merge);
						setCell(new Position(i+1,j),0);
					}	
					//guardamos puntuacion si hay fusion
					mergeconseguido = mGr.getValorMergeConseguido(merge);
					//mergeconseguido = merge;				
				}else{
					if(simulator == false){
						setCell(new Position(i,j),this._board[i][j].getValor());
					}	
				}				
			}
		}
		r = new MoveResults((movimiento || mergeconseguido !=0),mergeconseguido);
		calcularLastPosDown(simulator);				
		return r;
 	}
	
	
	
	public int[][] getState(){
		int aux [][] = new int[this._boardSize][this._boardSize];		
		for(int i=0;i<aux.length;i++){
			for(int j=0;j<aux.length;j++){
				aux[i][j] = this._board[i][j].getValor();
			}
		}		
		return aux;		
	}
	
	public void setState(int[][] aState){
		// establece el estado del tablero actual a traves de getState				
		for(int i=0;i<aState.length;i++){
			for(int j=0;j<aState.length;j++){
				this._board[i][j].setValor(aState[i][j]);
			}
		}
	}
	
	public void store(String file){
		//guardar fichero en file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			bw.write("This file stores a saved 2048 game\n\n");
			for (int i = 0; i < _board.length; i++) {
				for (int j = 0; j < _board.length; j++) {
					bw.write(String.valueOf(this.getCell(new Position(i, j))));
					bw.write(" ");
				}
				bw.write("\n");
			}
			bw.write("\n");
			bw.write(""+new Game().getInitCells());
			bw.write(" "+new Game().getScore());
			//falta guardar store y init cell y tipo juego
			if(new Game().getGameType() == GameType.FIB){
				bw.write(" fib");
			}else if(new Game().getGameType() == GameType.INV){
				bw.write(" inverse");
			}else if(new Game().getGameType() == GameType.ORIG){
				bw.write(" original");
			}
			
			
			
			bw.write("\n");
			bw.close();
			
		} catch (IOException e) {
			
		}
	}
	
	public void load(BufferedReader br,String [] numeros) throws IOException, CommandException{
		
		String linea = "--";
		Cell [][] tablero;
		int contador = 0;
		int tamañoCol= numeros.length;
		int tamañoFil = 0;
		
			
		try{
			
		
			tablero = new Cell[tamañoCol][tamañoCol];
			
			
			//Se recorre hasta encontrar salto linea
			while(!linea.equals("")){		
				for (int j = 0; j < numeros.length; j++) {
					Cell c = new Cell();
					c.setValor(Integer.parseInt(numeros[j]));
					tablero[contador][j] = c;
				}
				linea = br.readLine();
				numeros = linea.split(" ");
				contador++;
				tamañoFil++;
			}
			if(tamañoCol == tamañoFil){
				this._boardSize = tamañoCol;
				//guardamos el tablero leido en el board			
				this._board = new Cell[this._boardSize][this._boardSize];
				for (int i = 0; i < this._boardSize; i++) {
					for (int j = 0; j < this._boardSize; j++) {
						this._board[i][j] = tablero[i][j];
					}
				}
			}else{
				throw new CommandException("error, tamaño no valido");
			}
			
				
		}catch(Exception e){
			throw new CommandException("error, tamaño no valido");
		}
		
		
		
		
		
		
	}
	
	
	
}







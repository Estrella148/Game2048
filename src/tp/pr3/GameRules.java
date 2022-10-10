package tp.pr3;

import java.util.Random;

import tp.pr3.enums.Direction;
import tp.pr3.excepciones.GameOverException;

//import tp.pr2.Comands.GameState;

//INTERFAZ

public interface GameRules {
	
	void addNewCellAt(Board board, Position pos, Random rand); //introduce un nuevo valor aleatoriamente
	int merge(Cell self, Cell other); // fusiona 2 celdas y devuelve el valor de la fusion
	int getWinValue(Board board); // devuelve el mejor valor del tablero y compruea si se ha ganado o no
	boolean win(Board board) throws GameOverException; // Comprueba si se ha ganado
	//boolean lose(Board board); // Comprueba si se ha perdido
	
	
	Board createBoard(int size);
	void addNewCell(Board board, Random rand);
	default void initBoard(Board board, int numCells, Random rand) {
		
		//rellenamos el tablero con ic celdas
		int contador = 0;
		while(contador<numCells){
			int x = rand.nextInt(board.getBoardSize());
			int y = rand.nextInt(board.getBoardSize());
			if(board.getCell(new Position(x,y)) == 0){
				addNewCellAt(board, new Position(x,y) , rand);
				contador++;
			}
		}
	}
	
	default boolean lose (Board board) throws GameOverException{
		boolean result = false;
		boolean moved = false;
		
		
		if(board.executeMove(Direction.DOWN, true,this).getMoved()==true){
			moved = true;
		}
		else if(board.executeMove(Direction.UP, true,this).getMoved()==true){
			moved = true;
		}
		else if(board.executeMove(Direction.LEFT, true,this).getMoved()==true){
			moved = true;
		}
		else if(board.executeMove(Direction.RIGHT, true,this).getMoved()==true){
			moved = true;
		}
		if(!moved){
			result = true;
			throw new GameOverException("Game Over!!!");
		}
		
		return result;		
	}
	
	
	int getValorMergeConseguido(int merge);
	int getNumberAleatorio(Random rand);
	int getHighest(Board board);
	
}

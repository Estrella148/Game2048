package tp.pr3;

import java.util.Random;

public class RulesInverse implements GameRules{

	private final int MINIMO = 1;
	private final int MAXIMO = 2048;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {		
		board.setCell(pos, getNumberAleatorio(rand));
	}

	@Override
	public int merge(Cell self, Cell other) {
		int sumaFusion = 0;
		boolean fusion;
		
		fusion = (self.getValor() == other.getValor());
		if(fusion){
			sumaFusion = self.getValor() / 2;
		}
		return sumaFusion;
	}

	@Override
	public int getWinValue(Board board) { // en este caso devuelve el maximo		
		return board.getHighest();
	}

	@Override
	public boolean win(Board board) {
		boolean result = false;

		if(board.getHighest() == MINIMO){
			result = true;
			System.out.println("Well done!");
		}
		
		return result;
	}

	/*
	@Override
	public boolean lose(Board board) {
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
			System.out.println("Game Over!!!");
		}
		
		return result;		
	}*/

	@Override
	public Board createBoard(int size) {
		
		return new Board(size,  new Random(),this);
	}

	@Override
	public void addNewCell(Board board, Random rand) {
		
		Position poslibre = null;
		boolean encontrado = false;
		for(int i=0; i<board.getBoardSize(); i++){
			for(int j=0; j<board.getBoardSize(); j++){
				if(board.getCell(new Position(i,j)) == 0 && encontrado == false){
					poslibre = new Position(i,j);
					encontrado = true;
				}
			}
		}
		this.addNewCellAt(board, poslibre, rand);
		
	}
	
	public int getNumberAleatorio(Random rand){
		int number = rand.nextInt(10);//numeor entre 0..9
		if(number >=0 && number>=8){
			number=2048;
		}else{
			number = 1024;
		}
		return number;
	}

	@Override
	public int getHighest(Board board) {
		int result = 2048;
		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize(); j++) {
				if((board.getCell(new Position(i, j)) < result) && (board.getCell(new Position(i, j)) != 0)){
					result = board.getCell(new Position(i, j));
				}
			}
		}
		return result;
	}

	@Override
	public int getValorMergeConseguido(int merge) {
		int result;
		
		result = MAXIMO / (merge*2);
		
		return result;
		
	}

}

package tp.pr3;

import java.util.ArrayList;
import java.util.Random;


public class RulesFib implements GameRules{
	
	private final int MAXIMO = 144;
	
	private ArrayList<Integer> listadoFib = new ArrayList<Integer>();
	
	public RulesFib(){
		listadoFib.add(1);
		listadoFib.add(1);
		listadoFib.add(2);
		listadoFib.add(3);
		listadoFib.add(5);
		listadoFib.add(8);
		listadoFib.add(13);
		listadoFib.add(21);
		listadoFib.add(34);
		listadoFib.add(55);
		listadoFib.add(89);
		listadoFib.add(144);		
	}
	

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		board.setCell(pos, getNumberAleatorio(rand));
	}
	
	public boolean consecutivos(int self, int other){
		boolean result = false;
		
		for (int i = 0; i <listadoFib.size()-1; i++) {
			if(listadoFib.get(i) == self && listadoFib.get(i+1) == other){
				result = true;
			}else if(listadoFib.get(i) == other && listadoFib.get(i+1) == self){
				result = true;
			}
		}		
		
		return result;
	}

	@Override
	public int merge(Cell self, Cell other) {
		int sumaFusion = 0;
		boolean consecutivos = consecutivos(self.getValor(),other.getValor());
		if(consecutivos){
			sumaFusion = self.getValor() + other.getValor();
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

		if(board.getHighest() == MAXIMO){
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
			number=2;
		}else{
			number = 1;
		}
		return number;
	}

	@Override
	public int getHighest(Board board) {
		int result = 0;
		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize(); j++) {
				if(board.getCell(new Position(i, j))> result){
					result = board.getCell(new Position(i, j));
				}
			}
		}
		return result;
	}

	@Override
	public int getValorMergeConseguido(int merge) {		
		return merge;
		
	}

}

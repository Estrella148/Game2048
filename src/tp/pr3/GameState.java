package tp.pr3;

public class GameState {
	private int score;
	private int highest;
	private int boardState[][];
	
	public GameState(int b[][],  int s,int h){
		this.score = s;
		this.highest= h;
		this.boardState = b;
	}

	public int getScore() {
		return score;
	}

	/*public void setScore(int score) {
		this.score = score;
	}*/

	public int getHighest() {
		return highest;
	}
	/*
	public void setHighest(int highest) {
		this.highest = highest;
	}*/

	public int[][] getBoardState() {
		return boardState;
	}

	/*public void setBoardState(int[][] boardState) {
		this.boardState = boardState;
	}*/

}

package tp.pr3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Random; // importamos la libreria random que es de java
import java.util.Scanner;

import tp.pr3.enums.Direction;
import tp.pr3.enums.GameType;
import tp.pr3.excepciones.CommandException;
import tp.pr3.excepciones.GameOverException;
//EXCEPCIONES capturar la excepción y personalizarla . Poner undo para que salga la excepción.

public class Game {
	
	// hay que hacer el isOver() que controle que max = 2048 y ademas que mire num casillavACIAS = O y no se pueda fusionar

	private static Board board;
	private static int size;
	private static int initCells; // el numero de baldosas ini NO NULAS
	private Random myRamdom; // comportamiento 
	private static long seed;
	
	private static int totalHighest;
	private static int score;
	
	private static GameRules currentRules;
	private AlmacenEstados almacenEstados;
	
	private static boolean terminado;
	private static Scanner sc;
	private static Game g;
	private static GameType gt;


	
// creamos constructor
	public Game(GameRules gr,int s, int iC, long seed, Scanner scanner){
		Game.size = s;
		Game.initCells = iC;
		Game.seed = seed;
		Game.currentRules = gr;
		this.almacenEstados = new AlmacenEstados();
		Game.sc = scanner;
		this.myRamdom = new Random(Game.seed);
		// cuando ponga instanciar, quiere decir que creemos el objeto, en este caso el board
		initBoard();		
		
		Game.g = this;
		Game.gt = GameType.ORIG;
	}
	
	public Game(){
		
	}
	
	public void initBoard(){
		Game.totalHighest = 0;
		Game.score = 0;
		Game.board = new Board(Game.size,this.myRamdom, Game.currentRules);
		currentRules.initBoard(Game.board, Game.initCells, this.myRamdom);				
		
		try{
			this.almacenEstados.push(new GameState(Game.board.getState(), Game.score, Game.totalHighest));
		}catch(EmptyStackException e){
			//System.out.println("Aviso, buffer vacio");
		}
	}
	
	public Scanner getScanner(){
		return Game.sc;
	}
	
	public void setGame(Game g, GameType gt){
		Game.g = g;
		this.setGameType(gt);
	}
	
	
	public long getSeed(){
		return Game.seed;
	}
	
	public boolean isOver() throws GameOverException{
		
		if(currentRules.win(Game.board)){
			return true;
		}else if(currentRules.lose(Game.board) == true){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void setTerminado(boolean t){
		Game.terminado = t;
	}
	
	public boolean getTerminado(){
		return Game.terminado;
	}
	
	
// metodos getter setter
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		Game.size = size;
	}
	public int getInitCells() {
		return initCells;
	}
	public void setInitCells(int initCells) {
		Game.initCells = initCells;
	}
	public Random getMyRamdom() {
		return myRamdom;
	}
	public void setMyRamdom(Random myRamdom) {
		this.myRamdom = myRamdom;
	}
	
	public int getScore(){
		return Game.score;
	}
	
	public GameType getGameType(){
		return Game.gt;
	}
	
	public void setScore(int score){
		Game.score=score;
	}
	
	public void setGameType(GameType gt){
		Game.gt=gt;
	}
	
	//metodos de la clase board
	
	public boolean move(Direction dir){		
		MoveResults r =  Game.board.executeMove(dir, false,Game.currentRules);		
		Game.totalHighest = Game.board.getHighest();
		Game.score = Game.score + r.getPoints();
		if(r.getMoved()==true){
			return true;
		}else{
			return false;
		}
	}
	
	public void reset(){
		initBoard();
	}	
	
	public String toString(){
		String game = Game.board.toString();
		
		game = game +"\n" + "mejor valor: "+ Game.totalHighest + "       score: "+Game.score+ "\n";
		
		return game;
	}
	
	public AlmacenEstados getAlmacenEstados(){
		return this.almacenEstados;
	}
			
	private GameState getState(){
		GameState aux = new GameState(Game.board.getState(),Game.score,Game.totalHighest);
		return aux;
	}
	
	public void guardarGameState(){
		getAlmacenEstados().push(getState()); // lo que hace es guardar en el almacen el estado actual del tablero
	}
	private void setState(GameState aState){
		Game.score = aState.getScore();
		Game.totalHighest = aState.getHighest();
		Game.board.setState(aState.getBoardState());		
	}
	
	public void undo(){
		// devuelve el estado actual del juego llamando al getState de Board
		GameState s = this.almacenEstados.pop(true);
		setState(s);
	}
	
	public void redo(){ 
		// invoca el setState de Board
		GameState s = this.almacenEstados.pop(false);
		setState(s);
		
	}
	
	public void store(String file){
		Game.board.store(file);
	}
	
	public GameType load(String file) throws CommandException{
		String linea = "";
		
			try{
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				br.readLine();
				br.readLine();
				
				//Se trocea la primera linea para saber el tamaño del tablero
				linea = br.readLine();
				String [] numeros = linea.split(" ");
				
				
				Game.board.load(br, numeros);
				
				linea = br.readLine();
				String [] parametros = linea.split(" ");
				
				//se lee el score
				Game.score = Integer.parseInt(parametros[1]);
				
				//se lee el tipo juego
				Game.gt = GameType.parse(parametros[2]);
				
				Game.board.setGr(Game.gt.getRules());	
				
				br.close();		
				
			}catch(IOException e){
				throw new CommandException();
			}catch (NullPointerException e) {
				throw new CommandException("Error, matriz no valida, inserte otro fichero");
			}
			
			
			//Game.totalHighest = Game.board.getHighest();
		
		
		
		return Game.gt;
		
	}
}

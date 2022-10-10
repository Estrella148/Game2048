package tp.pr3.Comands;


//import tp.pr3.Controller;
import java.util.Scanner;

import tp.pr3.Game;
import tp.pr3.enums.GameType;


public class PlayCommand extends Command {
	
	private GameType tipo;
	private Scanner scanner;
	
	
	public PlayCommand() {
		super("play <game>", "donde game sera, original,fib, inverse");
	}

	public PlayCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game){
		boolean terminado = false;
		int size = 3;
		int init = 2;
		long seed = 2;
		String comando;
		//hay que pedir valores para el nuevo juego,si se pulsa intro coger por defecto
		Game g= null;
		
		while(terminado == false){
			terminado = true;
			System.out.println("Inserte el tamaño del tablero");		
			comando = scanner.nextLine();
			if(!comando.equals("")){ // cuando el usuario pulsa ENTER == RETURN, que equivale				
				try{
					size = Integer.parseInt(comando);
				}catch(NumberFormatException e){
					System.out.println("Error, el dato debe ser un numero");
					terminado = false;
				}	
			}	
		}
		
		terminado = false;
		while(terminado == false){
			terminado = true;
			System.out.println("Inserte el numero celdas iniciadas");
			comando = scanner.nextLine();
			if(!comando.equals("")){
				try{
					init = Integer.parseInt(comando);
				}catch(NumberFormatException e){
					System.out.println("Error, el dato debe ser un numero");
					terminado = false;
				}	
			}
		}
		
		terminado = false;
		while(terminado == false){
			terminado = true;
			System.out.println("Inserte la semilla");
			comando = scanner.nextLine();
			if(!comando.equals("")){
				try{
					seed = Integer.parseInt(comando);
				}catch(NumberFormatException e){
					System.out.println("Error, el dato debe ser un numero");
					terminado = false;
				}	
			}
		}
				
		g = new Game(tipo.getRules(),size ,init , seed,scanner);
	
		game.setGame(g,tipo);
		return true;
		
		
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) {
		this.scanner = sc;
		Command c = null;
		if(commandWords.length ==2){
			String comando = commandWords[0]+" "+commandWords[1];
			switch (comando) {
			
				case "play original":
					c = this;	
					this.tipo=GameType.ORIG;
					break;	
				case "play fib":
					c = this;
					this.tipo=GameType.FIB;
					break;	
				case "play inverse":
					c = this;
					this.tipo = GameType.INV;
					break;		
			}
		}
		return c;
	}

}

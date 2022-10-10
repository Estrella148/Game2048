package tp.pr3.Comands;

//import tp.pr3.Controller;
import java.util.Scanner;

import tp.pr3.Game;
import tp.pr3.enums.Direction;

public class MoveCommand extends Command {
	
	// tiene atributos tipo direction
	private Direction dir;
	
	public MoveCommand() {
		super("Move <direction>", "execute a move in one of the four directions, up, down, left, right");
	}

	public MoveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		
		boolean movimiento = game.move(dir);	
		if(movimiento==true){
			game.guardarGameState();
		}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) {
		
		Command c = null;
		
		if(commandWords.length ==2){
			String comando = commandWords[0]+" "+commandWords[1];
			switch (comando) {
			
				case "move down":
					c = this;	
					dir = Direction.DOWN;
					break;	
				case "move up":
					c = this;
					dir = Direction.UP;
					break;	
				case "move right":
					c = this;	
					dir = Direction.RIGHT;
					break;	
				case "move left":
					c = this;		
					dir = Direction.LEFT;
					break;					
				
			}
			
		}
		
		
		return c;
	}

}

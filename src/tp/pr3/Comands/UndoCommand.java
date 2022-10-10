package tp.pr3.Comands;

import java.util.Scanner;


//import tp.pr3.Controller;
import tp.pr3.Game;
import tp.pr3.excepciones.CommandException;

public class UndoCommand extends Command {

	public UndoCommand() {
		super("Undo", "rehace movimiento");
	}
	
	@Override
	public boolean execute(Game game) throws CommandException {
		try{
			if(!game.getAlmacenEstados().isEmpty()){
				game.undo();
			}else{
				throw new CommandException("Error, buffer vacio, no se puede deshacer");
			}
		} catch(Exception e){
			throw new CommandException("Error, buffer vacio, no se puede deshacer");
		}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) {
		
		Command c=null;
		
		if(commandWords.length == 1 && commandWords[0].equals("undo")){
			c = this;
		}
		
		return c;
	}

}

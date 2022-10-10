package tp.pr3.Comands;

import java.util.Scanner;


//import tp.pr3.Controller;
import tp.pr3.Game;
import tp.pr3.excepciones.CommandException;

public class RedoCommand extends NoParamsCommand {
	
	public RedoCommand() {
		super("Redo", "deshace movimiento");
	}
	

	@Override
	public boolean execute(Game game) throws CommandException {
		try{
			if(game.getAlmacenEstados().isEmptyRedo()){
				game.redo();
			}else{
				throw new CommandException("Error, buffer vacio, no se puede rehacer");
			}
		}catch(Exception e){
			throw new CommandException("Error, buffer vacio, no se puede rehacer");
		}
		
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) {
		
		Command c=null;
		
		if(commandWords.length == 1 && commandWords[0].equals("redo")){
			c = this;
		}
		
		return c;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}

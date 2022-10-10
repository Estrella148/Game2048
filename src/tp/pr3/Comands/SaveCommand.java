package tp.pr3.Comands;

import java.util.Scanner;

import tp.pr3.Game;
import tp.pr3.excepciones.CommandException;
import tp.pr3.utils.MyStringUtils;

public class SaveCommand extends Command {
	
	private String file;
	
	public SaveCommand() {
		super("Save <filaname>", "Guardar partida");
	}

	public SaveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		
		game.store(this.file);
		System.out.println("Game successfully saved to file; use load command to reload it.");
		
		return false;		
		
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) throws CommandException {	
		Command c = null;
		if(commandWords.length ==2){
			//String comando = commandWords[0]+" "+commandWords[1];
			if(commandWords[0].equals("save")){				
				this.file = MyStringUtils.confirmFileNameStringForWrite(commandWords[1], sc);
				c = this;
				
			}
			
		}else if (commandWords.length >2){
			throw new CommandException("Invalid filename: the filename contains spaces");
		}else{
			throw new CommandException("Save must be followed by a filename");
		}
		return c;
	}

}

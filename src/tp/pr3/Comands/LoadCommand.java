package tp.pr3.Comands;

//import java.util.Random;

//import tp.pr2.AlmacenEstados;
//import tp.pr2.Board;
//import tp.pr2.Cell;
//import tp.pr2.GameRules;
//import tp.pr2.Position;
//import tp.pr2.enums.Direction;
//import tp.pr3.Controller;
import java.util.Scanner;

import tp.pr3.Game;
//import tp.pr3.Rules2048;
//import tp.pr3.RulesFib;
//import tp.pr3.RulesInverse;
import tp.pr3.enums.GameType;
import tp.pr3.excepciones.CommandException;
import tp.pr3.utils.MyStringUtils;

public class LoadCommand extends Command {
	
	private String file;
	
	public LoadCommand() {
		super("Load <filaname>", "Cargar un fichero");
	}

	public LoadCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws CommandException {
		
		try{
		GameType gt = game.load(file);
		
		System.out.println("Game successfully loaded from file: "+gt.toString());
		}catch(NullPointerException e){
			throw new CommandException();
		}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords,Scanner sc) throws CommandException {		
		Command c = null;
		if(commandWords.length ==2){
			//String comando = commandWords[0]+" "+commandWords[1];
			if(commandWords[0].equals("load")){				
				this.file = MyStringUtils.confirmFileNameStringForRead(commandWords[1], sc);
				c = this;
				
			}			
		}else if (commandWords.length >2){
			throw new CommandException("Invalid filename: the filename contains spaces");
		}else{
			throw new CommandException("Load must be followed by a filename");
		}
		return c;
	}

}

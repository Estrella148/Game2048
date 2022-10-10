package tp.pr3.Comands;

import java.util.Scanner;

import tp.pr3.excepciones.CommandException;

//import tp.pr3.Controller;

public class CommandParser {
	
	public CommandParser() {
	}

	
	// incorparar dos nuevos objetos comando al atributo array availableCommands 
	
	
	private static Command[] availableCommands = {new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand(),
												  new RedoCommand(), new UndoCommand(), new PlayCommand(), new SaveCommand(), new LoadCommand()};
	
	public static Command parseCommand (String[] commandWords,Scanner sc) throws CommandException{
	
		// a su vez invoca al metodo parse de cada subclase command
		Command c0 = null;
		int tamaño = availableCommands.length;
		int i=0;
		while((i < tamaño) && c0==null){
			c0 = availableCommands[i].parse(commandWords,sc);
			i++;
		}
		
		
		return c0;
	}
	
	
	public static String commandHelp(){
		String cadenahelp = "";
		for (int i = 0; i < availableCommands.length; i++) {
			cadenahelp += availableCommands[i].helpText() +" \n";
		}	
		return cadenahelp;
	}
	
	


}

package tp.pr3.Comands;

import java.util.Scanner;


//import tp.pr3.Controller;
import tp.pr3.Game;
import tp.pr3.excepciones.CommandException;

public abstract class Command {
	
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(){
		commandName = "";
	}

	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];

	}

	public abstract boolean execute(Game game) throws CommandException;  // convertir la funcion en una booleana y ya no necesita pasar por parametro el controller , Controller controller

	public abstract Command parse(String[] commandWords, Scanner sc) throws CommandException; //, Controller controller

	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
	
	
}

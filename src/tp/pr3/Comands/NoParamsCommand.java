package tp.pr3.Comands;

import java.util.Scanner;

//import tp.pr3.Controller;


public abstract class NoParamsCommand extends Command{
	
	// esta clase implementa parse haciendo uso del atributo command name de la clase Command
	// las clases que derivan de esta clase solo implimentan el metodo execute (son todas menos la de move commands)
	
	public abstract void execute();
	public NoParamsCommand(String commandInfo, String helpInfo){
		super(commandInfo, helpInfo);
	}
	public NoParamsCommand(){
		
	}
	
	@Override
	public Command parse(String[] commandWords,Scanner sc) {

		Command c=null;
		
		if(commandWords.length == 1 && commandWords[0].equals("exit")){
			c = new ExitCommand();
		}else if(commandWords.length == 1 && commandWords[0].equals("help")){
			c = new HelpCommand();
		}else if(commandWords.length == 1 && commandWords[0].equals("reset")){
			c = new ResetCommand();
		}
		
		
		return c;
	}
	
	
}

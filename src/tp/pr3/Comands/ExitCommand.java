package tp.pr3.Comands;

//import tp.pr3.Controller;
import tp.pr3.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("Exit", "terminate the program");		
	}


	public ExitCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}
	
	@Override
	public void execute() {
		
	}

	@Override
	public boolean execute(Game game) {
		game.setTerminado(true);
		return false;
	}

	

}

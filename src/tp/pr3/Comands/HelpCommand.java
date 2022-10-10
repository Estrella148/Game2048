package tp.pr3.Comands;

//import tp.pr3.Controller;
import tp.pr3.Game;

public class HelpCommand extends NoParamsCommand{

	public HelpCommand() {
		super("Help", "print this help message");
	}
	

	public HelpCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public void execute() {
		
		
		
	}

	@Override
	public boolean execute(Game game) {
		
		System.out.println(CommandParser.commandHelp());
		
		return true;
		
	}

	
	

}

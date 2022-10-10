package tp.pr3.Comands;

//import tp.pr3.Controller;
import tp.pr3.Game;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand() {
		super("Reset", "start a new game");
	}
	
	

	@Override
	public void execute() {
		
		
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		game.getAlmacenEstados().removeAll();
		return true;
	}

	

	

}

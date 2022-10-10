package tp.pr3.excepciones;

public class CommandException extends Exception{  // exception es una clase de java


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandException(){
		super();
	}
	
	public CommandException(String msg){
		super(msg);
	}
	
	
}

package tp.pr3.excepciones;

public class GameOverException extends Exception{  // exception es una clase de java

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOverException(){
		super();
	}
	
	public GameOverException(String msg){
		super(msg);
	}
	
	
}

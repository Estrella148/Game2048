package tp.pr3;


import java.util.EmptyStackException;

import tp.pr3.Comands.Command;
import tp.pr3.Comands.CommandParser;
import tp.pr3.excepciones.CommandException;
import tp.pr3.excepciones.GameOverException;

public class Controller {
	
	private Game game;
	private boolean terminado;	
	
	public Controller(Game g){
		this.game = g;				
	}
	
	public void run(){
		//quitar terminado y obtenerlo del game que es que realmente cambia sus valores
		String opcion = "";
		
		System.out.println(this.game.toString());
		
		while(terminado == false){	
						
			try {
				if(this.game.isOver() || this.game.getTerminado()==true){   
					terminado = true;	
				}
			} catch (GameOverException e) {
				System.out.println(e.getMessage());
			}
			
			if(terminado == false){	
				System.out.println("Command >");			
				
				opcion = this.game.getScanner().nextLine().toLowerCase(); 
				
				String[] opcion2 = opcion.split(" ");// devuelve en un array la frase separada x espacios en este caso
				
				try{					
					Command c = CommandParser.parseCommand(opcion2,this.game.getScanner());
					if(c!=null){					
						boolean resultado = c.execute(this.game); // this == controller que es referencia a la clase en la que estoy	
						if(resultado){
							System.out.println(this.game.toString());
						}
					}else{
						throw new CommandException("Error, no existe ese comando");
					}	
					
				}catch(CommandException e){
					System.out.println(e.getMessage());
				}catch(EmptyStackException e){
					System.out.println("Buffer vacio");
				}
							
				
			}				
		}
	}
	
}

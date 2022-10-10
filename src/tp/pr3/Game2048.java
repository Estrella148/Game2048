package tp.pr3;


import java.util.Random;
import java.util.Scanner;



public class Game2048 {
	

	public static void main (String []args){ // este es el main PRINCIPAL		
				
		Scanner sc = new Scanner(System.in);
		
		
		int dim = 0;
		int ic = 0;
		long seed = 0;
		try{
			dim = Integer.parseInt(args[0]);
			ic = Integer.parseInt(args[1]);
			
			if(args.length>2){
				seed = Long.parseLong(args[2]);
			}else{
				seed = new Random().nextInt(1000);
			}
			
			Controller ct = new Controller(new Game(new Rules2048(),dim,ic,seed, sc));
			ct.run();
			
		}catch(NumberFormatException e){
			System.out.println("Error, alguno de los argumentos no son validos");
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error, no estan los argumentos correctos");
		}
		
			
	
	}
	
	
}

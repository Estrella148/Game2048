package tp.pr3.utils;

public class MyMathsUtils {
	
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}

}

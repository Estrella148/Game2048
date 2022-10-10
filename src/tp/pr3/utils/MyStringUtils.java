package tp.pr3.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.excepciones.CommandException;

public class MyStringUtils {

	public static final int MARGIN_SIZE = 4;

	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	public static String centre(String text, int len) {
		String out = String.format(" %" + len + "s %s %" + len + "s", "", text,
				"");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;
		return out.substring((int) start, (int) end);
	}

	public static boolean validFileName(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			return canWriteLocal(file);
		} else {
			try {
				file.createNewFile();
				file.delete();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public static boolean canWriteLocal(File file) {
		// works OK on Linux but not on Windows (apparently!)
		if (!file.canWrite()) {
			return false;
		}
		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static String confirmFileNameStringForWrite(String filenameString, Scanner in) throws CommandException {
		String loadName = filenameString;
		boolean filename_confirmed = false;
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (!file.exists())
					filename_confirmed = true;
				else {
					loadName = getLoadName(filenameString, in);
					filename_confirmed = true;
				}
			} else {
				// ADD SOME CODE HERE
				throw new CommandException("Error, fichero no valido");
			}
		}
		return loadName;
	}
	
	public static String confirmFileNameStringForRead(String filenameString, Scanner in) throws CommandException {
		String loadName = filenameString;
		boolean filename_confirmed = false;
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (file.exists())
					filename_confirmed = true;
				else {
					throw new CommandException("Error, fichero no existe");
				}
			} else {
				throw new CommandException("Error, fichero no valido");
			}
		}
		return loadName;
	}

	public static String getLoadName(String filenameString, Scanner in) throws CommandException {
		String newFilename = null;
		boolean yesOrNo = false;
		while (!yesOrNo) {
			System.out.print("Quieres sobreescribir el fichero "+filenameString+" ? (y/n)");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
				case "y":
					yesOrNo = true;
					// ADD SOME CODE HERE
					newFilename = filenameString;		
					break;
				case "n":
					yesOrNo = true;
					// ADD SOME CODE HERE
					System.out.print("Inserte un nuevo nombre del fichero");
					String  nombre = in.nextLine();
					newFilename = confirmFileNameStringForWrite(nombre,in);
					break;
				default:
					// ADD SOME CODE HERE					
				}
			} else {
				// ADD SOME CODE HERE
				throw new CommandException("Error, fichero no valido");
			}
		}
		return newFilename;
	}
}

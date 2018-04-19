import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputUtil {
	public static Scanner getScanner(){
		try {
			return new Scanner(new File("sample_input.txt"));
		} catch (FileNotFoundException e) {
			return null;
		} 
	}
	
}

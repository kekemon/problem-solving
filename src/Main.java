
public class Main {
	public static void main(String[] args) {
		TextEditor textEditor = new TextEditor();
		String str = "Emon";
		textEditor.insert(0, str.toCharArray(), str.length());
//		str = "Palsh";
//		textEditor.insert(2, str.toCharArray(), str.length());
		textEditor.delete(1, 1);
		
		char[] text = textEditor.read(0, 10);
		System.out.println(text);
		
	}
}

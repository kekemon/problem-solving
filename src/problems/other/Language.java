package problems.other;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Language {
	final static int WORD_LENGTH = 6;
	final static int LETTER_LENGTH = 256;
	static class Dictionary{
		public Dictionary[] child;
	}
	
	static Dictionary[] dictionary = new Dictionary[LETTER_LENGTH];
	
	private static boolean find(char[] word, int len){
		Dictionary[] child = dictionary;
		for (int i = 0; i < len; i++) {
			if(child == null || child[word[i]] == null){
				return false;
			}
			child = child[word[i]].child;
		}
		return true;
	}
	
	
	private static void toBinary(char ch){
		System.out.println((int)ch);
		for(int i=0;i<8;i++){
			if(((ch>>i)^1) == 0){
				System.out.print(1);
			}else{
				System.out.print(0);
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException  {
//		toBinary('a');
		Scanner scanner = new Scanner(new File("sample_input.txt"));
//		Scanner scanner = new Scanner(System.in);
		
		int dictionaryWordCount = scanner.nextInt();
		
		for (int i = 0; i < dictionaryWordCount; i++) {
			Dictionary[] child = dictionary;
			String word = scanner.next();
			int len = word.length();
			for (int j=0; j<len; j++) {
				int pos = word.charAt(j);
				System.out.println(pos);
				if(child[pos] == null){
					child[pos] = new Dictionary();
				}

				if(child[pos].child == null){
					child[pos].child = new Dictionary[LETTER_LENGTH];
				}
				
				child = child[pos].child;
			}
		}
		
		int inputCount = scanner.nextInt();
		
		for (int i = 0; i < inputCount; i++) {
			String search = scanner.next();
			int len = search.length();
			char[] word = search.toCharArray();
			for (int j=0; j<len; j++) {
				for (int k = 0; k < 8; k++) {
					
					word[j] = (char)(word[j]^(1<<k));
					boolean isFound = find(word, len);
					if(isFound){
						System.out.println("FOUND");
						break;
					}
					word[j] = (char)(((int)word[j])^(1<<k));
				}
			}
		}
		scanner.close();
	}
}

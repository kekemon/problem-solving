import java.util.Scanner;

public class BalancedBrackets {
	static Map map = new Map();
	
	static class Stack{
		char stack[];
		int size = 0;
		
		public Stack(int maxSize) {
			stack = new char[maxSize + 1];
		}
		
		public void push(char value){
			stack[++size] = value;
		}
		
		public char pop(){
			if(isEmpty())
				return '\0';
			return stack[size--];
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
	}
	
	static class Map{
		int map[];
		
		public Map() {
			map = new int[256];
		}
		
		public void insert(char key, char value){
			map[key] = value;
		}
		
		public char get(int key){
			return (char)map[key];
		}
	}
	
	public static boolean isBalanced(char[] line){
		Stack stack = new Stack(line.length);
		for (int j = 0; j < line.length; j++) {
			char c = line[j];
			if(map.get(c) == c){
				stack.push(c);
			}else{
				char ch = stack.pop();
				if(ch != map.get(c)){
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		map.insert('(', '(');
		map.insert('{', '{');
		map.insert('[', '[');
		map.insert(')', '(');
		map.insert('}', '{');
		map.insert(']', '[');
		
		
		for (int i = 0; i < N; i++) {
			if(isBalanced(in.next().toCharArray())){
				System.out.println("YES");
			}else{
				System.out.println("NO");
			}
				
		}
		in.close();
	}
}

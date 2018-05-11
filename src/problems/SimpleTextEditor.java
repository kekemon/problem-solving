package problems;
import java.util.Scanner;

import utils.InputUtil;

public class SimpleTextEditor {
	
	static class Stack{
		private int size = 0;
		private String[] stack;
		
		public Stack(int maxSize) {
			stack = new String[maxSize + 1];
		}
		
		public void append(String value){
			if(isEmpty()){
				push(value);
			}else{
				push(top() + value);
			}
		}
		
		public void push(String value){
			stack[++size] = value;
		}
		
		public String pop(){
			return stack[size--];
		}
		
		public String top(){
			return stack[size];
		}
		
		public boolean isEmpty(){
			return size == 0;
		}

		public void deleteLast(int n) {
			String top = top();
			push(top.substring(0, top.length() - n));
		}
	}
	
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);	
		int N = in.nextInt();
		Stack stack = new Stack(N);
		
		for (int i = 0; i < N; i++) {
			int command = in.nextInt();
			
			switch (command) {
			case 1:
				stack.append(in.next());
				break;
			case 2:
				stack.deleteLast(in.nextInt());
				break;
			case 3:
				System.out.println(stack.top().toCharArray()[in.nextInt() - 1]);
				break;
			case 4:
				stack.pop();
				break;

			default:
				break;
			}
		}
		
		in.close();
	}
}

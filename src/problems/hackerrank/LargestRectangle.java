package problems.hackerrank;
import java.util.Scanner;

import utils.InputUtil;

public class LargestRectangle {
	
	static class Node{
		private int value;
		private int index;
		
		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	static class Stack{
		private int size = 0;
		private Node[] stack;
		
		public Stack(int maxSize) {
			stack = new Node[maxSize + 1];
		}
		
		public void push(int index, int value){
			stack[++size] = new Node(index, value);
		}
		
		public Node pop(){
			return stack[size--];
		}
		
		public Node top(){
			return stack[size];
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Stack stack = new Stack(N);
		long maxRec = 0;
		int i;
		for (i = 0; i < N; i++) {
			int value = in.nextInt();
			if(stack.isEmpty() || stack.top().getValue() <= value){
				stack.push(i, value);
			}else{
				while(!stack.isEmpty() && stack.top().getValue() > value){
					Node top = stack.pop();
					long currectRec;
					
					if(stack.isEmpty()){
						currectRec = top.getValue() * i;
					}else{
						currectRec = top.getValue() * (i - stack.top().getIndex() -1);
					}
					
					if(currectRec > maxRec){
						maxRec = currectRec;
					}
				}
				stack.push(i, value);
			}
		}
		
		while(!stack.isEmpty()){
			Node top = stack.pop();
			long currectRec;
			
			if(stack.isEmpty()){
				currectRec = top.getValue() * i;
			}else{
				currectRec = top.getValue() * (i - stack.top().getIndex() - 1);
			}
			if(currectRec > maxRec){
				maxRec = currectRec;
			}
		}
		System.out.println(maxRec);
		in.close();
	}
}

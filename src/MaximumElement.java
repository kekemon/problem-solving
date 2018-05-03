import java.util.Scanner;

public class MaximumElement {
	static class Stack{
		int stack[];
		int size = 0;
		
		public Stack(int maxSize) {
			stack = new int[maxSize + 1];
		}
		
		public void push(int value){
			stack[++size] = value;
		}
		
		public int pop(){
			if(isEmpty())
				return -1;
			return stack[size--];
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
		
		public int getMaximum(){
			int max = 0;
			for (int i = 1; i <= size; i++) {
				if(max<stack[i]){
					max = stack[i];
				}
			}
			return max;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Stack stack = new Stack(N);
		for (int i = 0; i < N; i++) {
			int command = in.nextInt();
			if(command == 1){
				stack.push(in.nextInt());
			}else if(command == 2){
				stack.pop();
			}else if(command == 3){
				System.out.println(stack.getMaximum());
			}
		}
		in.close();
	}
}

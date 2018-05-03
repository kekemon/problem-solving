import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class EqualStacks {
	
	static class Node{
		int value;
		int sum;
		
		public Node(int value, int sum) {
			this.sum = sum;
			this.value = value;
		}
	}
	
	static class Stack{
		Node stack[];
		int size = 0;
		
		public Stack(int maxSize) {
			stack = new Node[maxSize + 1];
			stack[0] = new Node(0, 0);
		}
		
		private void push(int value){
			int lastSum = stack[size].sum;
			stack[++size] = new Node(value, lastSum + value);
		}
		
		public void push(int[] arr){
			for (int i = arr.length -1; i >=0 ; i--) {
				push(arr[i]);
			}
		}
		
		public void pop(){
			if(!isEmpty())
				size--;
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
		
		public int getSum(){
			return stack[size].sum;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
//		System.setOut(new PrintStream(new File(System.getenv("OUTPUT_PATH"))));
		int n1 = in.nextInt();
		int n2 = in.nextInt();
		int n3 = in.nextInt();
		
		Stack stack1 = new Stack(n1);
		Stack stack2 = new Stack(n2);
		Stack stack3 = new Stack(n3);
		
		int arr1[] = new int[n1];
		int arr2[] = new int[n2];
		int arr3[] = new int[n3];
		
		for (int i = 0; i < n1; i++) {
			arr1[i] = in.nextInt();
		}
		
		for (int i = 0; i < n2; i++) {
			arr2[i] = in.nextInt();
		}
		
		for (int i = 0; i < n3; i++) {
			arr3[i] = in.nextInt();
		}
		stack1.push(arr1);
		stack2.push(arr2);
		stack3.push(arr3);
		
		while(!stack1.isEmpty() && !stack2.isEmpty() && !stack3.isEmpty()){
			int sum1 = stack1.getSum();
			int sum2 = stack2.getSum();
			int sum3 = stack3.getSum();
			
			if(sum1 == sum2 && sum2 == sum3){
				System.out.println(sum1);
				break;
			}else if(sum1>=sum2 && sum1>=sum3){
				stack1.pop();
			}else if(sum2>=sum1 && sum2>=sum3){
				stack2.pop();
			}else{
				stack3.pop();
			}
			
		}
		if(stack1.isEmpty() || stack2.isEmpty() || stack3.isEmpty()){
			System.out.println(0);
		}
		in.close();
	}
}

package problems;
import java.util.Scanner;

import utils.InputUtil;

public class Stacks2Queue {
	
	static class Queue{
		
		private int queue[];
		int front = 0;
		int rare = 0;
		
		public Queue(int maxSize) {
			queue = new int[maxSize];
		}
		
		public void push(int value){
			queue[++rare] = value;
		}
		
		public void pop(){
			++front; 
		}
		
		public int front(){
			return queue[front+1];
		}
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		Queue queue = new Queue(N);
		
		for (int i = 0; i < N; i++) {
			int command = in.nextInt();
			
			if(command == 1){
				queue.push(in.nextInt());
			}else if(command == 2){
				queue.pop();
			}else if(command == 3){
				System.out.println(queue.front());
			}
		}
		
		in.close();
	}
}

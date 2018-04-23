import java.util.Scanner;

public class GuessDataStructure {
	
	static class MaxHeap{
		private int maxHeap[];
		private int size = 0;
		
		public MaxHeap(int maxSize) {
			maxHeap = new int[maxSize + 1];
		}
		
		public void push(int value){
			maxHeap[++size] = value;
			bubbleUp(size);
		}
		
		public int pop(){
			if(isEmpty())
				return -1;
			int min = maxHeap[1];
			delete(1);
			return min;
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
		
		private void delete(int position){
			if(position>0){
				maxHeap[position] = maxHeap[size--];
				sinkDown(position);
			}
		}
		
		private void bubbleUp(int position){
			int parentPos = position/2;
			if(parentPos>0 && maxHeap[parentPos] < maxHeap[position]){
				swap(parentPos, position);
				bubbleUp(parentPos);
			}
		}
		
		private void swap(int pos1, int pos2) {
			int tmp = maxHeap[pos1];
			maxHeap[pos1] = maxHeap[pos2];
			maxHeap[pos2] = tmp;
		}

		private void sinkDown(int position){
			int maxPos = position;
			int leftPos = position*2;
			int rightPos = leftPos + 1;
			
			if(leftPos <= size && maxHeap[leftPos] > maxHeap[maxPos]){
				maxPos = leftPos;
			}
			
			if(rightPos <= size && maxHeap[rightPos] > maxHeap[maxPos]){
				maxPos = rightPos;
			}
			
			if(maxPos != position){
				swap(maxPos, position);
				sinkDown(maxPos);
			}
		}
	}
	
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
	}
	
	static class Queue{
		int queue[];
		int rare = 0;
		int front = 0;
		
		public Queue(int maxSize) {
			queue = new int[maxSize+1];
		}
		
		public void push(int value){
			queue[++rare] = value;
		}
		
		public int pop(){
			if(isEmpty())
				return -1;
			return queue[++front];
		}
		
		public boolean isEmpty(){
			return rare == front;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int N = in.nextInt();
			MaxHeap maxHeap = new MaxHeap(N);
			Stack stack = new Stack(N);
			Queue queue = new Queue(N);
			int popCount = 0;
			
			int heapPopCount = 0;
			int stackPopCount = 0;
			int quePopCount = 0;
			
			for (int i = 0; i < N; i++) {
				int command = in.nextInt();
				int value = in.nextInt();
				
				if(command == 1){
					maxHeap.push(value);
					stack.push(value);
					queue.push(value);
				}else if(command == 2){
					if(popCount == heapPopCount && maxHeap.pop() == value){
						heapPopCount++;
					}
					
					if(popCount == stackPopCount && stack.pop() == value){
						stackPopCount++;
					}
					
					if(popCount == quePopCount && queue.pop() == value){
						quePopCount++;
					}
					popCount++;			
				}
			}
			int count = 0;
			String answer = "impossible";
			
			if(popCount == stackPopCount){
				count++;
				answer = "stack";
			}
			if(popCount == heapPopCount){
				count++;
				answer = "priority queue";
			}
			if(popCount == quePopCount){
				count++;
				answer = "queue";
			}
			
			if(count > 1){
				answer = "not sure";
			}
			System.out.println(answer);
		}
	}
}

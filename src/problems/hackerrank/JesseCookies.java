package problems.hackerrank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import utils.InputUtil;

public class JesseCookies {
	
	static class MinHeap{
		
		private int heap[];
		private int size = 0;
		
		public MinHeap(int A[]) {
			heap = new int[A.length+1];
			for (int i = 0; i < A.length; i++) {
				insert(A[i]);
			}
		}
		
		public void insert(int value){
			heap[++size] = value;
			bubbleUp(size);
		}
		
		public int extractMin(){
			int min = heap[1];
			heap[1] = heap[size--];
			sinkDown(1);
			return min;
		}
		
		private void swap(int pos1, int pos2){
			int tmp = heap[pos1];
			heap[pos1] = heap[pos2];
			heap[pos2] = tmp;
		}
		
		private void bubbleUp(int position){
			if(position>0){
				int parent = position/2;
				if(heap[parent]>heap[position]){
					swap(parent, position);
					bubbleUp(parent);
				}
			}
		}
		
		private void sinkDown(int position){
			int left = 2*position;
			int right = left + 1;
			
			int min = position;
			if(left <= size && heap[left] < heap[min]){
				min = left;
			}
			
			if(right <= size && heap[right] < heap[min]){
				min = right;
			}
			
			if(min != position){
				swap(min, position);
				sinkDown(min);
			}
		}
		
	}
	
	
	static int cookies(int K, int[] A) {
		try {
			int count = 0;
	        MinHeap minHeap = new MinHeap(A);
	        int firstMin;
	        while( (firstMin =  minHeap.extractMin()) < K ){
	        	int secondMin =  minHeap.extractMin();
	            int sweetness = firstMin + (2 * secondMin);
	            minHeap.insert(sweetness);
	            count++;
	        }
	        
	        return count;
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
		
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
//		System.setOut(new PrintStream(new File(System.getenv("OUTPUT_PATH"))));
		
		int N = in.nextInt();
		int K = in.nextInt();
		int A[] = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
		}
		System.out.println(cookies(K, A));
		in.close();
	}

}

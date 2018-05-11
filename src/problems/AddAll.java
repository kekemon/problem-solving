package problems;
import java.util.Scanner;

import utils.InputUtil;

public class AddAll {
	static class MinHeap{
		private long minHeap[];
		private int size = 0;
		
		public MinHeap(int maxSize) {
			minHeap = new long[maxSize + 1];
		}
		
		public void insert(long value){
			minHeap[++size] = value;
			bubbleUp(size);
		}
		
		public long extractMin(){
			long min = minHeap[1];
			delete(1);
			return min;
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
		
		private void delete(int position){
			if(position>0){
				minHeap[position] = minHeap[size--];
				sinkDown(position);
			}
		}
		
		private void bubbleUp(int position){
			int parentPos = position/2;
			if(parentPos>0 && minHeap[parentPos]>minHeap[position]){
				swap(parentPos, position);
				bubbleUp(parentPos);
			}
		}
		
		private void swap(int pos1, int pos2) {
			long tmp = minHeap[pos1];
			minHeap[pos1] = minHeap[pos2];
			minHeap[pos2] = tmp;
		}

		private void sinkDown(int position){
			int minPos = position;
			int leftPos = position*2;
			int rightPos = leftPos + 1;
			
			if(leftPos <= size && minHeap[leftPos]<minHeap[minPos]){
				minPos = leftPos;
			}
			
			if(rightPos <= size && minHeap[rightPos]<minHeap[minPos]){
				minPos = rightPos;
			}
			
			if(minPos != position){
				swap(minPos, position);
				sinkDown(minPos);
			}
		}
	}
	
	private static long getAddCost(MinHeap minHeap) {
		long minCost = 0;
		while(!minHeap.isEmpty()){
			long min1 = minHeap.extractMin();
			long min2 = 0;
			if(!minHeap.isEmpty()){
				min2 = minHeap.extractMin();
			}
			long cost = min1 + min2; 
			minCost+= cost;
			if(!minHeap.isEmpty()){
				minHeap.insert(cost);
			}
		}
		return minCost;
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		
		int N;
		while((N = in.nextInt()) != 0){
			MinHeap minHeap = new MinHeap(N);
			for (int i = 0; i < N; i++) {
				minHeap.insert(in.nextInt());
			}
			System.out.println(getAddCost(minHeap));
		}
	}
	
}

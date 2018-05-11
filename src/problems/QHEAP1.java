package problems;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QHEAP1 {

	private final static int MAX_SIZE = 100000;
//	private final static int MAX_SIZE = 50;
	
	static class MinHeap{
		private int size;
		int heap[];
		
		public MinHeap() {
			 size = 0;
			 heap = new int[MAX_SIZE];
		}
		
		public  int getIndex(int value) {
			for (int i = 1; i <= size; i++) {
				if(value == heap[i]){
					return i;
				}
			}
			return 0;
		}
		
		public  int getMin() {
			return heap[1];
		}

		public  void delete(int value) {
			int index = getIndex(value);
			if(index != 0){
				heap[index] = heap[size];
				size--;
				sinkDown(index);
			}
		}

		private void sinkDown(int pos) {
			int leftChild = 2*pos;
			int rightChild = 2*pos + 1;
			int small = pos;
			if(leftChild<=size && heap[leftChild]<heap[pos]){
				small = leftChild;
			}
			
			if(rightChild<=size && heap[rightChild]<heap[small]){
				small = rightChild;
			}
			
			if(pos != small){
				swap(small, pos);
				sinkDown(small);
			}
		}

		public  void insert(int value) {
			heap[++size] = value;
			bubbleUp(size);
		}

		private void bubbleUp(int pos) {
			int parent = pos/2;
			if(parent>0 && heap[parent]>heap[pos]){
				swap(parent, pos);
				bubbleUp(parent);
			}
		}

		private void swap(int i, int j) {
			int tmp = heap[i];
			heap[i] = heap[j];
			heap[j] = tmp;
		}

		public void print() {
			for (int i = 1; i <= size; i++) {
				System.out.print(heap[i]);
			}
			System.out.println();
		}
		
		
	}
	
	
    public static void main(String[] args) throws FileNotFoundException {
//    	Scanner in = new Scanner(new File("sample_input.txt"));
    	Scanner in = new Scanner(System.in);
    	int command = in.nextInt();
    	MinHeap heap = new MinHeap();
    	for (int i = 0; i < command; i++) {
    		int query = in.nextInt();
    		if(query == 1){
    			heap.insert(in.nextInt());
    		}else if(query == 2){
    			heap.delete(in.nextInt());
    		}else if(query == 3){
    			System.out.println(heap.getMin());
    		}
    	}
//    	heap.print();
    	in.close();
    }

	
}
package problems.hackerrank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import utils.InputUtil;


public class MinAvgWait {

	static class MinHeap{
		private Customer heap[];
		private int size = 0;
		
		public MinHeap(int maxSize) {
			heap = new Customer[maxSize + 1];
		}
		
		public MinHeap(Customer[] customers) {
			heap = new Customer[customers.length + 1];
			for (Customer customer : customers) {
				insert(customer);
			}
		}
		
		public int getSize() {
			return size;
		}

		public void insert(Customer customer) {
			heap[++size] = customer;
			bubbleUp(size);
		}
		
		public void delete(int position) {
			if(position>0 && position<=size){
				heap[position] = heap[size--];
				sinkDown(position);
			}
		}

		public Customer extarctMin() {
			Customer customer = heap[1];
			delete(1);
			return customer;
		}
		
		private void sinkDown(int position) {
			int leftChild = position * 2;
			int rightChild = leftChild + 1;
			int minPos = position;
			
			if(leftChild <= size && heap[minPos].getCookTime() > heap[leftChild].getCookTime()){
				minPos = leftChild;
			}
			
			if(rightChild <= size && heap[minPos].getCookTime() > heap[rightChild].getCookTime()){
				minPos = rightChild;
			}
			
			if(minPos != position){
				swap(position, minPos);
				sinkDown(minPos);
			}
		}
		
		private void bubbleUp(int position) {
			int parentPosition = position/2;
			if(parentPosition > 0 && heap[parentPosition].getCookTime()>heap[position].getCookTime()){
				swap(parentPosition, position);
				bubbleUp(parentPosition);
			}
		}

		private void swap(int parentPosition, int position) {
			Customer tmp = heap[position];
			heap[position] = heap[parentPosition];
			heap[parentPosition] = tmp;
		}
		
	}
	
	
	static class Customer{
		
		private long arivalTime;
		private long cookTime;
		
		public Customer(long arivalTime, long cookTime) {
			setArivalTime(arivalTime);
			setCookTime(cookTime);
		}
		
		public long getArivalTime() {
			return arivalTime;
		}

		public void setArivalTime(long arivalTime) {
			this.arivalTime = arivalTime;
		}

		public long getCookTime() {
			return cookTime;
		}

		public void setCookTime(long cookTime) {
			this.cookTime = cookTime;
		}
	}
	
	private static void mergeSort(Customer[] arr, int left, int right) {
		if(left < right){
			int mid = (left + right)/2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, right, mid);
		}
	}


	private static void merge(Customer[] arr, int left, int right, int mid) {
		int leftSize = mid - left + 1;
		int rightSize = right - mid;
		
		Customer L[] = new Customer[leftSize];
		Customer R[] = new Customer[rightSize];
		
		for (int i = 0; i < leftSize; i++) {
			L[i] = arr[left + i];
		}
		
		for (int i = 0; i < rightSize; i++) {
			R[i] = arr[mid + i + 1];
		}
		
		int i= 0, j = 0;
		int k = left;
		
		while(j < rightSize && i < leftSize){
			if(L[i].getArivalTime() < R[j].getArivalTime()){
				arr[k++] = L[i++];
			}else{
				arr[k++] = R[j++];
			}
		}
		
		while (i < leftSize) {
			arr[k++] = L[i++];
		}
		
		while (j < rightSize) {
			arr[k++] = R[j++];
		}
	}
	
	private static long max(long a, long b){
		if(a>b) return a;
		return b;
	}
	
	private static long getMinAvgWaitTime(Customer[] customers) {
		mergeSort(customers, 0, customers.length-1);
		
		long currentTime = 0; 
		long totatlWaitTime = 0;
		
		MinHeap minHeap = new MinHeap(customers.length);
		
		for (int i = 0; i < customers.length; i++) {
			if(i == 0 || customers[i].getArivalTime() <= currentTime){
				if(minHeap.getSize() == 0){
					currentTime = max(currentTime, customers[i].getArivalTime());
				}
				minHeap.insert(customers[i]);
			}else if(minHeap.getSize() > 0){
				while(minHeap.getSize() > 0){
					Customer customer = minHeap.extarctMin();
					currentTime += customer.getCookTime();
					totatlWaitTime += (currentTime - customer.getArivalTime());
				}
				currentTime = max(currentTime, customers[i].getArivalTime());
				minHeap.insert(customers[i]);
			}
		} 
		while(minHeap.getSize() > 0){
			Customer customer = minHeap.extarctMin();
			if(customer.getArivalTime()>currentTime){
				totatlWaitTime += customer.getCookTime();
				currentTime = customer.getArivalTime()+customer.getCookTime();
			}else{
				long diff = currentTime - customer.getArivalTime();
				System.out.println(diff);
				totatlWaitTime += (diff + customer.getCookTime());
				currentTime += (diff + customer.getCookTime());
			}
		}
		return totatlWaitTime/customers.length;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
//		System.setOut(new PrintStream(new File(System.getenv("OUTPUT_PATH"))));
		int nCustomers = in.nextInt();
		Customer customers[] = new Customer[nCustomers];
		
		for (int i = 0; i < nCustomers; i++) {
			Customer customer =  new Customer(in.nextInt(), in.nextInt());
			customers[i] = customer;
		}
		System.out.println(getMinAvgWaitTime(customers));
		
		in.close();
	}	
}

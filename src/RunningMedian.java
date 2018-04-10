import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class RunningMedian {
	
	private static void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	static class MinHeap{
		int arr[];
		int size = 0;
		
		public double getMin(){
			return arr[1];
		}
		
		public MinHeap(int n) {
			arr = new int[n+1];
		}
		
		public int getSize(){
			return size;
		}
		
		public void insert(int value){
			arr[++size] = value;
			bubbleUp(size);
		}
		
		private void bubbleUp(int pos) {
			int parent = pos/2;
			if(parent>0 && arr[parent]>arr[pos]){
				swap(arr, parent, pos);
				bubbleUp(parent);
			}
		}
		
		public int extractMin(){
			int min = arr[1];
			arr[1] = arr[size];
			size--;
			sinkDown(1);
			return min;
		}

		private void sinkDown(int pos) {
			int left = 2*pos;
			int right = 2*pos + 1;
			
			int min = pos;
			if(left <= size && arr[min]>arr[left]){
				min = left;
			}
			
			if(right <= size && arr[min]>arr[right]){
				min = right;
			}
			
			if(pos != min){
				swap(arr, pos, min);
				sinkDown(min);
			}
			
		}
	}
	
	
	static class MaxHeap{
		int arr[];
		int size = 0;
		
		public double getMax(){
			return arr[1];
		}
		
		public MaxHeap(int n) {
			arr = new int[n+1];
		}
		
		public int getSize(){
			return size;
		}
		
		public void insert(int value){
			arr[++size] = value;
			bubbleUp(size);
		}
		
		private void bubbleUp(int pos) {
			int parent = pos/2;
			if(parent>0 && arr[parent]<arr[pos]){
				swap(arr, parent, pos);
				bubbleUp(parent);
			}
		}
		
		public int extractMax(){
			int max = arr[1];
			arr[1] = arr[size];
			size--;
			sinkDown(1);
			return max;
		}

		private void sinkDown(int pos) {
			int left = 2*pos;
			int right = 2*pos + 1;
			
			int max = pos;
			if(left <= size && arr[max]<arr[left]){
				max = left;
			}
			
			if(right <= size && arr[max]<arr[right]){
				max = right;
			}
			
			if(pos != max){
				swap(arr, pos, max);
				sinkDown(max);
			}
			
		}
	}
	
    /*
     * Complete the runningMedian function below.
     */
    static double[] runningMedian(int[] a) {
    	double result[] = new double[a.length];
    	MinHeap minHeap = new MinHeap(a.length);
    	MaxHeap maxHeap = new MaxHeap(a.length);
    	for (int i = 0; i < a.length; i++) {
    		if(maxHeap.getMax()<a[i]){
    			minHeap.insert(a[i]);
    		}else{
    			maxHeap.insert(a[i]);
    		}
    		
    		int diff = minHeap.getSize() - maxHeap.getSize();
    		if(diff>1){
    			maxHeap.insert(minHeap.extractMin());
    		}else if(diff<-1){
    			minHeap.insert(maxHeap.extractMax());
    		}
    		
    		if(minHeap.getSize()>maxHeap.getSize()){
    			result[i] = minHeap.getMin();
    		}else if(minHeap.getSize()<maxHeap.getSize()){
    			result[i] = maxHeap.getMax();
    		}else{
    			result[i] = (minHeap.getMin() + maxHeap.getMax())/2;
    		}
		}
    	
    	return result;

    }

//    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
    	 Scanner scanner = new Scanner(new File("sample_input.txt"));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sample_output.txt"));
        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

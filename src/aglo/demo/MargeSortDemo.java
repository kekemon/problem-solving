package aglo.demo;

import java.util.Scanner;

public class MargeSortDemo {
	private static void sort(int[] arr, int start, int end) {
		if(start<end){
			int mid = (start+end)/2;
			sort(arr, start, mid);
			sort(arr, mid+1, end);
			merge(arr, start, end, mid);
		}	
	}
	
	private static void merge(int[] arr, int start, int end, int mid) {
		int L = mid - start + 1;
		int R = end - mid;
		int Left[] = new int[L];
		int Right[] = new int[R];
		
		for(int i=0; i<L; i++){
			Left[i] = arr[i+start];
		}
		
		for(int i=0; i<R; i++){
			Right[i] = arr[i+mid+1];
		}
		
		int i=0,j=0;
		int k = start;
		while(i<L && j<R){
			if(Right[j]>Left[i]){
				arr[k++] = Left[i++];
			}else{
				arr[k++] = Right[j++];
			}
		}
		
		
		while(j<R){
			arr[k++] = Right[j++];
		}
		
		while(i<L){
			arr[k++] = Left[i++];
		}
		
	}
	
	private static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
       
        sort(arr, 0, n-1);
        print(arr);
        
        in.close();
	}
}

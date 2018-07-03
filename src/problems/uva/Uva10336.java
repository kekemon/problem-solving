package problems.uva;

import java.util.Scanner;

public class Uva10336 {
	private static int row;
	private static int col;
	private static char[][] world;
	private static int dx[] = new int[]{0, 1 , -1, 0};
	private static int dy[] = new int[]{1, 0 , 0, -1};
	private static int[] count;
	
	private static boolean isSafe(int x, int y, char c){
		if(x >=0 && y>=0 && x<row && y<col && world[x][y] == c) return true;
		return false;
	}
	
	private static void fill(int x, int y, char c){
		world[x][y] = '_';
		for (int i = 0; i < dx.length; i++) {
			int nX = x + dx[i];
			int nY = y + dy[i];
			if(isSafe(nX, nY, c)){
				fill(nX, nY, c);
			}
		}
	}
	
	private static void mergesort(int arr[], int start, int end){
		if(start<end){
			int mid = (start + end)/2;
			mergesort(arr, start, mid);
			mergesort(arr, mid+1, end);
			merge(arr, start, mid, end);
		}
	}
	
	
	
	private static void merge(int[] arr, int start, int mid, int end) {
		int L[] = new int[mid - start + 1];
		int R[] = new int[end - mid];
		
		for (int i = 0; i < L.length; i++) {
			L[i] = arr[start + i];
		}
		
		for (int i = 0; i < R.length; i++) {
			R[i] = arr[mid + i + 1];
		}
		
		int k = start;
		int i = 0;
		int j = 0;
		
		while(i<L.length && j<R.length){
			if(count[L[i]] > count[R[j]]){
				arr[k++] = L[i++];
			}else if(count[L[i]] == count[R[j]]){
				if(L[i] < R[j]){
					arr[k++] = L[i++];
				}else{
					arr[k++] = R[j++];
				}
			}else{
				arr[k++] = R[j++];
			}
		}
		
		while(i<L.length){
			arr[k++] = L[i++];
		}
		
		while(j<R.length){
			arr[k++] = R[j++];
		}
	}

	public static void main(String[] args) {
		Scanner in = utils.InputUtil.getScanner();
		//Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		
		for (int tc = 1; tc <= TC; tc++) {
			row = in.nextInt();
			col = in.nextInt();
			world = new char[row][col];
			count = new int[27];
			int[] region = new int[26];
			int regionCount = 0;
			for (int i = 0; i < row; i++) {
				world[i] = in.next().toCharArray();
			}
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(world[i][j] != '_'){
						char c = world[i][j];
						int index = (int)c -96;
						if(count[index] == 0){
							region[regionCount++] = index;
						}
						count[index]++;
						fill(i, j, c);
					}
				}
			}
			mergesort(region, 0, regionCount - 1);
			System.out.println("World #" + tc);
			for (int i = 0; i < regionCount; i++) {
				System.out.println((char)(region[i] + 96) + ": " + count[region[i]]);
			}
		}
		
		in.close();
	}
}

package problems;
import java.util.Scanner;

import utils.InputUtil;

public class UvaTreeColoring {
	private static Node[] nodes;
	private static int MAX_CHILD;
	
	
	static class Node{
		private int childCount;
		private Node[] childList;
		private int cost;
		private int sumCost;
		private Node parent;
		
		public Node(int cost) {
			parent = null;
			this.cost = cost;
			childCount = 0;
			childList = new Node[MAX_CHILD];
			sumCost = cost;
		}
		
		public void addChild(Node child){
			childList[childCount++] = child;
		}


		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
		
		public int calCost(){
			for (int i = 0; i < childCount; i++) {
				sumCost += childList[i].calCost();
			}
			mergeSort(0, childCount - 1);
			return sumCost;
		}
		
		private void mergeSort(int start, int end) {
			if(start<end){
				int mid = (start + end)/2;
				mergeSort(start, mid);
				mergeSort(mid+1, end);
				merge(start, end, mid);
			}
		}

		private void merge(int start, int end, int mid) {
			int L = mid - start + 1;
			int R = end - mid;
			Node[] left = new Node[L];
			Node[] right = new Node[R];
			
			for (int i = 0; i < L; i++) {
				left[i] = childList[start+i];
			}
			
			for (int i=0; i < L; i++) {
				left[i] = childList[start + i];
			}
			
			for (int i=0; i < R; i++) {
				right[i] = childList[mid + i + 1];
			}
			int i =0;
			int j =0;
			int k = start;
			while(i<L && j<R){
				if(left[i].sumCost > right[j].sumCost){
					childList[k++] = left[i++];
				}else if(left[i].sumCost == right[j].sumCost){
					if(left[i].cost>right[j].cost){
						childList[k++] = left[i++];
					}else{
						childList[k++] = right[j++];
					}
				}else{
					childList[k++] = right[j++];
				}
			}
			
			while(i<L){
				childList[k++] = left[i++];
			}
			
			while(j<R){
				childList[k++] = right[j++];
			}
		}
	}
	
	public static int getMinCost(Node[] nodes, int count, int time){ 
		if(count>0){
			
			return getMinCost(nodes, count, time);
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int N = 0;
		int R = 0;
		while ((N = in.nextInt()) != 0 && (R = in.nextInt()) != 0){
			MAX_CHILD = N-1;
			nodes = new Node[N+1];
			
			for (int i = 1; i <= N; i++) {
				nodes[i] = new Node(in.nextInt());
			}
			
			
			for (int i = 0; i < N-1; i++) {
				int parent = in.nextInt();
				int child = in.nextInt();
				
				nodes[parent].addChild(nodes[child]);
				nodes[child].setParent(nodes[parent]);
			}
			
			nodes[R].calCost();
			System.out.println(nodes[R].cost + getMinCost(nodes[R].childList, nodes[R].childCount, 2));
		}
		
		
		
		
		in.close();
	}
}

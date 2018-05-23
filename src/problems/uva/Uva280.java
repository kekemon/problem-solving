package problems.uva;

import java.util.Scanner;
import utils.InputUtil;

public class Uva280 {
	private static int nVertex;
	private static boolean graph[][];
	
	static class Node{
		private int value;
		private Node next;
		
		public Node(int value) {
			this.setValue(value);
			setNext(null);
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	static class NodeList{
		private Node root;
		private Node last;
		private int size;
		
		public NodeList() {
			root = null;
			last = null;
			size = 0;
		}
		
		public void add(int value){
			if(root == null){
				root = new Node(value);
				last = root;
			}else{
				Node node = new Node(value);
				last.setNext(node);
				last = node;
			}
			size++;
		}
		
		public int size(){
			return size;
		}
		
		public Node getRoot(){
			return root;
		}
		
	}
	
	private static NodeList getAvailableList(int src){
		NodeList nodeList = new NodeList();
		
		for (int des = 1; des <= nVertex; des++) {
			if(!graph[src][des]){
				nodeList.add(des);
			}
		}
		
		return nodeList;
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		
		while((nVertex = in.nextInt()) != 0){
			graph = new boolean[nVertex+1][nVertex+1];
			int src, des;
			while((src = in.nextInt()) != 0){
				while((des = in.nextInt()) != 0){
					graph[src][des] = true;
				}
			}
			
			for(int k = 1; k<=nVertex; k++){
				for(int i = 1; i<=nVertex; i++){
					for(int j = 1; j<=nVertex; j++){
						if(graph[i][k] && graph[k][j]){
							graph[i][j] = true;
						}
					}
				}
			}
			
			int nQuery = in.nextInt();
			for (int i = 0; i < nQuery; i++) {
				NodeList nodeList = getAvailableList(in.nextInt());
				System.out.print(nodeList.size());
				Node root = nodeList.getRoot();
				
				while(root != null){
					System.out.print(" " + root.getValue());
					root = root.getNext();
				}
				System.out.println();
				
			}
		}
		
	}
}

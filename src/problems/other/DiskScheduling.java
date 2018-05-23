package problems.other;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiskScheduling {

	static class Node{
		Node left;
		Node right;
		int value;
		
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
		public void insert(int value){
			if(this.value<value){
				if(right == null){
					right = new Node(value);
				}else{
					right.insert(value);
				}
			}else{
				if(left == null){
					left = new Node(value);
				}else{
					left.insert(value);
				}
			}
		}

		public Node delete(int value) {
			if(this.value>value){
				if(left == null) return left;
				left = left.delete(value);
			}else if(this.value<value){
				if(right == null) return right;
				right = right.delete(value);
			}else{
				if(left == null){
					return right;
				}else if(right == null){
					return left;
				}else{
					Node minNode = getMin();
					delete(minNode.value);
					this.value = minNode.value;
				}
			}
			return this;
		}

		private Node getMin() {
			Node node = left;
			Node tmp = node;
			while(node!=null){
				tmp = node;
				node = node.right;
			}
			return tmp;
		}

		public void print() {
			if(left != null){
				left.print();
			}
			
			System.out.print(value + " ");
			
			if(right != null){
				right.print();
			}
				
		}
	}
	
	static class BST{
		Node root;
		
		public BST(int head) {
			root = new Node(head);
		}
		
		public void insert(int value){
			root.insert(value);
		}
		
		public void delete(int value){
			root.delete(value);
		}
		
		public void print(){
			root.print();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("sample_input.txt"));
    	//Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		for (int i = 0; i < TC; i++) {
			BST bst = new BST(in.nextInt());
			int n = in.nextInt();
			for (int j = 0; j < n; j++) {
				bst.insert(in.nextInt());
			}
			bst.delete(50);
			bst.print();
			
			System.out.println();
		}
		
	}
	
	
}

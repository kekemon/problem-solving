
public class BSTDemo {
	static class Node{
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
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
		public Node insert(int value){
			if(this.value>value){
				if(left != null){
					left = left.insert(value);
				}else{
					left = new Node(value);
				}
			}else if(this.value<value){
				if(right != null){
					right = right.insert(value);
				}else{
					right = new Node(value);
				}
			}

			return this;
		}
		
		public Node delete(int value){
			if(this.value>value){
				if(left != null){
					left = left.delete(value);
				}
			}else if(this.value<value){
				if(right != null){
					right = right.delete(value);
				}
			}else{
				if(left == null && right == null){
					return null;
				}else if(right == null){
					return left;
				}else if(left == null){
					return right;
				}else{
					Node nearest = getNearest();
					delete(nearest.getValue());
					this.value = nearest.getValue();
				}
			}
			return this;
		}
		
		public Node getNearest(){
			Node node = left;
			Node tmp = node;
			
			while(node != null){
				tmp = node;
				node = node.right;
			}
			
			return tmp;
		}
		
		public void printInorder(){
			if(left != null){
				left.printInorder();
			}
			System.out.print(value + " ");
			if(right != null){
				right.printInorder();
			}
		}
	}
	
	static class BST{
		Node root;
		public BST() {
			root = null;
		}
		
		public void insert(int value){
			if(root == null){
				root = new Node(value);
			}else{
				root = root.insert(value);
			}
		}
		
		public void delete(int value){
			if(root == null) return;
			root = root.delete(value);
		}
		
		public void print(){
			System.out.println();
			
			if(root != null)
				root.printInorder();
		}
	}
	
	public static void main(String[] args) {
		BST bst = new BST();
		
		bst.insert(50);
		bst.insert(30);
		bst.insert(20);
		bst.insert(40);
		bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        
		bst.print();
		
		bst.delete(20);
		bst.print();
		
		bst.delete(30);
		bst.print();
		
		bst.delete(50);
		bst.print();
		
		bst.delete(10);
		bst.print();
	}
}

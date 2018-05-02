
public class AVLTreeDemo {
	static class Node{
		private int value;
		private Node left;
		private Node right;
		private int height;
		
		public Node(int value) {
			setValue(value);
			setHeight(0);
			setLeft(null);
			setRight(null);
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

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public Node insert(int value) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	class AVLTree{
		private Node root;
		
		public AVLTree() {
			root = null;
		}
		
		public void insert(int value){
			if(root == null){
				root = new Node(value);
			}else{
				root = root.insert(value);
			}
		}
	}

}

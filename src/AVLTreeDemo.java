public class AVLTreeDemo {
	static class Node{
		private int value;
		private Node left;
		private Node right;
		private int height;
		
		public Node(int value) {
			setValue(value);
			setHeight(1);
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

		public Node getNearest(){
			Node node = left;
			Node tmp = node;
			
			while(node != null){
				tmp = node;
				node = node.right;
			}
			
			return tmp;
		}
		
		private int getBalance(){
			if(left != null && right != null) return left.getHeight() - right.getHeight();
			if(left != null) return left.getHeight();
			if(right != null) return -right.getHeight();
			return 0;
		}
		
		private void updateHeight() {
			int leftHeight = 0;
			int rightHeight = 0;
			
			if(left != null) leftHeight = left.getHeight();
			if(right != null) rightHeight = right.getHeight();
			
			setHeight(1 + (rightHeight > leftHeight ? rightHeight : leftHeight));
		}
		
		public Node insert(int value) {
			//insert like BST
			if(this.value < value){
				if(right != null){
					right = right.insert(value);
				}else{
					right = new Node(value);
				}
			}else if(this.value > value){
				if(left != null){
					left = left.insert(value);
				}else{
					left = new Node(value);
				}
			}else{
				return this;
			}
			
			//updating height while backtracking.
			updateHeight();
			
			//balancing while backtracking.
			return balanceInsert(value);
		}

		public Node delete(int value) {
			if(this.value < value){
				if(right != null){
					right = right.delete(value);
				}
			}else if(this.value > value){
				if(left != null){
					left = left.delete(value);
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
			
			updateHeight();
			
			return balanceDelete(value);
		}
		
		private Node balanceInsert(int value) {
			//balancing tree while backtracking.
			int balance = getBalance();
			
			//LL case
			if(balance > 1 && left.getValue() > value){
				return rotateRight();
			}
			
			//LR case
			if(balance > 1 && left.getValue() < value){
				left = left.rotateLeft();
				return rotateRight();
			}
			
			//RR case
			if(balance < -1 && right.getValue() < value){
				return rotateLeft();
			}
			
			//RL case
			if(balance < -1 && right.getValue() > value){
				right = right.rotateRight();
				return rotateLeft();
			}
			
			//if no balance needed
			return this;
		}

		private Node balanceDelete(int value2) {
			int balance = getBalance();
			
			if(balance > 1 && left.getBalance() >= 0){
				return rotateRight();
			}
			
			if(balance > 1 && left.getBalance() < 0){
				left = left.rotateLeft();
				return rotateRight();
			}
			
			if(balance < -1 && right.getBalance() <= 0){
				return rotateLeft();
			}
			
			if(balance < -1 && right.getBalance() > 0){
				right = right.rotateRight();
				return rotateLeft();
			}
			
			return this;
		}
		
		/*
	     y                               x
        / \     Right Rotation          /  \
       x   T3   - - - - - - --> 	   T1   y 
      / \       < - - - - - - -            / \
     T1  T2     Left Rotation            T2  T3
		*/
		
		private Node rotateLeft() {
			Node y = right;
			Node T2 = y.left;
			
			y.left = this;
			right = T2;
		
			this.updateHeight();
			y.updateHeight();
			
			return y;
		}
		
		private Node rotateRight() {
			Node x = left;
			Node T2 = x.right;
			
			x.right = this;
			left = T2;
			
			this.updateHeight();
			x.updateHeight();
			
			return x;
		}
		
		public void print(){
			if(left != null) left.print();
			System.out.print(value + " ");
			if(right != null) right.print();
		}
	}
	
	static class AVLTree{
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
		
		public void delete(int value){
			if(root != null){
				root = root.delete(value);
			}
		}
		
		public void print(){
			if(root != null){
				System.out.println();
				root.print();
			}
		}
	}
	
	public static void main(String[] args) {
		AVLTree avTree = new AVLTree();
		
		avTree.insert(10);
		avTree.insert(20);
		avTree.insert(30);
		avTree.insert(40);
		avTree.insert(50);
        avTree.insert(60);
        avTree.insert(70);
		
		avTree.print();
		
		avTree.delete(60);
		avTree.print();
	}

}

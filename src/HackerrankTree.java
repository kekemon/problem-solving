import java.util.Scanner;

public class HackerrankTree {
	static class Node {
	    int data;
	    Node left;
	    Node right;
	    
	    Node(int data) {
	        this.data = data;
	        left = null;
	        right = null;
	    }
	}

	void preOrder(Node root) {
		if(root != null){
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	void postOrder(Node root) {
	    if(root != null){
	        postOrder(root.left);
	        postOrder(root.right);
	        System.out.print(root.data + " ");
	    }
	}
	
	void inOrder(Node root) {
		if(root != null){
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
	    }
	}
	
	static int height(Node root) {
      	if(root != null){
      		int leftHeight = height(root.left);
      		int rightHeight = height(root.right);
      		int max = leftHeight > rightHeight? leftHeight : rightHeight;
      		return max+1;
      	}
      	return -1;
    }
	
	public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
	
	
	void leftView(Node root){
		if(root != null){
			leftView(root.left);
			System.out.print(root.data + " ");
		}
	}
	
	void rightView(Node root){
		if(root != null){
			System.out.print(root.data + " ");
			rightView(root.right);
		}
	}
	
	void topView(Node root) {
	      if(root != null){
	    	  leftView(root.left);
	    	  System.out.print(root.data + " ");
	    	  rightView(root.right);
	      }
    }
	
	static class Queue{
		Node[] queue;
		int front = 0;
		int rare = 0;
		
		public Queue() {
			queue = new Node[501];
		}
		
		public void push(Node node){
			queue[++rare] = node;
		}
		
		public Node pop(){
			return queue[++front];
		}
		
		public boolean isEmpty(){
			return front == rare;
		}
	}
	
	
	static void levelOrder(Node root) {
		Queue queue = new Queue();
		queue.push(root);
		
		while(!queue.isEmpty()){
			Node node = queue.pop();
			if(node != null){
				System.out.print(node.data + " ");
				if(node.left != null) queue.push(node.left);
				if(node.right != null)  queue.push(node.right);
			}
		}
    }
	
	static Node Insert(Node root,int value) {
		if(root == null) return new Node(value);
		else{
			if(root.data < value){
				return Insert(root.right, value);
			}else{
				return Insert(root.left, value);
			}
		}
	}
	
	static Node lca(Node root,int v1,int v2){
		Node parent1 = root;
		Node parent2 = root;
		Node last = root;
		
		while(parent1.data == parent2.data){
			last = parent1;
			if(parent1.data < v1){
				parent1 = parent1.right;
			}else if (parent1.data > v1){
				parent1 = parent1.left;
			}
			
			if(parent2.data < v2){
				parent2 = parent2.right;
			}else if (parent2.data > v2){
				parent2 = parent2.left;
			}
		}
		return last;
    }
	
	boolean isBST(Node node, int min, int max){
		if(node == null) return true;
		
		if(node.data < min || node.data > max) return false;
		
		return isBST(node.left, min, node.data-1) && isBST(node.right, node.data+1, max);
	}
	
	boolean checkBST(Node root) {
       return isBST(root, 0, 1000000);
    }
	
    public static void main(String[] args) {
    	Scanner scan = InputUtil.getScanner();
        //Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            int data = scan.nextInt();
            root = insert(root, data);
        }
        
        System.out.println(lca(root, scan.nextInt(), scan.nextInt()).data);
        scan.close();
//        int height = height(root);
//        System.out.println(height);
//        topView(root);
//        levelOrder(root);
    }	
}

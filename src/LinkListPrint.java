import java.util.Scanner;

public class LinkListPrint {
	

	
	static class Node {
		int data;
		Node next;
		public Node() {
			// TODO Auto-generated constructor stub
		}
		public Node(int data) {
			this.data = data;
			this.next = null;
			// TODO Auto-generated constructor stub
		}
		
		public Node append(int val){
			Node node = new Node(val);
			this.next = node;
			return node;
		}
	}
	
	static void print(Node head){
		Node root = head;
		while(root != null){
			System.out.println("aa" +root.data);
			root = root.next;
		}
	}
	static int count = 0;
	static int GetNode(Node head,int n) {
	    int val = 0;
		if(head != null){
			val = GetNode(head.next, n);
			if(count == n){
				val = head.data;
			}
			count++;
	    }
	    
	    return val;
	}
	
	boolean hasCycle(Node head) {
		Node fast = head;
		
		while(fast != null && fast.next != null){
			head = head.next;
			fast = fast.next.next;
			if(head.equals(fast)){
				return true;
			}
		}
		return false;

	}
	
	
	public static void main(String[] args) {
		
		Node head = new Node(2);
		head.append(1).append(3).append(5).append(6);
//		head = null;
		System.out.println(GetNode(head, 0));
	}
}

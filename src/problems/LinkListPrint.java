package problems;
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
	
	static boolean hasCycle(Node head) {
		Node fast = head;
		while(fast != null && fast.next != null){
			if(head.equals(fast.next)){
				return true;
			}
			head = head.next;
			fast = fast.next.next;
		}
		return false;

	}
	
	
	public static void main(String[] args) {
		
		Node head = new Node(2);
		head.next = head;
//		head.append(1);//.append(3).append(5).append(6);
//		head = null;
		System.out.println(hasCycle(head));
	}
}

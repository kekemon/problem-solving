import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NoPrefixSet {

	static int MAX_LEN = 10;
	
	static Node[] nodes;
	
	static class Node{
		private Node[] child;
		private boolean isEnd;
		
		public Node() {
			child = new Node[MAX_LEN];
			setEnd(false);
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}

		public Node[] getChild() {
			return child;
		}
	}
	
	
	private static int getIndex(char c){
		return c - 'a';
	}
	
	private static boolean isBadSet(char[] set){
		Node[] child = nodes;
		for (int i = 0; i < set.length; i++) {
			int index = getIndex(set[i]);
			if(child[index] == null){
				child[index] = new Node();
			}else{
				if(i == set.length-1 || child[index].isEnd()){
					return true;
				}
			}
			if(i == set.length-1){
				child[index].setEnd(true);
			}else{
				child = child[index].getChild();
			}
		}
		return false;
	}
	
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int n = in.nextInt();
    	nodes = new Node[MAX_LEN];
    	boolean isBadSet = false;
    	String badSet = null;
    	for (int i = 0; i < n; i++) {
			String set = in.next();
			if(!isBadSet){
				isBadSet = isBadSet(set.toCharArray());
				badSet = set;
			}
    	}
    	if(isBadSet){
    		System.out.println("BAD SET");
    		System.out.println(badSet);
    	}else{
    		System.out.println("GOOD SET");
    	}
    	in.close();
    }
}
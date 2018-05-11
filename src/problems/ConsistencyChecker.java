package problems;
import java.util.*;

public class ConsistencyChecker {

	static int MAX_CHILD = 10;
	
	static class Node{
		private Node[] child;
		private boolean isEnd;
		
		public Node() {
			child = new Node[MAX_CHILD];
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
		return c - '0';
	}
	
	private static boolean isBadSet(Node[] nodes, char[] set){
		
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
	
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
//    	Scanner in = new Scanner(new File("sample_input.txt"));
    	int tc = in.nextInt();
    	for (int i = 1; i <= tc; i++) {
    		Node[] nodes = new Node[MAX_CHILD];
        	boolean isBadSet = false;
        	int n = in.nextInt();
        	for (int j = 0; j < n; j++) {
    			String set = in.next();
    			if(!isBadSet){
    				isBadSet = isBadSet(nodes, set.toCharArray());
    			}
        	}
        	if(isBadSet){
        		System.out.println("Case " + i + ": NO");
        	}else{
        		System.out.println("Case " + i + ": YES");
        	}
        	System.gc();
		}
    	
    	in.close();
    }
}
package problems;
import java.util.Scanner;

public class ForestTrees {
	
	private static Node hashMap[];
	
	static class Node{
		
		private Node parent;
		private char value;
		private int count;
		private boolean isParent;
		
		public Node(char value) {
			this.setValue(value);
			this.setParent(this);
			this.setCount(1);
			this.setParent(true);
		}

		public Node getParent() {
			if(!this.equals(parent))
				return parent.getParent();
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public char getValue() {
			return value;
		}

		public void setValue(char value) {
			this.value = value;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public boolean isParent() {
			return isParent;
		}

		public void setParent(boolean isParent) {
			this.isParent = isParent;
		}
	}
	
	private static int getValueByKey(char key){
		return key - 'A';
	}
	
	private static Node getNode(char ch){
		int index = getValueByKey(ch);
		if(hashMap[index] == null){
			hashMap[index] = new Node(ch);
			return hashMap[index];
		}
		return hashMap[index];
	}
	
	public static void makeSet(char ch1, char ch2){
		Node paNode1 = getNode(ch1).getParent();
		Node paNode2 = getNode(ch2).getParent();
		
		if(paNode1.equals(paNode2)) return;
		
		if(paNode1.getCount() > paNode2.getCount()){
			paNode2.setParent(false);
			paNode2.setParent(paNode1);
			paNode1.setCount(paNode1.getCount() + paNode2.getCount());
		}else{
			paNode1.setParent(false);
			paNode1.setParent(paNode2);
			paNode2.setCount(paNode1.getCount() + paNode2.getCount());
		}
	}
	
	private static boolean isLetter(char c){
		if(c >='A' && c <='Z') return true;
		return false;
	}
	
	private static void parseInput(char[] line) {
		char[] pair = new char[2];
		for (int j = 0,k = 0; j < line.length && k<=1; j++) {
			if(isLetter(line[j])){
				pair[k++] = line[j];
			}
		}
		makeSet(pair[0], pair[1]);
		
		
	}
	
	public static void main(String[] args) {
//		Scanner in = InputUtil.getScanner();
		Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		for (int i = 0; i < TC; i++) {
			hashMap = new Node[26];
			
			while(in.hasNext()){
				char[] line = in.next().toCharArray();
				if(line[0]== '*') break;
				parseInput(line);
			}
			char[] data = in.next().toCharArray();
			int tree = 0;
			int acorn = 0;
			for (int j = 0; j < data.length; j++) {
				if(isLetter(data[j])){
					int index = getValueByKey(data[j]);
					if(hashMap[index] != null){
						if(hashMap[index].isParent()){
							tree++;
						}
					}else{
						acorn++;
					}
				}
			}
			System.out.println("There are " + tree + " tree(s) and " + acorn + " acorn(s).");
		}
		
	}

}

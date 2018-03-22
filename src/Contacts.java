import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Contacts {

	final static String ADD = "add";
	final static String FIND = "find";
	static Node[] nodes;
	
	static class Node{
		
		private Node child[];
		private int count;
		
		
		public Node() {
			child = new Node[26];
			count = 0;
		}
		
		public Node[] getChild(){
			return child;
		}

		public int getCount() {
			return count;
		}

		public void increaseCount(){
			count++;
		}
	}
	
	private static int getIndex(char c){
		return c-'a';
	}
	
	
	private static void addContacts(char[] name){
		Node[] child = nodes;
		for (char c : name) {
			int index = getIndex(c);
			if(child[index] == null){
				child[index] = new Node();
			}
			child[index].increaseCount();
			child = child[index].getChild();
		}
		
	}
	
	private static int findPartial(char[] partial){
		int count  = 0;
		Node[] child = nodes;
		for (char c : partial) {
			int index = getIndex(c);
			if(child[index] == null){
				return 0;
			}
			count = child[index].getCount();
			child = child[index].getChild();
		}
		
		return count;
	}
	
	private static boolean compare(char[] str1, char[] str2){
		int len1 = str1.length;
		int len2 = str1.length;
		if(len1 != len2){
			return false;
		}else{
			for (int i = 0; i < len1; i++) {
				if(str1[i] != str2[i]){
					return false;
				}
			}
			return true;
		}
	}
	
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int n = in.nextInt();
    	nodes = new Node[26];
    	for (int i = 0; i < n; i++) {
			String command = in.next();
			String name = in.next();;
			if(compare(command.toCharArray(), ADD.toCharArray())){
				addContacts(name.toCharArray());
			}else{
				System.out.println(findPartial(name.toCharArray()));
			}
		}
    	in.close();
    }
}

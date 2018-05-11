package problems;
import java.util.Scanner;


class Conformity {
	final static int MAX_CORSE = 5;
	final static int DIGIT = 10;
	
	
	static class Node{
		private Node[] child;
		private int frequency;
		
		public Node() {
			child = new Node[DIGIT];
			frequency = 0;
		}
		
		public Node[] getChild() {
			return child;
		}

		public void setChild(Node[] child) {
			this.child = child;
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		
	}
	
	public static void main(String[] args)  {
//    	Scanner in = InputUtil.getScanner();
    	Scanner in = new Scanner(System.in);
    	int n;
    	while((n=in.nextInt())!=0){
    		int hash[] = new int[5000000];
    		int total = 0;
    		int count = 0;
    		Node[] nodes = new Node[DIGIT];
    		for (int i = 0; i < n; i++) {
    			int arr[] = new int[MAX_CORSE];
				for (int j = 0; j < MAX_CORSE; j++) {
					arr[j] = in.nextInt();
					for (int k = j; k > 0; k--) {
						if(arr[k] < arr[k-1]){
							int tmp = arr[k-1];
							arr[k-1] = arr[k];
							arr[k] = tmp;
						}else{
							break;
						}
					}
					
				}
				
				int val = insert(nodes, arr);

				if(val>count){
					count = val;
					total = count;
				}else if(val==count){
					total += count;
				}

			}
    		System.out.println(total);
    		
    	}
    	in.close();

	}

	private static int insert(Node[] nodes, int[] arr) {
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			char[] str = (""+arr[i]).toCharArray();
			for (int j = 0; j < str.length; j++) {
				int index = str[j] - '0';
				if(nodes[index] == null){
					nodes[index] = new Node();
				}
				if(i == arr.length -1 && j == str.length -1){
					count = nodes[index].getFrequency() + 1;
					nodes[index].setFrequency(count);
				}
				nodes = nodes[index].getChild();
				
			}
		}
		return count;
	}

	private static void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

	private static int getKey(int[] arr) {
		int key = 0;
		for (int i = 0; i < arr.length; i++) {
			key = (key*1000 + (arr[i]-100));
		}
		return key;
	}
}

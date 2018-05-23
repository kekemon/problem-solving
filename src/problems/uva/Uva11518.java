package problems.uva;
import java.util.*;


class Uva11518 {
	
	private final static int MAX_DOMINO = 10005;

	
	static class Node{
		private ArrayList<Node> nextLinks;
		private boolean isVisited;
		
		public Node() {
			setVisited(false);
			nextLinks = new ArrayList<>();
		}

		public ArrayList<Node> getNextLinks() {
			return nextLinks;
		}

		public void addNextLinks(Node next) {
			nextLinks.add(next);
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}
	
		
		public int getFallCount(){
			if(!isVisited()){
				setVisited(true);
				int count = 0;
				ArrayList<Node> links = getNextLinks();
				for (Node node : links) {
					count += node.getFallCount();
				}
				return 1 + count;
			}
			return 0;
		}
	}
	
	
	

	
	public static void main(String[] args) {
//		Scanner in = InputUtil.getScanner();
		Scanner in = new Scanner(System.in);	
		int TC = in.nextInt();
		
		for (int i = 0; i < TC; i++) {
			int nDominos = in.nextInt();
			int links = in.nextInt();
			int actions = in.nextInt();
			
			Node[] dominos = new Node[MAX_DOMINO];
			
			for (int j = 0; j < links; j++) {
				int src = in.nextInt();
				int des = in.nextInt();
				
				
				if(dominos[src] == null) dominos[src] = new Node();
				if(dominos[des] == null) dominos[des] = new Node();
				
				dominos[src].addNextLinks(dominos[des]);
			}
			
			int count = 0;
			for (int j = 0; j < actions; j++) {
				int action = in.nextInt();
				if(dominos[action] == null) {
					dominos[action] = new Node();
					dominos[action].setVisited(true);
					count++;
				}else if(!dominos[action].isVisited()){
					count += dominos[action].getFallCount();
				}
				
			}
			System.out.println(count);
			
		}
		in.close();
	}

}

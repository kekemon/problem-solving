import java.util.Scanner;

public class MergingCommunities {
	
	static People[] peopleMap;
	
	static class People{
		int value;
		int rank;
		People parent;
		
		public People(int value) {
			this.value = value;
			this.rank = 1;
			this.parent = this;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public People getParent() {
			if(parent.value == value) return parent;
			return parent.getParent();
		}

		public void setParent(People parent) {
			this.parent = parent;
		}

	}
	
	public static void init(int peoples){
		peopleMap = new People[peoples];
		
		for (int i = 1; i < peoples; i++) {
			peopleMap[i] = new People(i);
		}
	}
	
	public static void makeSet(int key1, int key2){
		People parent1 = peopleMap[key1].getParent();
		People parent2 = peopleMap[key2].getParent();
		
		if(parent1 == parent2) return;
		
//		System.out.println(key1 + "   "+key2);
		if(parent1.getRank() >= parent2.getRank()){
			parent2.setParent(parent1);
			parent1.setRank(parent1.getRank() + parent2.getRank());
		}else{
			parent1.setParent(parent2);
			parent2.setRank(parent1.getRank() + parent2.getRank());
		}
	}
	
	public static void main(String[] args) {
//		Scanner in = InputUtil.getScanner();
		Scanner in = new Scanner(System.in);
		int peoples = in.nextInt();
		int queries = in.nextInt();
		
		init(peoples+1);
		
		for (int i = 0; i < queries; i++) {
			String query = in.next();
			if(query.equals("M")){
				makeSet(in.nextInt(), in.nextInt());
			}else if(query.equals("Q")){
				System.out.println(peopleMap[in.nextInt()].getParent().getRank());
			}
		}
		
		in.close();
	}
}

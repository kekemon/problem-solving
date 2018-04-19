import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class ComponentsInGraph {
	
	static int parentSize;
	static Component[] parentList;
	static Component[] componentMap;
	static int max = 0;
	static int min = 15001;
	
	static class Component{
		int value;
		int size;
		Component parent;
		
		public Component(int value) {
			setValue(value);
			setSize(1);
			setParent(this);
			parentList[parentSize++] = this;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int rank) {
			this.size = rank;
		}

		public Component getParent() {
			if(parent.value == value) return parent;
			return parent.getParent();
		}

		public void setParent(Component parent) {
			this.parent = parent;
		}

	}
	
	public static void init(int size){
		componentMap = new Component[(2*size)+1];
		parentSize = 0;
		parentList = new Component[(2*size)+1];
	}
	
	public static void makeSet(int key1, int key2){
		if(componentMap[key1] == null) componentMap[key1] = new Component(key1);
		if(componentMap[key2] == null) componentMap[key2] = new Component(key2);
		
		Component parent1 = componentMap[key1].getParent();
		Component parent2 = componentMap[key2].getParent();
		
		if(parent1 == parent2) return;
		
		if(parent1.getSize() >= parent2.getSize()){
			parent2.setParent(parent1);
			parent1.setSize(parent1.getSize() + parent2.getSize());
			removeParent(parent2);
		}else{
			parent1.setParent(parent2);
			parent2.setSize(parent1.getSize() + parent2.getSize());
			removeParent(parent1);
		}
	}
	
	
	
	private static void removeParent(Component parent) {
		for (int i = 0; i < parentSize; i++) {
			if(parentList[i] == parent){
				if(i != parentSize-1){
					parentList[i] = parentList[parentSize-1];
				}
				parentSize--;
				break;
			}
		}
	}
	
	private static void calculateMaxMin() {
		for (int i = 0; i < parentSize; i++) {
			if(parentList[i].getSize()>max){
				max = parentList[i].getSize();
			}
			
			if(parentList[i].getSize()<min){
				min = parentList[i].getSize();
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = InputUtil.getScanner();
		Scanner in = new Scanner(System.in);
		System.setOut(new PrintStream(new File(System.getenv("OUTPUT_PATH"))));
		int size = in.nextInt();
		
		init(size);
		
		for (int i = 0; i < size; i++) {
			makeSet(in.nextInt(), in.nextInt());
		}
		calculateMaxMin();
		System.out.println(min + " "+max);
		in.close();
	}

	
}

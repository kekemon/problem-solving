package problems.uva;

import java.util.Scanner;

public class Uva11060 {
	

	private static int nBeverage;
	private static int nBeverageOrder;
	private static Stack stack;
	
	static class Stack{
		private int top;
		private Beverage list[];
		
		public Stack() {
			list = new Beverage[nBeverage + 1];
			top = 0;
		}
		
		public void push(Beverage beverage){
			list[++top] = beverage;
		}
		
		public Beverage pop(){
			return list[top--];
		}
		
		public boolean isEmpty(){
			return top == 0;
		}
	}
	
	static class Beverage{
		private Beverage[] next;
		private int count;
		private char[] name;
		private boolean visited;
		
		public Beverage(char[] name) {
			this.name = name;
			next = new Beverage[nBeverage];
			count = 0;
		}
		
		public void addNext(Beverage beverage){
			next[count++] = beverage;
		}
		
		public Beverage[] getNext(){
			return next;
		}

		public char[] getName() {
			return name;
		}
		
		public int getNextCount() {
			return count;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		@Override
		public boolean equals(Object object){
			char[] cName = (char [])object;
			
			if(cName.length != name.length) return false;
			for (int i = 0; i < cName.length; i++) {
				if(cName[i] != name[i]) return false;
			}
			
			return true;
		}
	}
	
	static class BevarageList{
		private Beverage[] beverages;
		private int current;
		
		public BevarageList(int size) {
			current = 0;
			beverages = new Beverage[size];
		}
		
		public void add(char[] name){
			beverages[current++] = new Beverage(name);
		}
		
		public Beverage get(char[] name){
			for(int i=0; i<current; i++){
				if(beverages[i] != null && beverages[i].equals(name)){
					return beverages[i];
				}
			}
			return null;
		}
		
		public Beverage[] getList(){
			return beverages;
		}
	}
	
	private static void topologicalSort(Beverage beverage) {
		beverage.setVisited(true);
		
		Beverage[] next = beverage.getNext();
		for (int i = 0; i < beverage.count; i++) {
			if(!next[i].isVisited()){
				topologicalSort(next[i]);
			}
		}
		stack.push(beverage);
	}
	
	public static void main(String[] args) {
		Scanner in = utils.InputUtil.getScanner();
		//Scanner in = new Scanner(System.in);
		int tc = 1;
		while(in.hasNext()){
			nBeverage = in.nextInt();
			BevarageList beverageList = new BevarageList(nBeverage);
			
			stack = new Stack();
			for (int i = 0; i < nBeverage; i++) {
				beverageList.add(in.next().toCharArray());
			}
			
			nBeverageOrder = in.nextInt();
			for (int i = 0; i < nBeverageOrder; i++) {
				Beverage beverage1 = beverageList.get(in.next().toCharArray());
				Beverage beverage2 = beverageList.get(in.next().toCharArray());
				beverage1.addNext(beverage2);
			}
			
			Beverage[] beverages = beverageList.getList();
			
			for (int i = 0; i < beverages.length; i++) {
				System.out.println(beverages[i].name);
				if(!beverages[i].isVisited()){
					topologicalSort(beverages[i]);
				}
			}
			
			System.out.print("Case #" + tc++ + ": Dilbert should drink beverages in this order: ");
			while (!stack.isEmpty()) {
				System.out.print(stack.pop().getName());
				if(!stack.isEmpty()) System.out.print(" ");
				else System.out.println(".");
			}
		}
		
		in.close();
	}

	
}

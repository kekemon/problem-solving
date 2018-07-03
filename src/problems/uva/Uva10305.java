package problems.uva;

import java.util.Scanner;

public class Uva10305 {

	private static Task[] taskList;
	private static int precedence;
	private static Stack stack;
	
	static class Stack{
		int top;
		Task list[];
		
		public Stack() {
			list = new Task[taskList.length];
			top = 0;
		}
		
		public void push(Task task){
			list[++top] = task;
		}
		
		public Task pop(){
			return list[top--];
		}
		
		public boolean isEmpty(){
			return top == 0;
		}
	}
	
	static class Task{
		private Task[] successors;
		private int count;
		private int id;
		private boolean visited;
		
		public Task(int id) {
			this.id = id;
			successors = new Task[precedence];
			count = 0;
		}
		
		public void addSuccessor(Task task){
			successors[count++] = task;
		}
		
		public Task[] getSuccessor(){
			return successors;
		}
	}
	
	private static Task getTask(int index){
		if(taskList[index] == null){
			taskList[index] = new Task(index);
		}
		return taskList[index];
	}
	
	private static void topologicalSort(Task task){
		Task[] successors = task.getSuccessor();
		task.visited = true;
		for (int i = 0; i < task.count; i++) {
			if(!successors[i].visited)
				topologicalSort(successors[i]);
		}
		stack.push(task);
	}
	
	public static void main(String[] args) {
		Scanner in = utils.InputUtil.getScanner();
		//Scanner in = new Scanner(System.in);
		int m, n;
		
		while(true){
			n = in.nextInt();
			m = in.nextInt();
			if(n == 0 && m == 0) break;
			taskList = new Task[n + 1];
			stack = new Stack();
			precedence = m + 1;
			
			for (int i = 0; i < m; i++) {
				getTask(in.nextInt()).addSuccessor(getTask(in.nextInt()));
			}
			
			for (int i = 1; i <= n; i++) {
				Task task = getTask(i);
				if( !task.visited ){
					topologicalSort(task);
				}
			}
			
			boolean isStart = true;
			while (!stack.isEmpty()) {
				if(!isStart) System.out.print(" ");
				System.out.print(stack.pop().id);
				if(isStart) isStart = false;
			}
			System.out.println();
		}
		
		
		in.close();
	}

}

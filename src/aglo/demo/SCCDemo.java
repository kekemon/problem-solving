
public class SCCDemo {

	int MAX_NODE = 100000;
	
	class Stack{
		int[] stack;
		int index;
		
		Stack() {
			stack = new int[MAX_NODE + 1];
		}
		
		void push(int value){
			stack[++index] = value;
		}
		
		int pop(){
			return stack[index--];
		}
		
		boolean iEsmpty(){
			return index == 0;
		}
	}
	
	class Node{
		int value;
		Node[] child;
		int childCount;
		boolean isVisited;
		
		Node(int value){
			this.value = value;
		}
		
		void addhild(Node node){
			if(child == null ) child = new Node[MAX_NODE];
			child[childCount++] = node;
		}
	}
	
	class Graph{
		int vertex;
		Node[] nodes;
		
		Graph(int vertex) {
			this.vertex = vertex;
			nodes = new Node[vertex];
		}
		
		Node get(int value){
			if(nodes[value] == null) nodes[value] = new Node(value);
			return nodes[value];
		}
		
		void addEdge(int src, int des){
			get(src).addhild(get(des));
		}
		
		Graph getTranpose(){
			Graph graph = new Graph(vertex);
			
			for (int i = 0; i < vertex; i++) {
				Node node = nodes[i];
				for (int j = 0; j < node.childCount; j++) {
					graph.addEdge(node.child[j].value, node.value);
				}
			}
			
			return graph;
		}
		
		void makeOrder(int index, Stack stackOut){
			Node node = nodes[index];
			node.isVisited = true;
			
			for (int i = 0; i < node.childCount; i++) {
				if(!node.child[i].isVisited)
					makeOrder(node.value, stackOut);
			}
			
			stackOut.push(index);
		}
		
		Stack getOrder(){
			Stack stackOut = new Stack();
			for (int i = 0; i < vertex; i++) {
				if(!nodes[i].isVisited){
					makeOrder(nodes[i].value, stackOut);
				}
			}
			return stackOut;
		}
		
		void traverseFrom(int index){
			
			Node node = nodes[index];
			node.isVisited = true;
			
			for (int i = 0; i < node.childCount; i++) {
				if(!node.child[i].isVisited)
					traverseFrom(node.child[i].value);
			}
		}
		
		int tarverseinOrder(Stack stack){
			int count = 0;
			
			while (!stack.iEsmpty()) {
				int top = stack.pop();
				if(!nodes[top].isVisited){
					traverseFrom(top);
					count++;
				}
			}
			
			return count;
		}
		
		int getSCCCount(){
			Stack stack = getOrder();
			Graph graph = getTranpose();
			
			return graph.tarverseinOrder(stack);
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}

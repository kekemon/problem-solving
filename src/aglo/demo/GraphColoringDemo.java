package aglo.demo;

import java.util.Scanner;

import utils.InputUtil;


public class GraphColoringDemo {
	
	public static void colorGraph(int nColors, int graph[][]){
		int nodColors[] = new int[graph.length];
		if(isGraphCanColor(graph, nodColors, nColors, 0)){
			printColors(nodColors);
		}else{
			System.out.println("Solution does not exist");
		}
	}
	
	private static void printColors(int[] colors) {
		for (int color : colors) {
			System.out.print(color + " ");
		}
		System.out.println();
	}

	private static boolean isGraphCanColor(int[][] graph, int[] nodColors, int nColors, int currentNode) {
		if(currentNode == graph.length) return true;
			
		for(int color = 1; color<= nColors; color++){
			if(isSafe(graph, nodColors, currentNode, color)){
				nodColors[currentNode] = color;
				if(isGraphCanColor(graph, nodColors, nColors, currentNode+1)){
					return true;
				}
				nodColors[currentNode] = 0;
			}
		}
		return false;
	}



	private static boolean isSafe(int[][] graph, int[] nodColors, int currentNode, int color) {
		for (int des = 0; des < graph.length; des++) {
			if(graph[currentNode][des] == 1 && nodColors[des] == color){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		
		int nNodes = in.nextInt();
		int nColors = in.nextInt();
		
		int graph[][] = new int[nNodes][nNodes];
		
		for (int i = 0; i < nNodes; i++) {
			for (int j = 0; j < nNodes; j++) {
				graph[i][j] = in.nextInt();
			}
		}
		
		colorGraph(nColors, graph);
		
		in.close();
	}
}

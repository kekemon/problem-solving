package problems.uva;

import java.io.IOException;
import java.util.Scanner;

import utils.InputUtil;

public class Uva10116 {
	static class Node{
		private int steps;
		private char instruction;
		
		public Node(char instruction) {
			this.setInstruction(instruction);
			this.setSteps(0);
		}

		public int getSteps() {
			return steps;
		}

		public void setSteps(int steps) {
			this.steps = steps;
		}

		public char getInstruction() {
			return instruction;
		}

		public void setInstruction(char instruction) {
			this.instruction = instruction;
		}
	}
	
	private static void traverse(Node grid[][], int startR, int startC, int steps){
		if(startR <0 || startC < 0 || startR>= grid.length || startC >= grid[0].length){
			System.out.println(steps + " step(s) to exit");
			return;
		}else if(grid[startR][startC].getSteps() != 0){
			int st = grid[startR][startC].getSteps() - 1;
			System.out.println(st + " step(s) before a loop of " + (steps - st) + " step(s)");
			return;
		}else{
			char instruction = grid[startR][startC].getInstruction();
			grid[startR][startC].setSteps(++steps);
			
			switch (instruction) {
			case 'N':
				traverse(grid, startR - 1, startC, steps);
				break;
			case 'S':
				traverse(grid, startR + 1, startC, steps);
				break;
			case 'E':
				traverse(grid, startR, startC + 1, steps);
				break;
			case 'W':
				traverse(grid, startR, startC - 1, steps);
				break;
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int R, C, start;
		
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		
		while((R = in.nextInt()) != 0 && (C = in.nextInt()) != 0 && (start = in.nextInt()) != 0){
			Node grid[][] = new Node[R][C];
			
			for (int i = 0; i < R; i++) {
				char[] line = in.next().toCharArray();
				for (int j = 0; j < C; j++) {
					grid[i][j] = new Node(line[j]);
				}
			}
			traverse(grid, 0, start - 1, 0);
		}
		
	}
}

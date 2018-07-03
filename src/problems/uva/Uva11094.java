package problems.uva;

import java.util.Scanner;

public class Uva11094 {
	private static int row;
	private static int col;
	private static char[][] map;
	private static int resX;
	private static int resY;
	private static boolean [][]visited;
	private static int dx[] = new int[]{0, 1 , -1, 0};
	private static int dy[] = new int[]{1, 0 , 0, -1};
	
	public static void main(String[] args) {
		Scanner in = utils.InputUtil.getScanner();
		//Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			row = in.nextInt();
			col = in.nextInt();
			map = new char[row][col];
			visited = new boolean[row][col];
			in.nextLine();
			
			for (int i = 0; i < row; i++) {
				map[i] = in.nextLine().toCharArray();
			}
			
			resX = in.nextInt();
			resY = in.nextInt();
			capture(resX, resY);
			
			int maxCapture = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(isSafe(i, j)){
						int capture = capture(i, j);
						if(capture > maxCapture)
							maxCapture = capture;
					}
				}
			}
			System.out.println(maxCapture);
		}
		
		in.close();
	}
	
	private static int capture(int x, int y) {
		int capture = 1;
		if(!visited[x][y] && map[x][y] == map[resX][resY]){
			visited[x][y] = true;
			for (int k = 0; k < dx.length; k++) {
				int newX = x + dx[k];
				int newY = y + dy[k];
				if(isSafe(newX, newY))
					capture += capture(newX, newY);
			}
			
			if(y == 0 && isSafe(x, col - 1)){
				capture += capture(x, col - 1);
			}else if(y == col - 1 && isSafe(x, 0)){
				capture += capture(x, 0);
			}
		}
		return capture;
	}

	private static boolean isSafe(int i, int j) {
		if(i>=0 && j>=0 && i < row && j < col && map[i][j] == map[resX][resY] && !visited[i][j]) return true;
		return false;
	}
}

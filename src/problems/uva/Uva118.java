package problems.uva;

import java.util.HashMap;
import java.util.Scanner;
import utils.InputUtil;

public class Uva118 {
	private static int R, C;
	private static char[] instruction;
	private static HashMap<String, String> directionMap; 
	private static boolean safe[][];
	
	private static void traverse(int insIndex, int curX, int curY, int lastX, int lastY, String direction){
		if(curX<0 || curY < 0 || curX>R || curY > C){
			if(!safe[lastX][lastY]){
				System.out.println(lastX + " " + lastY + " " + direction + " LOST");
				safe[lastX][lastY] = true;
			}else{
				traverse(insIndex, lastX, lastY, lastX, lastY, direction);
			}
			
		}else if(insIndex>=instruction.length){
			System.out.println(curX + " " + curY + " " + direction);
		}else{
			if(instruction[insIndex] == 'F'){
				int newX = curX, newY =curY;
				switch (direction) {
				case "N":
					newY++; 
					break;
				case "S":
					newY--;
					break;
				case "E":
					newX++;
					break;
				case "W":
					newX--;
					break;
				default:
					break;
				}
				traverse(insIndex + 1, newX, newY, curX, curY, direction);
			}else{
				traverse(insIndex + 1, curX, curY, lastX, lastY, directionMap.get(direction + instruction[insIndex]));
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int startX, startY;
		String startDir;
		
		R = in.nextInt();
		C = in.nextInt();
		safe = new boolean[R+1][C+1];
		directionMap = new HashMap<String, String>();
		directionMap.put("ER", "S");
		directionMap.put("EL", "N");
		directionMap.put("WR", "N");
		directionMap.put("WL", "S");
		directionMap.put("NR", "E");
		directionMap.put("NL", "W");
		directionMap.put("SR", "W");
		directionMap.put("SL", "E");
		
		while(in.hasNext()){
			startX = in.nextInt();
			startY = in.nextInt();
			startDir = in.next();
			instruction = in.next().toCharArray();
			traverse(0, startX, startY, startX, startY, startDir);
		}
		
	}
}

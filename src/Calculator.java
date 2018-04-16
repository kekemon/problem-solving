import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Calculator {
	
	public static double evalExp(String exp){
		return addSub(exp);
	}
	
	private static boolean isDigit(char c){
		if(c<='9' && c>='0'){
			return true;
		}
		return false;
	}
	
	private static double addSub(String exp) {
		//System.out.println("addSub");
		int bracketCount = 0;
		String operand1 = "";
		String operand2 = "0";
		int len = exp.length();
		boolean isExpression = false;
		for (int i = 0; i < len; i++) {
			char ch = exp.charAt(i);
			if(!isExpression && !isDigit(ch)){
				isExpression = true;
			}
			if(ch == '('){
				bracketCount++;
				operand1 += ch;
			}else if(ch == ')'){
				bracketCount--;
				operand1 += ch;
			}else if (ch == '+'){
				if(bracketCount == 0){
					operand1 = exp.substring(0, i);
					operand2 = exp.substring(i+1, len);
					//System.out.println("IN add operand1 "+operand1 + "  operand2 " +operand2);
					double ret1 = divMul(operand1);
					double ret2 = divMul(operand2);
					//System.out.println("ret1 "+ret1 + "  ret2 "+ret2);
					return ret1 + ret2;
				}else{
					operand1 += ch;
				}
			}else if (ch == '-'){
				if(bracketCount == 0){
					operand1 = exp.substring(0, i);
					operand2 = exp.substring(i+1, len);
					//System.out.println("IN Sub operand1 "+operand1 + "  operand2 " +operand2);
					double ret1 = divMul(operand1);
					double ret2 = divMul(operand2);
					//System.out.println("ret1 "+ret1 + "  ret2 "+ret2);
					return ret1 - ret2;
				}else{
					operand1 += ch;
				}
			}else{
				operand1 += ch;
			}
		}
		if(!isExpression) return Integer.parseInt(operand1);
		return divMul(operand1);
	}

	private static double divMul(String exp) {
		//System.out.println("divMul");
		int bracketCount = 0;
		String operand1 = "";
		String operand2 = "0";
		int len = exp.length();
		for (int i = 0; i < len; i++) {
			char ch = exp.charAt(i);
			if(ch == '('){
				bracketCount++;
				operand1 += ch;
			}else if(ch == ')'){
				bracketCount--;
				operand1 += ch;
			}else if (ch == '*'){
				if(bracketCount == 0){
					operand1 = exp.substring(0, i);
					operand2 = exp.substring(i+1, len);
					//System.out.println("IN mul operand1 "+operand1 + "  operand2 " +operand2);
					double ret1 = bracket(operand1);
					double ret2 = bracket(operand2);
					//System.out.println("ret1 "+ret1 + "  ret2 "+ret2);
					return ret1 * ret2;
				}else{
					operand1 += ch;
				}
			}else if (ch == '/'){
				if(bracketCount == 0){
					operand1 = exp.substring(0, i);
					operand2 = exp.substring(i+1, len);
					//System.out.println("IN div operand1 "+operand1 + "  operand2 " +operand2);
					double ret1 = bracket(operand1);
					double ret2 = bracket(operand2);
					//System.out.println("ret1 "+ret1 + "  ret2 "+ret2);
					return ret1 / ret2;
				}else{
					operand1 += ch;
				}
			}else{
				operand1 += ch;
			}
		}
		return bracket(operand1);
	}
	
	public static double bracket(String exp){
		//System.out.println("bracket "+exp);
		if(exp.charAt(0) != '(') return evalExp(exp); 
		int bracketCount = 0;
		int len = exp.length();
		String operand = "";
		for (int i = 0; i < len; i++) {
			char ch = exp.charAt(i);
			if(ch == '('){
				if(bracketCount != 0){
					operand += ch;
				}
				bracketCount++;
			}else if(ch == ')'){
				bracketCount--;
				if(bracketCount == 0){
					break;
				}else{
					operand += ch;
				}
			}else{
				operand += ch;
			}
		}
		return evalExp(operand);
	}

	public static void main(String[] args) {
    	Scanner in = InputUtil.getScanner();
//    	Scanner in = new Scanner(System.in);
    	
    	int TC = in.nextInt();
    	for (int i = 0; i < TC; i++) {
    		System.out.println(evalExp(in.next()));
		}
    	
    	
    	in.close();
	}
}

package problems.other;
public class TextEditor {
	private final int TEXT_LENGTH = 20;
	private int start;
	private int end;
	private char[] text;
	
	public TextEditor() {
		text = new char[TEXT_LENGTH];
		start = 0;
		end = TEXT_LENGTH - 1;
	}
	
	
	public char[] read(int pos, int len){
		char[] data = new char[len];
		for(int i=0;i<len;i++){
			int k =i+pos;
			if(k<start){
				data[i] = text[k];
			}else{
				int p = k-start+end+1;
				if(p>=TEXT_LENGTH)
					break;
				data[i] = text[p];
			}
		}
		return data;
	}
	
	public void moveCursor(int pos){
		if(pos<start){
			while(pos!=start){
				start--;
				text[end] = text[start];
				end--;
			}
		}else{
			while(pos!=start){
				text[start] = text[end];
				start++;
				end++;
			}
		}
	}
	
	public void insert(int pos, char[] data, int len){
		moveCursor(pos);
		for(int i=0;i<len;i++){
			text[pos+i] = data[i];
			start++;
		}
	}
	
	public void delete(int pos, int len){
		moveCursor(pos);
		int k = (pos+len - start);
		end += k;
		System.out.println(k+ " "+ start + "  "+ end);
	}

}

import java.util.Scanner;

public class VirtualFriends {
	
	static class Data{
		Data parent;
		int rank;
		String value;
		
		public Data(String value) {
			this.value = value;
			this.rank = 1;
			this.parent = this;
		}
		
		public Data getParent() {
			if(equals(parent)){
				return parent;
			}
			return parent.getParent();
		}

		public void setParent(Data parent) {
			this.parent = parent;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object object){
			if(object instanceof String){
				String _value = (String)object;
				return isEqual(_value);
			}else if(object instanceof Data){
				Data data = (Data)object;
				return isEqual(data.getValue());
			}
			return false;
		}

		private boolean isEqual(String _value) {
			if(value.length() != _value.length()) return false;
			else{
				for (int i = 0; i < _value.length(); i++) {
					if(value.charAt(i) != _value.charAt(i)) return false;
				}
				return true;
			}
		}
		
	}
	
	static int MAX_DATA;
	static Data map[];
	
	private static Data getValueByKey(String str){
		int hashKey = 5381;
		char[] strch = str.toCharArray();
		for (char c : strch) {
			hashKey = ((hashKey<<5) + hashKey+ c) % MAX_DATA;
		}
		
		if(map[hashKey] == null) return map[hashKey] = new Data(str);
		else{
			if(map[hashKey].equals(str)) return map[hashKey];
			else{
				while(true){
					hashKey = (hashKey + 1) % MAX_DATA;
					if(map[hashKey] == null) return map[hashKey] = new Data(str);
					else if(map[hashKey].equals(str)) return map[hashKey];
				}
			}
		}
	}
	
	public static void init(int size){
		MAX_DATA = size * 2;
		map = new Data[MAX_DATA];
	}
	
	
	public static void makeSet(String key1, String key2){
		Data data1 = getValueByKey(key1);
		Data data2 = getValueByKey(key2);
		
		Data parent1 = data1.getParent();
		Data parent2 = data2.getParent();
		
		if( parent1 == parent2){
			return;
		}
		
		if(parent1.rank >= parent2.rank){
			parent1.rank = parent1.rank + parent2.rank; 
			parent2.parent = parent1;
		}else{
			parent2.rank = parent1.rank + parent2.rank; 
			parent1.parent = parent2;
		}
	}
	
	
	public static void main(String[] args) {
//		Scanner in = InputUtil.getScanner();
		Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		
		for (int i = 0; i < TC; i++) {
			int n = in.nextInt();
			init(n);
			for (int j = 0; j < n; j++) {
				String key1 = in.next(); 
				String key2 = in.next();
				makeSet(key1, key2);
				System.out.println(getValueByKey(key1).getParent().getRank());
			}
		}
		
		in.close();
	}
}

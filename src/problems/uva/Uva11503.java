package problems.uva;
import java.util.Scanner;

import utils.InputUtil;

public class Uva11503 {
	
	static class Person{
		Person leader;
		int teamSize;
		String name;
		
		public Person(String name) {
			this.name = name;
			this.teamSize = 1;
			this.leader = this;
		}
		
		public Person getLeader() {
			if(equals(leader)){
				return leader;
			}
			return leader.getLeader();
		}

		public void setLeader(Person leader) {
			this.leader = leader;
		}

		public int getTeamSize() {
			return teamSize;
		}

		public void setTeamSize(int teamSize) {
			this.teamSize = teamSize;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object object){
			if(object instanceof String){
				String _value = (String)object;
				return isEqual(_value);
			}else if(object instanceof Person){
				Person data = (Person)object;
				return isEqual(data.getName());
			}
			return false;
		}

		private boolean isEqual(String _value) {
			if(name.length() != _value.length()) return false;
			else{
				for (int i = 0; i < _value.length(); i++) {
					if(name.charAt(i) != _value.charAt(i)) return false;
				}
				return true;
			}
		}
		
	}
	
	static int MAX_PERSON;
	static Person friendMap[];
	
	private static Person getValueByKey(String key){
		int hashKey = 5381;
		char[] strch = key.toCharArray();
		for (char c : strch) {
			hashKey = ((hashKey<<5) + hashKey+ c) % MAX_PERSON;
		}
		
		if(friendMap[hashKey] == null) return friendMap[hashKey] = new Person(key);
		else{
			if(friendMap[hashKey].equals(key)) return friendMap[hashKey];
			else{
				while(true){
					hashKey = (hashKey + 1) % MAX_PERSON;
					if(friendMap[hashKey] == null) return friendMap[hashKey] = new Person(key);
					else if(friendMap[hashKey].equals(key)) return friendMap[hashKey];
				}
			}
		}
	}
	
	public static void init(int size){
		MAX_PERSON = size * 2;
		friendMap = new Person[MAX_PERSON];
	}
	
	
	public static void makeSet(String key1, String key2){
		Person data1 = getValueByKey(key1);
		Person data2 = getValueByKey(key2);
		
		Person parent1 = data1.getLeader();
		Person parent2 = data2.getLeader();
		
		if( parent1 == parent2){
			return;
		}
		
		if(parent1.getTeamSize() >= parent2.getTeamSize()){
			parent1.setTeamSize(parent1.getTeamSize() + parent2.getTeamSize());
			parent2.setLeader(parent1);
		}else{
			parent2.setTeamSize(parent1.getTeamSize() + parent2.getTeamSize());
			parent1.setLeader(parent2);
		}
	}
	
	
	public static void main(String[] args) {
		Scanner in = InputUtil.getScanner();
//		Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		
		for (int i = 0; i < TC; i++) {
			int n = in.nextInt();
			init(n);
			for (int j = 0; j < n; j++) {
				String key1 = in.next(); 
				String key2 = in.next();
				makeSet(key1, key2);
				System.out.println(getValueByKey(key1).getLeader().getTeamSize());
			}
		}
		
		in.close();
	}
}


public class LinkListDemo {
	static class Node{
		Node next;
		int item;
		Node(int item){
			this(item, null);
		}
		
		Node(int item, Node next){
			this.item =item;
			this.next = next;
		}
		
	}
	
	static class LinkList{
		Node head;
		Node last;
		
		final int MAX_LIMIT = 10;
		
		void insert(int position, int item){
			Node node = head;
			Node previous = null;
			boolean isInserted = false;
			int count = 1;
			while (node != null) {
				if(count == position){
					Node newNode = new Node(item);
					if(node == head){
						head = newNode;
					}else{
						previous.next = newNode;
					}
					newNode.next = node;
					isInserted = true;
					break;
				}
				count++;
				previous = node;
				node = node.next;
			}
			
			if(!isInserted && count == position){
				append(item);
			}
		}
		
		private void delete(Node previous, Node node){
			if(node == head){
				head = node.next;
			}else{
				previous.next = node.next;
				if(node == last){
					last = previous;
				}
			}
		}
		
		void deleteByItem(int item){
			Node node = head;
			Node previous = null;
			while (node != null) {
				if(node.item == item){
					delete(previous, node);
					return;
				}
				previous = node;
				node = node.next;
			}
		}
		
		void deleteByPos(int position){
			Node node = head;
			Node previous = null;
			int count = 1;
			while (node != null) {
				if(count == position){
					delete(previous, node);
					return;
				}
				previous = node;
				node = node.next;
				count++;
			}
		}
		
		void append(int item){
			Node node = new Node(item);;
			if(head == null){
				head = node;
			}else{
				last.next = node;
			}
			last = node;
		}
		
		void update(int oldValue, int newValue){
			Node node = head;
			while (node != null) {
				if(node.item == oldValue){
					node.item = newValue;
					return;
				}
				node = node.next;
			}
		}
		
		boolean contains(int item){
			Node node = head;
			while (node != null) {
				if(node.item == item){
					return true;
				}
				node = node.next;
			}
			return false;
		}
		
		void removeDuplicates(){
			LinkList[] linkLists = new LinkList[MAX_LIMIT];
			Node node = head;
			Node previous = null;
			while (node != null) {
				int item = node.item;
				int mod = item % MAX_LIMIT;
				if(linkLists[mod] == null){
					linkLists[mod] = new LinkList();
					linkLists[mod].append(item);
				}else{
					if(linkLists[mod].contains(item)){
						previous.next = node.next;
					}else{
						linkLists[mod].append(item);
					}
				}
				previous = node;
				node = node.next;
			}
		}
		
		void print(){
			Node node = head;
			while (node != null) {
				System.out.print(node.item + " ");
				node = node.next;
			}
		}
	}
	
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		
		linkList.append(11);
		linkList.append(21);
		linkList.append(20);
		linkList.removeDuplicates();
		linkList.print();
		
	}
}

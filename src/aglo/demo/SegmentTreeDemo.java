package aglo.demo;

public class SegmentTreeDemo {
	static class SegmentTree{
		int segmentTree[];
		int data[];
		
		public SegmentTree(int data[]) {
			this.data = data;
			double level = Math.ceil(Math.log(data.length)/Math.log(2));
			int len = 2*(int)Math.pow(2, level) -1;
			segmentTree = new int[len];
			costrutct(0, 0, data.length-1);
			
			
		}
		
		public void print(){
			for (int seg : segmentTree) {
				System.out.print(seg + " ");
			}
			System.out.println();
		}

		private void costrutct(int stIndex, int start, int end) {
			if(start == end){
				segmentTree[stIndex] = data[start];
				return;
			}
			
			int mid = (start + end)/2;
			int left = 2*stIndex + 1 ;
			int right = 2*stIndex + 2;
			costrutct(left, start, mid);
			costrutct(right, mid + 1, end);
			segmentTree[stIndex] = segmentTree[left] + segmentTree[right];
		}
		
		private void update(int stIndex, int start, int end, int index, int value){
			if(index<start || index>end) return;
			
			if(index>=start && index<=end) {
				segmentTree[stIndex] = value;
				return;
			}
			
			int mid = (start + end)/2;
			int left = 2*stIndex + 1;
			int right = 2*stIndex + 2;
			update(left, start, mid, index, value);
			update(right, mid + 1, end, index, value);
			segmentTree[stIndex] = segmentTree[left] + segmentTree[right];
		}
		
		public void update(int index, int value){
			update(0, 0, data.length - 1, index, value);
		}
		
		public int getSum(int start, int end){
			return calculateSum(0, 0, data.length - 1, start, end);
		}

		private int calculateSum(int stIndex, int start, int end, int querryStart, int querryEnd) {
			if(querryStart> end || querryEnd < start){
				return 0;
			}
			
			if(querryStart<= start &&  end <= querryEnd){
				return segmentTree[stIndex];
			}
			
			int mid = (start + end)/2;
			int left = 2*stIndex + 1;
			int right = 2*stIndex + 2;
			
			int leftSum = calculateSum(left, start, mid, querryStart, querryEnd);
			int rightSum = calculateSum(right, mid + 1, end, querryStart, querryEnd);
			return leftSum + rightSum;
		}
	}
	
	public static void main(String[] args) {
		
		int arr[] = new int[]{-1, 2, 4, 0}; 
		SegmentTree segmentTree = new SegmentTree(arr);
		segmentTree.print();
		System.out.println(segmentTree.getSum(3, 3));
	}
}

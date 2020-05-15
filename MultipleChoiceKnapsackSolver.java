package ads.set2.knapsack;

public class MultipleChoiceKnapsackSolver {

	private static void swap(int[] array1, int[] array2) {
		for (int index = 0; index < array1.length; index++) {
			int temp = array1[index];
			array1[index] = array2[index];
			array2[index] = temp;
		}
	}
	
	private static int max(int[] array) {
		int max = 0;
	    for (int value : array) {
			if (max < value) {
				max = value;
			}
		}
	    return max;
	}
	
	private static void initialize(int[] array, int by) {
		for (int i = 0; i < array.length; i++) {
			array[i] = by;
		}
	}
	
	public static int pack(ItemGroup[] itemGroupArray, int capacity) {
		if (itemGroupArray.length == 0)
	        return 0;

		int[] last = new int[capacity + 1];
		
		for (int i = 0; i < itemGroupArray[0].getItems().size(); ++i) {
	        int mWeight = itemGroupArray[0].getItems().get(i).getWeight();
	        int mProfit = itemGroupArray[0].getItems().get(i).getProfit();
			if (mWeight <= capacity)
	            last[mWeight] = Math.max(last[mWeight], mProfit);
	    }

		int[] current = new int[capacity + 1];
	    for (int i = 1; i < itemGroupArray.length; ++i) {
	    	initialize(current, 0);
	        
	        for (int j = 0; j < itemGroupArray[i].getItems().size(); ++j) {
	        	int mWeight = itemGroupArray[i].getItems().get(j).getWeight();
	        	int mProfit = itemGroupArray[i].getItems().get(j).getProfit();
	        	
	        	for (int k = mWeight; k <= capacity; ++k) {
	        		current[k] = Math.max(current[k],
                            last[k - mWeight] + mProfit);
	            }
	        }
	        swap(current, last);
	    } 
	    
	    return max(last);
	}
}

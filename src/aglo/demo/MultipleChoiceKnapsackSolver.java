package ads.set2.knapsack;

import java.util.HashMap;

public class MultipleChoiceKnapsackSolver {

	private static int max(HashMap<Integer, Integer> hashMap) {
		int maximum = 0;
	    for (int value : hashMap.values()) {
			if (maximum < value) {
				maximum = value;
			}
		}
	    return maximum;
	}
	
	private static int getValue(HashMap<Integer, Integer> hashMap, int index) {
		if (hashMap.get(index) == null) {
			return 0;
		}
		return hashMap.get(index);
	}
	
	public static int pack(ItemGroup[] itemGroupArray, int capacity) {
		if (itemGroupArray.length == 0)
	        return 0;

		HashMap<Integer, Integer> last = new HashMap<>();
		
		for (int i = 0; i < itemGroupArray[0].getItems().size(); ++i) {
	        int mWeight = itemGroupArray[0].getItems().get(i).getWeight();
	        int mProfit = itemGroupArray[0].getItems().get(i).getProfit();
			if (mWeight <= capacity)
				last.put(mWeight, Math.max(getValue(last, mWeight), mProfit));
	    }

		HashMap<Integer, Integer> current = new HashMap<>();
	    for (int i = 1; i < itemGroupArray.length; ++i) {
	    	current.clear();
	        
	        for (int j = 0; j < itemGroupArray[i].getItems().size(); ++j) {
	        	int mWeight = itemGroupArray[i].getItems().get(j).getWeight();
	        	int mProfit = itemGroupArray[i].getItems().get(j).getProfit();
	        	
	        	for (int k = mWeight; k <= capacity; ++k) {
	        		current.put(k, Math.max(getValue(current, k),
	        				getValue(last, k - mWeight) + mProfit));
	        		
	            }
	        }
	        HashMap<Integer, Integer> temp = current;
	        current = last;
	        last = temp;
	    } 
	    
	    return max(last);
	}
}

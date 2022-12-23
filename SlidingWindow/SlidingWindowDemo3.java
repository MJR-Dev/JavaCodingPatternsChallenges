import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SlidingWindowDemo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ints = {1,2};
		List<Integer> nums = Arrays.stream(ints).boxed().collect(Collectors.toList());
		ArrayDeque<Integer> finalResult = findMaxSlidingWindow(nums,2);
		Iterator<Integer> iter3 = finalResult.iterator();
		while(iter3.hasNext()) {
			System.out.println("##$$%%% "+iter3.next());
		}
		
	}

	public static ArrayDeque<Integer> findMaxSlidingWindow(List<Integer> nums, int windowSize) {
		ArrayDeque<Integer> result = new ArrayDeque<>();
		ArrayDeque<Integer> tempAr = new ArrayDeque<>();
		for(int i=0;i<=nums.size()-windowSize;i++) {
			if(i==0) {
				for(int j=i;j<i+windowSize;j++) {
					tempAr.add(nums.get(j));
				}
				result.add(tempAr.stream().max(Integer::compareTo).get());
			}
			else {
				tempAr.removeFirst();
				tempAr.addLast(nums.get(i+windowSize-1));
				
				int maxEle = tempAr.stream().max(Integer::compareTo).get();
				
				if(result.getFirst() < maxEle) {
					result.addLast(maxEle);
					result.removeFirst();					
				}				
			}
		}		
		return result;
	}
	
}

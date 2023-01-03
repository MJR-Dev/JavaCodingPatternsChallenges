import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given a string s that represents a DNA sequence, and a number k,return all the contiguous 
 * sequences (substrings) of length k that occur more than once in the string. The order of 
 * the returned subsequences does not matter. If no repeated subsequence is found, the function 
 * should return an empty set.
 * The DNA sequence is composed of a series of nucleotides abbreviated as A, C, G, and T. 
 * For example, ACGAATTCCGACGAATTCCG is a DNA sequence. When studying DNA, it is useful to identify 
 * repeated sequences in it.
 * Constraints:
 * 1 ≤ s.length ≤ 10^4
 * s[i] is either A, C, G, or T.
 * Test Cases:
 * "AAAAACCCCCAAAAACCCCCC" , 8
 * "GGGGGGGGGGGGGGGGGGGGGGGGG" , 12
 * "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT" , 10
 * "AAAAAACCCCCCCAAAAAAAACCCCCCCTG" , 10
 * "ATATATATATATATAT" , 6
 */

//problem statement for SlidingWindowDemo5.java, SlidingWindowDemo6.java is same. The solutions are different. 
//Here we are calculating the hash value for sliding window characters.
public class SlidingWindowDemo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		Set<String> output = findRepeatedSequences("AGACCTAGAC",3);
		long endTime = System.currentTimeMillis();
		if(output.size() > 0)
			output.stream().forEach(i -> System.out.println(i));
		else
			System.out.println("No DNA sequence");
		System.out.println("Time taken in ms: "+(endTime - startTime));
	}

	public static Set<String> findRepeatedSequences(String s, int k) {
		if(s.length() <= k)
			return new HashSet<String>();
		int base = 4;
		int hashing = 0;
		int hiPlaceVal = (int) Math.pow(base, k);
		System.out.println("hiPlaceVal:: "+hiPlaceVal);
		Map<Character, Integer> mapping = new HashMap<>();
		mapping.put('A', 1);
		mapping.put('C', 2);
		mapping.put('G', 3);
		mapping.put('T', 4);
		List<Integer> numbers = new ArrayList<>();
		for(int i=0;i<s.length();i++) {
			numbers.add(mapping.get(s.charAt(i)));
		}
		System.out.println("numbers :: ");
		for(int p : numbers)
			System.out.println(p);
		Set<Integer> seen = new HashSet<>();
		Set<String> output = new HashSet<>();
		for(int start = 0;start<s.length()-k+1;start++) {
			System.out.println("hashing before compute:: "+hashing);
			if(start != 0) {
				hashing  = hashing * base;
				System.out.println("hashing %%:: "+hashing+"   start:: "+start+"  hiplaceval :: "+hiPlaceVal);
				hashing -= numbers.get(start-1) * hiPlaceVal;
				System.out.println("hashing %%--:: "+hashing);
				hashing += numbers.get(start+k-1);
				System.out.println("hashing %%--++:: "+hashing);
			}
			else {
				for(int j=0;j<k;j++) { 
					hashing = hashing * base +numbers.get(j);
				}
				System.out.println("hashing %%--++::^^ "+hashing);
			}
			if(seen.contains(hashing)) {
				System.out.println("substring to be added:: start:: "+start+"  ::   "+s.substring(start, start+k));				
				output.add(s.substring(start, start+k));
			}				
			seen.add(hashing);
		}
		return output;
	}
}

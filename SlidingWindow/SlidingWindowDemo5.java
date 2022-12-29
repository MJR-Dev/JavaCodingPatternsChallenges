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

public class SlidingWindowDemo5 {

	public static String dnaSeqChar = "ACGT";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> lst = findRepeatedSequences("TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT",10);
		if(lst.size() > 0) {
			for(String seq : lst) {
				System.out.println("seq :: "+seq);
			}
		}
		else
			System.out.println("No DNA subsequence found!!");
			
	}

	//check s.length() > k
	public static List<String> findRepeatedSequences(String s, int k) {
		List<String> repSeq = new ArrayList<>();
		Map<String,Integer> subSeq = new HashMap<>();
		if(s.length() <= k)
			return new ArrayList<String>();
		if(s.length() > k) {
			for(int startInd = 0;startInd <= (s.length()-k);startInd++) {
				int endInd = startInd + k;
				String windowStr = s.substring(startInd, endInd);
				if(subSeq.containsKey(windowStr)) {
					int count = subSeq.get(windowStr);
					subSeq.put(windowStr, (count+1));
				}
				else {
					subSeq.put(windowStr, 1);
				}
			}
			for(Map.Entry<String, Integer> entry : subSeq.entrySet()) {
				boolean flag = false;
				if(entry.getValue() > 1) {
					String tmpSeq = entry.getKey();
					for(int j=0;j<tmpSeq.length();j++) {
						if(dnaSeqChar.contains(String.valueOf(tmpSeq.charAt(j))))
								flag = true;
						else
							flag = false;
					}
					if(flag)
						repSeq.add(tmpSeq);
				}
			}
			if(repSeq.size() > 0)
				return repSeq;
				
		}
	      
		return new ArrayList<String>();
	}
}

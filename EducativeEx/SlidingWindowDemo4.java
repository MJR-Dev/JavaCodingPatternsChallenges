import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SlidingWindowDemo4 {
	
	public static TreeMap<String, Integer> windowSizeVal = new TreeMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abababa";
		String t = "cdcd";
		Map<String,Integer> subStrWndw = getMapSubStrCount(t);
		TreeMap<String, Integer> flagWndw = checkStrForSubStr(s,t,subStrWndw);
		if(!flagWndw.isEmpty() || flagWndw.size() > 0)
			System.out.println("Minimum Sliding Window Subsequence : "+flagWndw.firstKey()+"  "+flagWndw.get(flagWndw.firstKey()));
		else
			System.out.println("no such sliding window");
	}
	
	public static Map<String, Integer> getMapSubStrCount(String t){
		Map<String, Integer> subStrCnt = new HashMap<>();
		for(int i=0;i<t.length();i++) {
			String tmp = String.valueOf(t.charAt(i));
			if(subStrCnt.containsKey(tmp)) {
				int count = subStrCnt.get(tmp);
				subStrCnt.put(tmp, (count+1));
			}
			else {
				subStrCnt.put(tmp, 1);
			}
  		}		
		return subStrCnt;
	}
	
	public static boolean checkStrValidToBeChecked(String s, Map<String,Integer> subStrValMap ) {
		boolean validStrToBeChecked = false;	
		Set<String> keySetChar = subStrValMap.keySet();
		Map<String, Integer> mapToCheck = new HashMap<>();
		for(int i=0;i<s.length();i++) {
			String tmpChar = String.valueOf(s.charAt(i));
			 if(keySetChar.contains(tmpChar)) {
				 if(mapToCheck.containsKey(tmpChar)) {
						int count = mapToCheck.get(tmpChar);
						mapToCheck.replace(tmpChar, (count+1));
					}
					else {
						mapToCheck.put(tmpChar, 1);
					}
			 }
		}
		if(mapToCheck.isEmpty() || mapToCheck.size()==0)
			return false;
		for(String str : keySetChar) {
			if(mapToCheck.get(str) >= subStrValMap.get(str))
				validStrToBeChecked = true;
			else 
				validStrToBeChecked = false;
		}		
		return validStrToBeChecked;
	}
	
	//Check to see if all the characters in the second string(t) occur in the same order, in the first string(s)
	public static TreeMap<String, Integer> checkStrForSubStr(String s, String t,Map<String,Integer> subStrValMap){
		int startInd = 0;
		int endInd = 0;
		int begin = 0;
		boolean windowValid = false;
		
		do {	
				windowValid = checkStrValidToBeChecked(s,subStrValMap);
				if(windowValid) {
					begin = s.indexOf(t.charAt(0));				
					String tmpStr = s.substring(begin);
					StringBuilder sb = new StringBuilder();
					boolean flag= false;
					for(int l=0;l<tmpStr.length();l++) {
						if(startInd <= t.length()-1) {
							if(tmpStr.charAt(l) == t.charAt(startInd)) {
								flag = true;
								startInd += 1;			
							}
							else
								flag = false;
							sb.append(tmpStr.charAt(l));
							endInd = l;							
						}
						else {							
							if(flag)
								windowSizeVal.put(sb.toString(), sb.length());
							s = tmpStr.substring(endInd+1);							
							break;
						}
						//Case where the sliding window includes the entire first string
						if(sb.length() == tmpStr.length())
							windowSizeVal.put(sb.toString(), sb.length());
					}
				}
				else 
					break;
		}while(!s.isEmpty());
		return windowSizeVal;
	}	
}

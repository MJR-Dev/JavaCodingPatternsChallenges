package com.edu.TwoPointer;

import java.util.Scanner;

public class ValidWordAbbreviation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		String word = "kxecdvdnfghoybgwl";
		String abbr = "2e2vdn1g1o";
		//System.out.println("Valid Word Abbreviation: " + checkAbbr(word, abbr));
		System.out.println("Valid Word Abbreviation: " + validWordAbbreviation(word, abbr));
	}

	//innovation , in5ion - true
	//kxecdvdnfghoybgwl, 2e2vdn1g1o - false
	//kkusiyrkmr, 10 - true
	//rxbehdcmpygfsurufnbf, r2e333u2f - false
	//yaqeeu, 3e1u22 - false
	//Approach 1 - uses two pointer pattern, covers all edge cases, multi-digit characters
	//Time complexity - O(n), Space complexity - O(1)
	public static boolean checkAbbr(String word, String abbr) {
		int wStart = 0;
		int aStart = 0;
		if(word.length() >= abbr.length()) {
			while(aStart < abbr.length()) {
			    if(wStart > word.length() - 1)
					return false;
				char ch1 = abbr.charAt(aStart);
				char ch2 = word.charAt(wStart);
				if(Character.isLetter(ch1)) {
					if(Character.isLetter(ch2) && ch1 != ch2)
						return false;
					else {
						wStart++;
						aStart++;
					}
				} else if(Character.isDigit(ch1)){
					if(Character.getNumericValue(ch1) == 0)
						return false;
					int num = 0;
					while(aStart < abbr.length() && Character.isDigit(abbr.charAt(aStart))) {
						num = num * 10 + (abbr.charAt(aStart) - '0');
						aStart++;
					}
					if(num > (word.length() - wStart))
						return false;
					wStart += num;					
				}
				//aStart++;
			}
			if((aStart == abbr.length()) && (wStart != word.length()))
				return false;
			return true;
		} else {
			return false;
		}	
	}
	
	//Approach 2 - Using variables for only pointers and counters
	//Time complexity - O(n), space complexity - O(1)
	public static boolean validWordAbbreviation(String word, String abbr) {
		int w = 0, a = 0;
		while(a < abbr.length() && w < word.length()) {
			char ch1 = abbr.charAt(a);
			if(Character.isLetter(ch1)) {
				if(ch1 != word.charAt(w))
					return false;			
				w++;
				a++;
			} else if(Character.isDigit(ch1)){
				if(ch1 == '0') return false;
				int num = 0;
				while(a < abbr.length() && Character.isDigit(abbr.charAt(a))) {
					num = num * 10 + (abbr.charAt(a) - '0');
					a++;
				}
				if(num > (word.length() - w))
					return false;
				w += num;
			}
		}		
		return (a == abbr.length() && w == word.length());
	}
}

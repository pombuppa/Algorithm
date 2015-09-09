package com.songkiet.lcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		
		String str1 = "This is a text";
		String str2 = "is text";
		
		List<Character> seq1 = new ArrayList<Character>();
		List<Character> seq2 = new ArrayList<Character>();
		
		for (char c : str1.toCharArray()) {
			seq1.add(c);
		}
		for (char c : str2.toCharArray()) {
			seq2.add(c);
		}
		
		LCS lcs = new LCS();
		
		int length = lcs.LCSLength(seq1, seq2);
		
		System.out.println("Longest common sequence is " + length);
		
	}
}

class LCS {
	public LCS() {
		
	}

	public int LCSLength(List<Character> seq1, List<Character> seq2) {
		
		if (seq1.isEmpty() || seq2.isEmpty()) {
			return 0;
		} else if (seq1.get(0) == seq2.get(0)) {
			return 1 + LCSLength(seq1.subList(1, seq1.size()), seq2.subList(1, seq2.size()));
		} else {
			return Math.max(LCSLength(seq1.subList(1, seq1.size()), seq2),
					        LCSLength(seq1, seq2.subList(1, seq2.size())));
		}
	}
}

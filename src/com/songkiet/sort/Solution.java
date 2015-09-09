package com.songkiet.sort;

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		
		int length = 50;
		int[] input = new int[length];
		
		Random rand = new Random();
		
		for (int i=0; i < length; i++) {
			input[i] = rand.nextInt(length);
		}
		
		for (int i : input) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.println("");
		
		Sort.quickSort(input, 0, length -1 );
		
		for (int i : input) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.println("");
		
	}
}

class Sort {

	
	public static void quickSort(int[] input, int low, int high) {
		
		int i = low;
		int j = high;
		
		int pivot = (low + high)/2;
		
		while (i <= j) {
			
			while (input[i] < input[pivot]) {
				i++;
			}
			
			while (input[j] > input[pivot]) {
				j--;
			}
			
			if (i <= j) {
				int tmp = input[i];
				input[i] = input[j];
				input[j] = tmp;
				
				i++;
				j--;
			}
		}
		
		if (low < j) {
			quickSort(input, low, j);
		}
		if (i < high) {
			quickSort(input, i, high);
		}
	}
}

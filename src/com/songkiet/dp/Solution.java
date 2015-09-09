package com.songkiet.dp;

import java.math.BigInteger;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		//int t1 = scan.nextInt();
		//int t2 = scan.nextInt();
		//int n = scan.nextInt();
		
		//BigInteger[] memorize = new BigInteger[n];
		//memorize[0] = BigInteger.valueOf(t1);
		//memorize[1] = BigInteger.valueOf(t2);
		
		//for (int i=2; i<n ;i++) {
		//	memorize[i] = memorize[i-1].multiply(memorize[i-1]).add(memorize[i-2]);
		//}
		
		//System.out.println(memorize[n-1]);
		
		int testNum = scan.nextInt();
		
		for (int i=0; i < testNum; i++) {
			int arraySize = scan.nextInt();
			
			int[] array = new int[arraySize];
			for (int j=0; j < arraySize; j++) {
				array[j] = scan.nextInt();
			}
			
			//DynamicProgramming.maxSumSubarray(array);
			DynamicProgramming.kadane(array);
		}
		
		scan.close();
	}
}

class DynamicProgramming {
	
	public static void maxSumSubarray(int[] array) {
		//BigInteger ans = BigInteger.valueOf(Long.MIN_VALUE);
		//BigInteger ans2 = BigInteger.valueOf(Long.MIN_VALUE);
		long ans = Long.MIN_VALUE;
		long ans2 = Long.MIN_VALUE;
		
		for (int i=0; i< array.length; i++) {
			//BigInteger sum = BigInteger.valueOf(0);
			long sum = 0L;
			
			for (int size=1; size <= array.length; size++) {
				if (i + size > array.length) {
					break;
				}
				//sum = sum.add(BigInteger.valueOf(array[i + size - 1]));
				sum = sum + array[i + size -1];
				
				//if (sum.compareTo(ans) > 0) {
				if (sum > ans ) {
					ans = sum;
				}
			}
			
			//BigInteger value = BigInteger.valueOf(array[i]);
			long value = array[i];
			if (i == 0) {
				ans2 = value;
			//} else if (value.add(ans2).compareTo(ans2) > 0 ) {
			//	ans2 = value.add(ans2);
			//}
			} else if (value + ans2 > ans2) {
				ans2 = value + ans2;
			}
		}
		
		System.out.println(ans + " " + ans2);
	}
	
	public static void kadane(int[] array) {
		
		long ans = array[0];
		long sum = array[0];
		long element = Long.MIN_VALUE;
		
		for (int i=1; i < array.length; i++) {

			if (array[i] > sum + array[i]) {
				sum = array[i];
			} else {
				sum = sum + array[i];
			}
			
			if (sum > ans) {
				ans = sum;
			}

		}
		if (ans == 0) {
			ans = element;
		}
		
		long ans2 = Long.MIN_VALUE;
		for (int i=0; i < array.length; i++) {
			if (i==0) {
				ans2 = array[i];
			} else if (array[i] + ans2 > ans2) {
				ans2 = array[i] + ans2;
			}
		}
		System.out.println(ans + " " + ans2);

	}
	
	public static void kadaneBigInteger(int[] array) {
		
		long ans = array[0];
		long sum = array[0];
		long element = Long.MIN_VALUE;
		
		for (int i=1; i < array.length; i++) {

			if (array[i] > sum + array[i]) {
				sum = array[i];
			} else {
				sum = sum + array[i];
			}
			
			if (sum > ans) {
				ans = sum;
			}

		}
		if (ans == 0) {
			ans = element;
		}
		
		long ans2 = Long.MIN_VALUE;
		for (int i=0; i < array.length; i++) {
			if (i==0) {
				ans2 = array[i];
			} else if (array[i] + ans2 > ans2) {
				ans2 = array[i] + ans2;
			}
		}
		System.out.println(ans + " " + ans2);

	}
}

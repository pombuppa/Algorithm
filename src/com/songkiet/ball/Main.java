package com.songkiet.ball;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int numSwap = scan.nextInt();
        int numSet = scan.nextInt();
        
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        
        int[][] swapPair = new int[numSwap][2];
        
        for (int i=0; i < numSwap; i++) {
            int pos1 = scan.nextInt();
            int pos2 = scan.nextInt();
            swapPair[i][0] = pos1;
            swapPair[i][1] = pos2;
        }
        
        for (int set=0; set < numSet; set++) {
        	for (int i=0; i < numSwap; i++) {
        		int pos1 = swapPair[i][0];
        		int pos2 = swapPair[i][1];
        		
        		int temp = array[pos1];
        		array[pos1] = array[pos2];
        		array[pos2] = temp;
        	}
        }
        
        for (int i=0; i < 8; i++) {
        	System.out.print(array[i]);
        	if (i < 7)
        		System.out.print(" ");
        }
        System.out.println();
        
        scan.close();
	}
}

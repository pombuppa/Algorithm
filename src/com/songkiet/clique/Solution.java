package com.songkiet.clique;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int testNum = scan.nextInt();
        
        long sum = 0L;
        for (int i=0; i < testNum; i++) {
        	
        	int n = scan.nextInt(); // vertex
        	int m = scan.nextInt(); // edge
 
        	double minClique = n*n/(n*n - 2.0*m);
        	int floor = (int) minClique;
        	
        	if ((minClique % 1) == 0) {
        		System.out.println((int)(minClique));
        	} else if (minClique - floor < 0.5) {
        		System.out.println((int)(minClique + 1.0));
        	} else {
        		System.out.println((int)(minClique + 0.5));
        	}
        
        }
        
        scan.close();
    }
}

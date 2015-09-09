package com.songkiet.rotatestring;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int arrayLen = scan.nextInt();
        
        String s = scan.next();
        char[] array = s.toCharArray();

        int rotateNum = scan.nextInt();
        
        for (int i=0; i < rotateNum; i++) {
        	
        	int startIndex = scan.nextInt() - 1;
        	int endIndex = scan.nextInt() - 1;
        	int count = scan.nextInt();
        	
        	int rotateSize = endIndex - startIndex + 1;
        	
        	count = count % rotateSize;
    		
    		int split = rotateSize - count;
    		
    		Rotate.rotate(array, startIndex, startIndex + split-1);
    		Rotate.rotate(array, startIndex + split, startIndex + rotateSize-1);
    		Rotate.rotate(array, startIndex, startIndex + rotateSize-1);
        }
        
        System.out.println(array);

        
        scan.close();
	}
}


class Rotate {

	public static void rotate(char[] array, int start, int end) {
		
		while (start < end) {
			char temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}
}

package com.songkiet;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		String text = scan.next();
	
		int sum = a+b+c;

		System.out.println(sum + " " + text);
		
		scan.close();
	}
}

package genericcode;

import java.util.Scanner;

public class GenericCode {

	public static boolean isLeapYear(int year) {

		return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));

	}

	public static void printLeapYears(int start, int end) {
		int counter = start;
		while (counter<= end) {
			if(isLeapYear(counter)) {
				System.out.println(counter);
			}
			counter++;
		}
	
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int startYear = 0;
		int endYear = 0;

		System.out.println("Enter Start Year: ");
		startYear = scnr.nextInt();

		System.out.println("Enter End Year: ");
		endYear = scnr.nextInt();
		
		printLeapYears(startYear, endYear);
	}
}

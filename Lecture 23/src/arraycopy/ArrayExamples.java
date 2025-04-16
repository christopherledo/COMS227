package arraycopy;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayExamples {

	public static void names() {
		String[] names = new String[4];
		names[0] = "Bob";
		names[1] = "Kim";
		names[2] = "Sue";
		// since names[3] is not set to anything, it will print "null"
		// this is because names[3] points to the memory location of the String names
		// array,
		// which creates a memory location for all 4 names in the **heap**
		for (int i = 0; i < names.length; ++i) {
			System.out.println(names[i]);
		}

	}

	public static void ratingReport() {
		Scanner scnr = new Scanner(System.in);

		int[] ratingCounts = new int[5];

		System.out.println("Enter rating 1-5, -1 to quit:");
		int rating = scnr.nextInt();

		while (rating != -1) {
//			if (rating == 5) {
//				++count5;
//			}
			ratingCounts[rating - 1]++; // index starts at 0, not 1, so index offset by -1
			System.out.println("Enter rating 1-5, -1 to quit:");
			rating = scnr.nextInt();
		}

		// output ratings
		for (int i = 0; i < ratingCounts.length; i++) {
			System.out.println("Number of " + (i + 1) + " star ratings is: " + ratingCounts[i]);
			// note from instructor: (i+1) corrects the index, but if one typed "i + 1"
			// without the parentheses,
			// it would print "01, 11, 21, 31, 41"
		}

	}

	public static int[] reverse(int[] arr) {
		int[] rev = Arrays.copyOf(arr, arr.length); // creates a temp copy of the array (like the temp variable in
													// string reversal)
		for (int i = 0; i < rev.length / 2; ++i) {
//			rev[i] = arr[arr.length - i - 1];
			swap(rev, i, rev.length - i - 1);
		}
		return rev;
	}


	public static void swap(int[] arr, int i1, int i2) {
		int oldi1 = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = oldi1;
	}

	public static void main(String[] args) {
//		names();
//		ratingReport();
		int[] testVals = {1,2,3,4,5};
		int[] answer = reverse(testVals);
		
		for(int i = 0; i <answer.length; i++) {
			System.out.println(answer[i]);
		}
		
				
	}

}

package arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ArrayListExamples {

	public static double findMedian(String text) {
		Scanner scnr = new Scanner(text);

		// count how many values are in the string
		int length = 0;
		while (scnr.hasNextInt()) {
			scnr.nextInt();
			length++;
		}

		int[] arr = new int[length];
		scnr = new Scanner(text);
		for (int i = 0; i < length; ++i) {
			int value = scnr.nextInt();
			arr[i] = value;
		}

		Arrays.sort(arr);// part of the Arrays util, sorts the numbers in the array least to greatest

		if (length % 2 == 0) { // if length is even
			return (arr[length / 2] + arr[(length / 2) - 1]) / 2.0;
		}

		else { // if length is odd
			return arr[length / 2];
		}
	}

	public static double findMedianArrayList(String text) {
		Scanner scnr = new Scanner(text);
		ArrayList<Integer> values = new ArrayList<Integer>();

		while (scnr.hasNextInt()) {
			values.add(scnr.nextInt());
		}

		Collections.sort(values);

		if (values.size() % 2 == 0) { // if length is even
			return (values.get(values.size() / 2) + values.get((values.size() / 2) - 1)) / 2.0;
		}

		else { // if length is odd
			return values.get(values.size() / 2);
		}
	}

	public static void main(String[] args) {
		/*
		 * ArrayList<String> names = new ArrayList<String>();
		 * 
		 * names.add("Bob"); names.add("Kim"); names.add("Sue"); names.add(1, "John");
		 * names.remove(2);
		 * 
		 * System.out.println("The length of 'names' is " + names.size());
		 * 
		 * for (int i = 0; i < names.size(); ++i) { System.out.println("Name " + i +
		 * " is " + names.get(i)); }
		 * 
		 * System.out.println("----------------------"); int count = 1; for (String name
		 * : names) { System.out.println("Name " + count + " is " + name); count++; }
		 * 
		 * System.out.println("----------------------");
		 * System.out.println(names.toString()); System.out.println(names);
		 * System.out.println("Note that these outputs are identical.");
		 * 
		 * System.out.println("----------------------");
		 */

		// Integer objects
		/*
		 * Integer num = Integer.valueOf(2000); Integer num2 = 2000; if
		 * (num.equals(num2)) {
		 * System.out.println("num.equals(num2) evaluates to true");
		 * System.out.println("These can be thought of as equivalent."); }
		 * 
		 * if (num == num2) { System.out.println("num == num2 evaluates to true"); }
		 * 
		 * else { System.out.println("num == num2 evaluates to false"); System.out.
		 * println("JVM is caching integer values. == only works for integers between -128 and 127. Don't use == to compare objects."
		 * ); }
		 */

		/*
		 * ArrayList<Integer> ints = new ArrayList<Integer>();
		 * 
		 * ints.add(1); ints.add(2); ints.add(Integer.valueOf(3));
		 * 
		 * System.out.println("The size is: " + ints.size());
		 * 
		 * for(int i = 0; i < ints.size(); i++) { System.out.println(ints.get(i)); }
		 * 
		 * int myAutoInt = ints.get(0);
		 * 
		 * System.out.println("Unboxing: " + myAutoInt);
		 */
		System.out.println("With arrays (odd): " + findMedian("1 2 4 5 3"));
		System.out.println("With arrayLists (odd): " + findMedianArrayList("1 2 4 5 3"));
		System.out.println("With arrays (even): " + findMedian("1 4 5 3"));
		System.out.println("With arrayLists (even): " + findMedianArrayList("1 4 5 3"));
		
		System.out.println("----------------------");
	
		//Tokens
		String text = "1 2 3 4 5";
		String[] valueStrings = text.split(" ");
		for (String val : valueStrings) { //for string val is a member of value strings
			int num = Integer.valueOf(val);
			System.out.println(num);
		}
	}
	
}

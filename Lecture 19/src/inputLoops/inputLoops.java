package inputLoops;

import java.util.Scanner;

public class inputLoops {
	public static double averageFromString(String text) {
		double total = 0;
		int count = 0;
		Scanner scnr = new Scanner(text);
		
		while (scnr.hasNextDouble()) {
			total+= scnr.nextDouble();
			count++;
		}
		scnr.close();
		return total / count;
	}
	
	public static double averageFromConsole() {
		double total = 0;
		int count = 0;
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter a number (q to quit): ");
		String input = scnr.next();
		while(!input.equals("q")) {
			double number = Double.parseDouble(input);
			total += number;
			count++;
		}
		scnr.close();
		return total/count;
	}
	
	public static void main (String[] args) {
//		System.out.println(averageFromString("1 2 3 4 5"));
		averageFromConsole();
	}
}

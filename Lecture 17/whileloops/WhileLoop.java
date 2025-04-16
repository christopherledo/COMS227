package whileloops;

import java.util.Scanner;

public class WhileLoop {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String input;
	
		do {
			System.out.println("Continue running?: ");
			input = scnr.next();
		}while(input == "y");
		System.out.println("done");
	}
}

package forloop;

//import java.util.Scanner;

public class ForLoop {
	
	public static String reverse(String input) {
		String reverse = "";
		for (int i = (input.length()-1); i >= 0; i--) {
			reverse += input.charAt(i);
		}
		return reverse;
	}

	public static void main(String[] args) {
//		Scanner scnr = new Scanner(System.in);
//		
//		System.out.print("Would you like to continue?: ");
//		
//		for(String input = scnr.next(); input.equals("y"); input = scnr.next()) {
//			System.out.println("In the loop!");
//			System.out.print("Would you like to continue?: ");
//		}
//		System.out.println("All done!");
		
//		int counter = 0;
//		while (counter<10) {
//			System.out.println(counter);
//			counter++;
//		}
		
		String str = "APPLE CAT!";
		reverse(str);
		System.out.println(str);
		
	}
}

package recursion;

public class Recursion {
	
	public static void countUp(int n) {
		if (n <= 0) {
			return;
		}
		countUp(n-1);
		System.out.println(n);
	}
	
	public static int fac(int n) {
		if (n <= 1) {
			return 1;
		}
		
		return n * fac(n-1);
	}
	
	public static int fib(int n) {
		//base case
//		if (n ==0) {
//			return 0;
//		}
//		if (n == 1) {
//			return 1;
//		}
		
		if (n == 0 || n == 1) {
			return n;
		}
		//recursion
		return fib(n-1) + fib(n-2);
	}

	public static void main(String[] args) {
		int answer = fac(5);
		System.out.println("The answer is " + answer);
		answer = fib(10);
		System.out.println("The answer is " + answer);
//		countUp(5);
	}

}

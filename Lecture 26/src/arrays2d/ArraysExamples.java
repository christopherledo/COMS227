package arrays2d;

import java.util.Arrays;

public class ArraysExamples {
	public static int calcSum(int[][] arr) {
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sum += arr[i][j];
			}
		}
		return sum;
	}

	public static int calcSum2(int[][] arr) {
		int sum = 0;
		for (int[] row : arr) {
			for (int col : row) {
				sum += col;
			}
		}
		return sum;
	}

	public static int[] calcSumOfColumns(int[][] arr) {
		int[] sums = new int[arr[0].length];
		
		for (int col = 0; col < sums.length; ++col) {
			for (int row = 0; row < arr.length; row++) {
				sums[col] += arr[row][col];
			}
		}

		return sums;
	}

	public static void main(String[] args) {
		int[][] arr = new int[2][3];

		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr.length; ++j) {
				System.out.println(i + j + arr[i][j]);
			}
		}

		System.out.println("-------------------------");

		// Shallow copy

		int[][] arr2 = new int[3][3];

		arr2[0][2] = 10;

		int[][] copyArr = Arrays.copyOf(arr2, arr2.length);

		copyArr[0][2] = 7;

		System.out.println(arr2[0][2]);
		System.out.println(copyArr[0][2]);

		System.out.println("-------------------------");

		// Deep copy

		int[][] arr3 = new int[3][3];

		arr3[0][2] = 10;

		int[][] copyArr2 = new int[arr3.length][arr3[0].length];

		for (int i = 0; i < arr3.length; i++) {
			for (int j = 0; j < arr3[i].length; j++) {
				copyArr2[i][j] = arr3[i][j];
			}
		}
		copyArr2[0][2] = 7;

		System.out.println(arr3[0][2]);
		System.out.println(copyArr2[0][2]);

		System.out.println("-------------------------");

		int[][] arr4 = new int[3][3];

		arr4[0][2] = 10;

		int[][] copyArr3 = new int[arr3.length][arr3[0].length];

		for (int i = 0; i < arr4.length; i++) {
			copyArr3[i] = Arrays.copyOf(arr4[i], arr4[i].length);
		}

		copyArr3[0][2] = 7;

		System.out.println(arr4[0][2]);
		System.out.println(copyArr3[0][2]);

		System.out.println("-------------------------");

		// Accumulator pattern for 2D arrays

		int[][] arr5 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int answer = calcSum(arr5);
		System.out.println("The answer is: " + answer);

		answer = calcSum2(arr5);
		System.out.println("The answer is: " + answer);
		
		System.out.println("-------------------------");
		
		int[] answerArr = calcSumOfColumns(arr5);
		for(int i = 0; i < answerArr.length; i++) {
			System.out.println(answerArr[i]);
		}
		
	}

}

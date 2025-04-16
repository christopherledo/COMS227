package arrays;

public class Arrays {

//		int[] ids;
//		ids = new int[5];
//		for (int i = 0; i < ids.length; ++i) {
//			ids[i] = i;

	public static void names() {
		String[] names = { "Bob", "Kim", "Sue" };
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}
	
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.println(arr[i]);
		}
	}
	
	public static void printReverse(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}

	public static int[] reverse(int[] arr) {
		int[] rev = new int[arr.length];

		for (int i = 0; i < arr.length; ++i) {
			rev[i] = arr[arr.length - 1 - i]; // TODO: REMEMBER THIS, THIS IS THE REVERSE INDEX NOTATION
		}
		return rev;
	}

	public static void reverseInPlace(int[] arr) {
		for (int i = 0; i < (arr.length - 1) / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = temp;
		}
	}

	public static boolean hasDuplicates(int[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void clampToZero(int arr[]) {
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] < 0) {
				arr[i] = 0;
			}
		}
	}

	public static void main(String[] args) {
		int[] ids = { 5, 2, -3, -4, 5 };
		reverseInPlace(ids);
		for (int i = 0; i < ids.length; ++i) {
			System.out.println(ids[i]);
		}
		
		System.out.println(hasDuplicates(ids));
		clampToZero(ids);
		for(int id : ids) {//"is a" operation
			System.out.println(id); //prints entire array
		}
		
	}

}

package nestedloops;

public class NestedLoops {
	public static int indexof(String text, String sub) {
		int index = -1;
		
		for (int i = 0; i < text.length(); ++i) {
			if (text.substring(i, i + sub.length()).equals(sub)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static boolean hasDuplicates(String text) {
		boolean duplicates = false;

		// text = "text"
		// i = 0
		// j = 3
		// text.charAt(i) = t
		// text.charAt(j) = t
//		outerloop:
		for (int i = 0; i < text.length(); ++i) {
			for (int j = i + 1; j < text.length(); ++j) {
				if (text.charAt(i) == text.charAt(j)) {
					duplicates = true;
					//Sets both loop counters to their exit conditions to terminate the loop
					i = text.length();
					j = text.length();
//					break outerloop;
				}

			}
		}
		return duplicates;
	}

	public static void charForChar(String string) {
		for (int i = 0; i < string.length(); i++) {
			for (int j = 0; j < string.length(); j++) {
				System.out.println(string.charAt(i) + " " + string.charAt(j));
			}
		}
	}

	public static void main(String[] args) {
//		charForChar("astring");
//		System.out.println(hasDuplicates("text"));
		System.out.println(indexof("static", "at"));
	}
}

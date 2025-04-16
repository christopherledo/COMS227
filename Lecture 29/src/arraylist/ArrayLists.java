package arraylist;

import java.util.ArrayList;

public class ArrayLists {
	public static ArrayList<String> listPermutations(String text){
		ArrayList<String> list = new ArrayList<String>();
		
		//base case
		if(text.length() == 1) {
			list.add(text);
			return list;
		}
		
		//recursion
		for (int i = 0; i < text.length(); i++) {
			//the character in question
			char c = text.charAt(i);
			// the string without the character in question
			String remainder = text.substring(0,i) + text.substring(i + 1);
			
			ArrayList<String> sublist = listPermutations(remainder);
			for(String s : sublist) {
				list.add(c + s);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(listPermutations("ABC"));
	}
}

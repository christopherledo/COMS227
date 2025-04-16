package recursion;

import java.io.File;

public class FileLister {
	
	public static void listOneLevel(File file) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File subfile : files) {
				System.out.println(subfile.getName());
			}
		}
	}
	
	public static void listAllFiles(File file) {
		
		//base case
		if(!file.isDirectory()) {
			System.out.println(file.getName());
			return;
		}
		
		//recursion
		File[] files = file.listFiles();
		System.out.println(file.getName());
		for(File subfile : files) {
			listAllFiles(subfile);
		}
		
	}
	
public static void listAllFilesIndented(File file, String padding) {
		
		//base case
		if(!file.isDirectory()) {
			System.out.println(padding + file.getName());
			return;
		}
		
		//recursion
		File[] files = file.listFiles();
		System.out.println(padding + file.getName());
		for(File subfile : files) {
			listAllFilesIndented(subfile, padding + "- ");
		}
		
	}

	public static void main(String[] args) {
//		listAllFiles(new File("/Root"));
		listAllFilesIndented(new File("./Root"), "- ");
	}

}

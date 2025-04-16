package conditionals2;

public class GradeBook {
	public String letterGrade (int percent) {
		String letter;
		
		if (percent >= 90) {
			letter = "A";
		}
		
		else if (percent >= 80) {
			letter = "B";
		}
		
		else if (percent >= 70) {
			letter = "C";
		}
		
		else if (percent >= 60) {
			letter = "D";
		}
		
		else {
			letter = "F";
		}
		
		return letter;
	}
	
}

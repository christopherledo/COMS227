package utilities2;

public class Utilities {
	public static final int IN_PER_FT = 12;
	public static final double CM_PER_IN = 2.54;
	public static double convertFtToIn(double ft) {
		return ft * IN_PER_FT;
	}
	
	public static double convertInToCm(double in) {
		return in * CM_PER_IN;
	}
}

//NOTE from class: Idempotence, which is the idea that running an operation after the first time doesn't change anything
package utilities2;

import java.util.Scanner;

public class CalorieCalc {
	//private final static double FEET_PER_STEP = 2.5; // Typical adult
	private final static int FEET_PER_MILE = 5280;
	private final static double STEPS_PER_MINUTE = 70.0; // Typical adult
	private final static double CALORIES_PER_MINUTE_WALKING = 3.5; // Typical adult
	private double feetPerStep;

	public static void main(String[] args) {
		CalorieCalc c = new CalorieCalc(3);
		
		int stepsWalked = 10;
		double milesWalked = c.calcMilesWalked(stepsWalked);
		double calories = c.calcCalories(stepsWalked);
		c.displayMilesWalked(milesWalked);
		c.displayCalories(calories);

	}
	
	public CalorieCalc(double feetPerStep) {
		this.feetPerStep = feetPerStep;
	}

	public int inputNumSteps() {
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter number of steps walked: ");
		int stepsWalked = scnr.nextInt();
		return stepsWalked;
	}
	
	

	public double calcMilesWalked(int stepsWalked) {
		double milesWalked;
		milesWalked = stepsWalked * feetPerStep * (1.0 / FEET_PER_MILE);
		return milesWalked;
	}
	
	public void displayMilesWalked(double milesWalked) {
		System.out.println("Miles walked: " + milesWalked);
	}

	
	public double calcApproxMinutes(int stepsWalked) {
		double minutesTotal = stepsWalked / STEPS_PER_MINUTE;
		return minutesTotal;
	}
	
	public double calcCalories(int stepsWalked) {
		double minutesTotal = calcApproxMinutes(stepsWalked);
		double caloriesTotal = minutesTotal * CALORIES_PER_MINUTE_WALKING;
		return caloriesTotal;
	}
	
	public void displayCalories(double caloriesTotal) {
		System.out.println("Calories: " + caloriesTotal);
	}
}
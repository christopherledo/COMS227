package car;

public class Car {
	private GasTank gasTank;
	private double mpg;
	private double odometer;
	private static final double MILES_TO_KM = 1.60934;
	//private double level = gasTank.getLevel(); this doesn't work lol
	
	/**
	 * Creates a Car object with given mpg and tank capacity.
	 * @param mpg is the fuel efficiency of the car
	 * @param capacity is the capacity of the gas tank
	 */
	public Car(double mpg, int capacity) {
		gasTank = new GasTank(capacity);
		this.mpg = mpg;
	}
	
	public double getOdometer() {
		return odometer;
	}
	
	public double getGasGauge() {
		return gasTank.getLevel() / gasTank.getCapacity();
	}
	
	public void buyGas() {
		gasTank.add(gasTank.getCapacity()-gasTank.getLevel());
	}
	
	public static double convertMilesToKm(double miles) {
		return miles * MILES_TO_KM;
	}
}

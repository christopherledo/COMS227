package hw1;

public class Balloon {
	
	//Instance Variables
	private double fuel;
	private int time;
	private int altitude;
	private int groundPos;
	private int maxGroundPos;
	private int maxAlt;
	private double tankCap;
	private double burnRate;
	private double maxBurnRate;
	private int vertVelocity;
	private int horizVelocity;
	
	//Constructors
	/**
	 * Creates an Balloon object, which is a hot air balloon. 
	 * This will simulate its flight path and take into account factors such as fuel.
	 * It will model the physics of the balloon in terms of velocity as well as position.
	 * @param groundPosition is the position of the balloon with respect to the ground. This will account for changes in terrain.
	 * @param maxGroundPosition is the limit of the balloons groundPosition parameter.
	 * @param maxAltitude is the largest altitude the balloon can fly, regardless of ground position.
	 * @param tankCapacity is the maximum capacity of fuel the balloon can store in its tank.
	 */
	public Balloon(int groundPosition, int maxGroundPosition, int maxAltitude, double tankCapacity) {
		groundPos = groundPosition; //sets to local variable
		maxGroundPos = maxGroundPosition; //sets to local variable
		maxAlt = maxAltitude; //sets to local variable
		tankCap = tankCapacity; //sets to local variable
		
	}
	
	//Methods
	/**
	 * Gets the current velocity of the balloon in the vertical direction
	 * @return
	 */
	public int getVerticalVelocity(){
		return vertVelocity;
	}
	/**
	 * Gets the current velocity of the balloon in the horizontal direction.
	 * @return
	 */
	public int getHorizontalVelocity(){
		return horizVelocity;
	}
	/**
	 * Gets the altitude of the balloon
	 * @return
	 */
	public int getAltitude(){
		return altitude;
	}
	/**
	 * Gets the maximum altitude of the balloon. The balloon may not fly above this value
	 * @return
	 */
	public int getMaxAltitude(){
		return maxAlt;
	}
	/**
	 * Gets the current position of the balloon with respect to the ground (i.e., what ground is it flying over).
	 * @return
	 */
	public int getGroundPosition(){
		return groundPos;
	}
	/**
	 * Gets the farthest ground position the balloon can travel before wrapping back around (in a circle) to position 0.
	 * @return
	 */
	public int getMaxGroundPosition(){
		return maxGroundPos;
	}
	/**
	 * Gets the amount of fuel the balloon has in its fuel tank
	 */
	public double getFuel() {
		return fuel;	
	}
	/**
	 * Gets the maximum capacity of the balloon’s fuel tank
	 * @return
	 */
	public double getTankCapacity(){
		return tankCap;
	}
	/**
	 * Gets the maximum rate of fuel burn (assuming there is enough fuel to burn at this rate).
	 * @return
	 */
	public double getMaxBurnRate(){
		return maxBurnRate;
	}
	/**
	 * Sets the capacity of the fuel tank to the given parameter.
	 * @param tankCapacity is the maximum capacity of fuel the balloon can store in its tank.
	 */
	public void setTankCapacity(double tankCapacity){
		tankCap = tankCapacity; //sets to local variable
	}
	/**
	 * Sets the maximum burn rate to the given parameter.
	 * @param maxBurnRate is the maximum amount of fuel the balloon can burn within a measure of time.
	 */
	public void setMaxBurnRate(double maxBurnRate){
		burnRate = maxBurnRate; //sets to local variable
	}
	/**
	 * Sets the total time the simulation has run in seconds to the given parameter.
	 * @param time is the time of the simulation.
	 */
	public void setTime(int time){
		this.time = time;
	}
	/**
	 * Set the ground position back to where it was set by the constructor.
	 */
	public void restoreInitialGroundPosition(){
		groundPos = 0;
	}
	/**
	 * Gets the number of second past the current minute. The returned value must be between 0 and 60. 
	 * For example, if the current time is 100, the seconds past the current minute are 40.
	 * @return the remaining time from seconds in a minute remaining from seconds in an hour
	 */
	public int getSeconds(){
		return ((time%3600)%60);//returns the remaining time from seconds in a minute remaining from seconds in an hour (this one was gross and I didn't like it)
	}
	/**
	 * Gets the number of full minutes past the current hour. The returned value must be between 0 and 60. 
	 * For example, if the current time is 100, the minutes past the current hour is 1.
	 * @return the remaining time from seconds in an hour divided by minutes in an hour
	 */
	public int getMinutes(){
		return (time % 3600 )/ 60; //returns the remaining time from seconds in an hour divided by minutes in an hour
	}
	/**
	 * Gets the number of full hours that have passed. For example, if the current time is 1000, the number of full hours is 2.
	 * @return the total time divided by the second in an hour
	 */
	public int getHours(){
		return time / 3600; //returns the total time divided by the second in an hour
	}
	/**
	 * Change the vertical velocity by the given delta (i.e., simply add delta to the current velocity).
	 * @param delta is the magnitude of change to a vector value.
	 */
	public void adjustVerticalVelocity(int delta){
		vertVelocity += delta;
	}
	/**
	 * Change the horizontal velocity by the given delta (i.e., simply add delta to the current velocity).
	 * @param delta is the magnitude of change to a vector value.
	 */
	public void adjustHorizontalVelocity(int delta){
		horizVelocity += delta; 
	}
	/**
	 * Add the given amount of fuel to the tank, however the tank cannot fill past its maximum capacity. Return the amount of fuel actually added.
	 * @param amount is the quantity of fuel added.
	 * @return the actual amount of fuel added
	 */
	public double addFuel(double amount){
		double fuelLevel = fuel;//stores the fuel level before amount is added
		fuel = Math.min(tankCap, amount + fuel); //fills the tank by the amount, but stops at the capacity
		return fuel - fuelLevel; //returns the actual amount of fuel added
	}
	/**
	 * Simulate the passing of one second of time.
	 * @return  the amount of fuel used (original level - current level = used)
	 */
	public double oneSecondUpdate(){
		double fuelLevel = fuel; //stores the fuel level before it is burned
		time += 1; //increments the time by one second
		altitude = Math.min(maxAlt, altitude + vertVelocity); /*calculates the altitude using the current vertical position plus the distance traveled in one second (the velocity)
		The calculated result is limited by the Math.min function, and will not surpass maxAlt, the maximum altitude*/
		//groundPos = Math.min(maxGroundPos, groundPos + horizVelocity); OLD FUNCTION, LEFT IN FOR REFERENCE
		groundPos = (maxGroundPos + groundPos + horizVelocity) % maxGroundPos; //calculates the ground position using the current ground position plus the distance traveled in one second (the velocity)
		//NOTE: the MaxGroundPos variable is added in the parentheses and then modulo'd out to prevent the ground position from going below 1 or above the max
		fuel = Math.max(fuelLevel - burnRate, 0); //calculates how much fuel is in the balloon after one second based on the burnRate
		return fuelLevel - fuel; //returns the amount of fuel used (original level - current level = used)
	}
	
	

}

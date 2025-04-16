package car;

public class GasTank {
	private int capacity;
	private int level;
	
	public GasTank(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void add(int amount) {
		level += amount;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void remove(int amount) {
		level = Math.max(level - amount, 0);
	}
}

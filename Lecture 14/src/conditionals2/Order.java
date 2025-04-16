package conditionals2;

public class Order {
	public static final double PRICE = 10.0;
	public static final double SHIPPING_COST_PER_SHIRT = 2.0;
	public static final double FREE_SHIPPING_THRESHOLD = 25.0;
	public static final double TAX_RATE = 0.05;

	private int numShirts;
	private boolean isResident;

	/**
	 * Base price is price_per_item * items Regular shipping cost is
	 * SHIPPING_COST_PER_SHIRT * items For an order of 25 or more shirts, shipping
	 * is free
	 * 
	 * Tax is base price * TAX_RATE No tax if resident
	 * 
	 * @return
	 */
	public double orderTotal() {
		double total = numShirts * PRICE;
		
		if (!isResident){
			total += total * TAX_RATE;
		}
		
		if (numShirts < 25) {
			total += SHIPPING_COST_PER_SHIRT * numShirts;
		}
		
		return total;
	}
}
package cashRegister;
/**
 * this is the main cash register class
 */
public class CashRegister {
	/**
	 * sum of item prices without tax
	 */
	private double subTotal;
	public double taxRate = 0.06;
	
	/**
	 * the constructor for the cash register. it takes in the tax rate.
	 * @param taxRate
	 */
	//these methods are mutators
	//mutators change the object, and do not return any values
	public CashRegister(double taxRate) {
		
	}
	
	/**
	 * add an item to the ticket. it will have tax
	 * @param price
	 */
	
	public void addItem(double price) {
		
	}
	
	/**
	 * add an item to the ticket. it will not have tax
	 * @param price
	 */
	
	public void addItemNonTaxable(double price) {
		subTotal += price;
	}
	
	//these methods are accessors
	//accessors get a value using the 
	
	/**
	 * return the total amount without tax.
	 * @return
	 */
	public double getSubTotal() {
		return subTotal;
	}
	
	/**
	 * get the total with tax.
	 * @return
	 */
	
	public double getTotal() {
		return subTotal+subTotal*taxRate;
	}
}

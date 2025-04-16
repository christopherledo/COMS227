package cashRegister;

public class TestCashRegister {

	public static void main(String[] args) {
		
		//Create new CashRegister with no items
		CashRegister reg = new CashRegister(0.07);
		System.out.println(reg.getSubTotal());
		System.out.println("Expected: 0");
		System.out.println(reg.getTotal());
		System.out.println("expected 0");
		
		//Add non-taxable item
		CashRegister reg1 = new CashRegister(0.07);
		reg1.addItemNonTaxable(10.0);
		System.out.println(reg1.getSubTotal());
		System.out.println("Expected: 10");
		System.out.println(reg1.getTotal());
		System.out.println("expected 10");
		
		//add taxable item
		CashRegister reg2 = new CashRegister(0.07);
		reg2.addItem(10.0);
		System.out.println(reg2.getSubTotal());
		System.out.println("Expected: 10");
		System.out.println(reg2.getTotal());
		System.out.println("expected 10.70");
	}
	

}

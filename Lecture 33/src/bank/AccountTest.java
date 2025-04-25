package bank;

public class AccountTest {
	
	public static void chargeFee(Account acc) {
		acc.withdraw(100);
	}

	public static void main(String[] args) {
		CheckingAccount myChecking = new CheckingAccount(10, 0, 10000);
		SavingsAccount mySaving = new SavingsAccount(10, 0, 100);
		Account acc;
		
		acc = myChecking;
		acc = mySaving;

	}

}

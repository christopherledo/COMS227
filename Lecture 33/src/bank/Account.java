package bank;

public class Account {
	private int id;
	private double balance;
	
	public Account (int id, double openingBalance) {
		this.id = id;
		balance = openingBalance;
	}
	
	public void deposit(int amount) {
		balance+= amount;
	}
	
	public void withdraw(int amount) {
		balance = Math.max(0, balance - amount);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}

class SavingsAccount extends Account{
	private double minBalance;
	
	public SavingsAccount(int id, int openingBalance, int minBalance) {
		super(id, openingBalance);
		this.minBalance = minBalance;
	}
	
	@Override
	public void withdraw(double amount) {
		if (getBalance() - amount >= minBalance) {
		super.withdraw(amount);
		}
	}
}

class CheckingAccount extends Account{
	private double maxWithdraw;
	public CheckingAccount(int id, int openingBalance, int maxWithdraw) {
		super(id, openingBalance);
		this.maxWithdraw = maxWithdraw;
	}
	
	@Override
	public void withdraw(double amount) {
		if (amount < maxWithdraw) {
			super.withdraw(amount);
		}
	}
}
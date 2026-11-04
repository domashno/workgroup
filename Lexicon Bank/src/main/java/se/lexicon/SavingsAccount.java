package se.lexicon;

public class SavingsAccount extends Account {
	private double interestRate;

	public SavingsAccount(String holder, double balance, double interestRate) {
		super(holder, balance);
		this.interestRate = interestRate;
	}

	@Override
	public void processMonth() {
		double interest = getBalance() * interestRate;
		deposit(interest);
		System.out.println(getHolder() + ": interest credited");
	}
}


package se.lexicon;

public class CheckingAccount extends Account {
	private double monthlyFee;

	public CheckingAccount(String holder, double balance, double monthlyFee) {
		super(holder, balance);
		this.monthlyFee = monthlyFee;
	}

	@Override
	public void processMonth() {
		setBalance(getBalance() - monthlyFee);
		System.out.println(getHolder() + ": monthly fee charged");
	}
}


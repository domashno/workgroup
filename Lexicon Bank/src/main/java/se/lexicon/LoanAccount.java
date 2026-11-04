package se.lexicon;

public class LoanAccount extends Account {
	private double loanRate;

	public LoanAccount(String holder, double balance, double loanRate) {
		super(holder, balance);
		this.loanRate = loanRate;
	}

	@Override
	public void processMonth() {
		// Debt increases when monthly interest builds up
		double interestCharge = getBalance() * loanRate;
		setBalance(getBalance() + interestCharge);
		System.out.println(getHolder() + ": loan interest added");
	}
}



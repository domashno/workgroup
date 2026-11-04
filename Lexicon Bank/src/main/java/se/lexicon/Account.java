package se.lexicon;

public abstract class Account {
	private String holder;
	private double balance;

	public Account(String holder, double balance) {
		if (holder == null || holder.trim().isEmpty()) {
			throw new IllegalArgumentException("Holder name cannot be blank.");
		}
		if (balance < 0) {
			throw new IllegalArgumentException("Starting balance cannot be negative.");
		}
		this.holder = holder;
		this.balance = balance;
	}

	public String getHolder() {
		return holder;
	}

	public double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive.");
		}
		this.balance += amount;
	}

	public abstract void processMonth();

	public void printSummary() {
		System.out.println(holder + " | Balance: " + balance + " kr");
	}
}

package se.lexicon.model;

import se.lexicon.exception.InsufficientBalanceException;

public class BankAccount {
	private double balance;

	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}

	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposited: $" + amount + " | New Balance: $" + balance);
		} else {
			System.out.println("Deposit amount must be positive.");
		}
	}

	public void withdraw(double amount) throws InsufficientBalanceException {
		if (amount <= 0) {
			System.out.println("Withdrawal amount must be positive.");
			return;
		}

		if (amount > balance) {
			throw new InsufficientBalanceException(
					"Withdrawal of $" + amount + " failed. Available balance is only $" + balance + "."
			);
		}

		balance -= amount;
		System.out.println("Withdrew: $" + amount + " | Remaining Balance: $" + balance);
	}

	public double getBalance() {
		return balance;
	}
}

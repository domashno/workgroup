package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private String name;
	private List<Account> accounts;

	public Bank(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	public void addAccount(Account account) {
		if (account != null) {
			this.accounts.add(account);
		}
	}

	public void processAllAccounts() {
		for (Account account : accounts) {
			account.processMonth();
		}
	}

	public void printAllSummaries() {
		for (Account account : accounts) {
			account.printSummary();
		}
	}
}



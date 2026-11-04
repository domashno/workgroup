package se.lexicon;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] main) {
		Bank bank = new Bank("Lexicon Bank");

		bank.addAccount(new SavingsAccount("Alice", 1000.0, 0.02)); // 1000 kr, 2% rate
		bank.addAccount(new CheckingAccount("Bob", 500.0, 25.0));    // 500 kr, 25 kr fee
		bank.addAccount(new LoanAccount("Charlie", 5000.0, 0.03));  // 5000 kr, 3% rate

		System.out.println("=== Starting Balances ===");
		bank.printAllSummaries();
		System.out.println();

		System.out.println("=== Processing Month End ===");
		bank.processAllAccounts();
		System.out.println();

		System.out.println("=== Updated Balances ===");
		bank.printAllSummaries();
	}
}

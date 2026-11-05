package se.lexicon;

import java.util.List;
import java.util.Scanner;

public class VendingMachineUI {

	public static void main(String[] args) {
		VendingMachine machine = new LexiconVendingMachine();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Lexicon Vending Machine");
		displayMenu(machine);

		boolean running = true;
		while (running) {
			System.out.print("\n> ");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.isEmpty()) {
				continue;
			}

			switch (input) {
				case "i", "insert" -> handleInsert(machine, scanner);
				case "1", "select" -> handleSelect(machine, scanner);
				case "2", "return" -> handleReturn(machine);
				case "3", "menu"   -> displayMenu(machine);
				case "4", "exit"   -> {
					int change = machine.returnChange();
					if (change > 0) {
						System.out.println("Change returned: " + change + " kr");
					}
					System.out.println("Thank you for using Lexicon Vending Machine. Goodbye!");
					running = false;
				}
				default -> System.out.println(
						"Unknown command. Use: insert (i), select (1), return (2), menu (3), exit (4).");
			}
		}

		scanner.close();
	}

	private static void handleInsert(VendingMachine machine, Scanner scanner) {
		System.out.print("Insert coin: ");
		String coinInput = scanner.nextLine().trim();

		int coin;
		try {
			coin = Integer.parseInt(coinInput);
		} catch (NumberFormatException e) {
			System.out.println("Invalid coin. Only 1, 2, 5, 10, 20, 50 kr accepted.");
			System.out.println("Balance: " + machine.getBalance() + " kr");
			return;
		}

		if (machine.insertCoin(coin)) {
			System.out.println("Balance: " + machine.getBalance() + " kr");
		} else {
			System.out.println("Invalid coin. Only 1, 2, 5, 10, 20, 50 kr accepted.");
			System.out.println("Balance: " + machine.getBalance() + " kr");
		}
	}

	private static void handleSelect(VendingMachine machine, Scanner scanner) {
		System.out.print("Select product: ");
		String idInput = scanner.nextLine().trim();

		int id;
		try {
			id = Integer.parseInt(idInput);
		} catch (NumberFormatException e) {
			System.out.println("Invalid product id.");
			return;
		}

		try {
			Product product = machine.selectProduct(id);
			System.out.println("Dispensing: " + product.getName()
					+ " (" + product.getDescription() + ")");

			int change = machine.returnChange();
			if (change > 0) {
				System.out.println("Change returned: " + change + " kr");
			}
			System.out.println("Balance: " + machine.getBalance() + " kr");
		} catch (VendingException e) {
			System.out.println(e.getMessage());
			System.out.println("Balance: " + machine.getBalance() + " kr");
		}
	}

	private static void handleReturn(VendingMachine machine) {
		int change = machine.returnChange();
		if (change > 0) {
			System.out.println("Change returned: " + change + " kr");
		} else {
			System.out.println("No balance to return.");
		}
	}

	private static void displayMenu(VendingMachine machine) {
		System.out.println("------------------------------------");
		List<Product> products = machine.getProducts();
		for (Product p : products) {
			System.out.printf("[%d] %-12s - %2d kr  (%-20s) Stock: %d%n",
					p.getId(), p.getName(), p.getPrice(),
					p.getDescription(), p.getQuantity());
		}
		System.out.println("------------------------------------");
		System.out.println("Balance: " + machine.getBalance() + " kr");
	}
}

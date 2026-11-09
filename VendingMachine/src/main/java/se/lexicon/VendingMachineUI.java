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
			System.out.print(
					"\nOptions: [i]nsert coin, [s]elect product, " +
							"[r]eturn change, [m]enu, [h]istory, " +
							"[a]dmin, [e]xit\n> "
			);

			String input = scanner.nextLine().trim().toLowerCase();

			if (input.isEmpty()) {
				continue;
			}

			switch (input) {
				case "i", "insert" -> handleInsert(machine, scanner);
				case "s", "select" -> handleSelect(machine, scanner);
				case "r", "return" -> handleReturn(machine);
				case "m", "menu"   -> displayMenu(machine);
				case "h",  "history"-> showPurchaseHistory(machine);
				case "a", "admin" -> handleAdmin(machine, scanner);
				case "e", "exit"   -> {
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

			/*int change = machine.returnChange();
			if (change > 0) {
				System.out.println("Change returned: " + change + " kr");
			}*/
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

	private static void showPurchaseHistory(VendingMachine machine) {

		List<Product> history = machine.getPurchasedHistory();

		if (history.isEmpty()) {
			System.out.println("No purchases made yet.");
			return;
		}

		int total = 0;

		System.out.println("===== PURCHASE HISTORY =====");

		for (Product product : history) {
			System.out.println(product.getName()
					+ " - " + product.getPrice() + " SEK");

			total += product.getPrice();
		}

		System.out.println("Total spent: " + total + " SEK");
		System.out.println("Change available: " + machine.getBalance() + " SEK");

	}

	private static void handleAdmin(VendingMachine machine, Scanner scanner) {
		Admin admin = new Admin();

		System.out.print("Enter Admin PIN: ");
		String pin = scanner.nextLine().trim();

		if (!admin.login(pin)) {
			System.out.println("Access denied!");
			return;
		}

		boolean adminRunning = true;

		while (adminRunning) {
			System.out.println("\n===== ADMIN PANEL =====");
			System.out.println("1. Restock product");
			System.out.println("2. View products");
			System.out.println("3. View low-stock products");
			System.out.println("4. Admin exit");
			System.out.print("\nChoose option: ");

			String choice = scanner.nextLine().trim();

			switch (choice) {
				case "1" -> {
					displayAdminProducts(machine);

					try {
						System.out.print("Enter product ID: ");
						int id = Integer.parseInt(scanner.nextLine().trim());

						System.out.print("Enter restock quantity: ");
						int amount = Integer.parseInt(scanner.nextLine().trim());

						machine.restockProduct(id, amount);

						System.out.println("Product restocked successfully!");

					} catch (NumberFormatException e) {
						System.out.println("Invalid number.");
					} catch (VendingException e) {
						System.out.println(e.getMessage());
					}
				}

				case "2" -> displayAdminProducts(machine);

				case "3" -> displayLowStockProducts(machine);

				case "4" -> {
					System.out.println("Admin logged out.");
					adminRunning = false;
				}

				default -> System.out.println("Invalid admin option.");
			}
		}
	}

	private static void displayAdminProducts(VendingMachine machine) {
		System.out.println("\n-----------Products in stock-----------");

		List<Product> products = machine.getProducts();

		for (Product p : products) {
			System.out.printf("[%d] %-12s - %2d kr  (%-20s) Stock: %d%n",
					p.getId(), p.getName(), p.getPrice(),
					p.getDescription(), p.getQuantity());
		}

		System.out.println("------------------------------------");
	}

	private static void displayLowStockProducts(VendingMachine machine) {

		List<Product> lowStockProducts = machine.getLowStockProducts();

		System.out.println("\n===== LOW STOCK ALERT =====");

		if (lowStockProducts.isEmpty()) {
			System.out.println("All products are sufficiently stocked!");
			return;
		}

		System.out.println("WARNING: The following products have low stock!");

		for (Product product : lowStockProducts) {

			System.out.printf("[%d] %-12s - Stock: %d%n",
					product.getId(),
					product.getName(),
					product.getQuantity());
		}

		System.out.println("===========================\n");
	}

}


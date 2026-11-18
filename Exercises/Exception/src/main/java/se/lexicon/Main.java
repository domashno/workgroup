package se.lexicon;
import java.util.Scanner;
import java.util.InputMismatchException;

import se.lexicon.exception.InsufficientBalanceException;
import se.lexicon.exception.OutOfRangeException;
import se.lexicon.model.BankAccount;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Exercise 1
		System.out.println("Exercise 1: Basic Exception Handling");
		System.out.print("Enter the first integer: ");
		int num1 = scanner.nextInt();

		System.out.print("Enter the second integer: ");
		int num2 = scanner.nextInt();
		try {
			int result = num1 / num2;
			System.out.println("Result: " + result);
		} catch (ArithmeticException e) {
			System.out.println("Error: Cannot divide by zero.");
		}
		//Exercise 2
		System.out.println("Exercise 2: Multiple Exception Types");
		try {
			int validNumber = getNumberInRange(scanner, 1, 100);
			System.out.println("Success! You entered: " + validNumber);
		} catch (InputMismatchException e) {
			System.out.println("Error: Invalid input. Please enter a whole integer.");
		} catch (OutOfRangeException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			scanner.close();
		}
		//Exercise 3
		System.out.println("Exercise 3: Custom Exception");
		BankAccount account = new BankAccount(500.00);
		System.out.println("Initial Balance: $" + account.getBalance());

		account.deposit(200.00);

		try {
			account.withdraw(300.00);
			account.withdraw(600.00);
		} catch (InsufficientBalanceException e) {
			System.out.println("Transaction Error: " + e.getMessage());
		}
		System.out.println("Final Balance: $" + account.getBalance());
	}
	public static int getNumberInRange(Scanner scanner, int min, int max)
			throws InputMismatchException, OutOfRangeException {
		System.out.print("Enter an integer between " + min + " and " + max + ": ");
		int number = scanner.nextInt();
		if (number < min || number > max) {
			throw new OutOfRangeException("The number " + number + " is outside the range of " + min + " to " + max + ".");
		}

		return number;
	}
}


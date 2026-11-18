package se.lexicon;
import java.util.Scanner;
import java.util.InputMismatchException;

import se.lexicon.exception.InsufficientBalanceException;
import se.lexicon.exception.OutOfRangeException;
import se.lexicon.model.BankAccount;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		} finally {
			System.out.println("Exercise 5: finally Block");
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
		//Exercise 4
		System.out.println("Exercise 4: Nested try-catch Blocks");
		Path filePath = Paths.get("a-file.txt");

		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
			String line;
			int lineNumber = 0;

			while ((line = reader.readLine()) != null) {
				lineNumber++;

				try {
					int number = Integer.parseInt(line.trim());
					System.out.println("Line " + lineNumber + ": Parsed integer = " + number);
				} catch (NumberFormatException e) {
					System.out.println("Line " + lineNumber + ": Skipping invalid integer ('" + line + "').");
				}
			}

		} catch (NoSuchFileException e) {
			System.out.println("Error: The file '" + e.getFile() + "' was not found.");
		} catch (IOException e) {
			System.out.println("Error: A problem occurred while reading the file: " + e.getMessage());
		}
		//Exercise 6
		System.out.println("Exercise 6: Throwing Exceptions");
		String[] testEmails = {
				"user@example.com",
				"invalid-email.com",
				"user@.com",
				""
		};
		for (String email : testEmails) {
			try {
				validateEmail(email);
				System.out.println("Valid email: " + email);
			} catch (IllegalArgumentException e) {
				System.out.println("Validation Error for '" + email + "': " + e.getMessage());
			}
		}
	}
	public static void validateEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email address cannot be null or empty.");
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

		if (!email.matches(emailRegex)) {
			throw new IllegalArgumentException("Invalid email format. Expected format: user@domain.com");
		}
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


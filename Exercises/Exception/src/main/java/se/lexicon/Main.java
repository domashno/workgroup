package se.lexicon;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the first integer: ");
		int num1 = scanner.nextInt();

		System.out.print("Enter the second integer: ");
		int num2 = scanner.nextInt();
		//Exercise 1
		try {
			int result = num1 / num2;
			System.out.println("Result: " + result);
		} catch (ArithmeticException e) {
			System.out.println("Error: Cannot divide by zero.");
		}
		//Exercise 2
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


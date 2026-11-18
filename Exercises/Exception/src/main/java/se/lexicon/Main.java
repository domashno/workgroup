package se.lexicon;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

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
			scanner.close();
		}
	}
}


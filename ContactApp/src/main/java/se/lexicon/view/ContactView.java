package se.lexicon.view;

import se.lexicon.model.Contact;

import java.util.List;
import java.util.Scanner;

public class ContactView {
	private final Scanner scanner;

	public ContactView() {
		this.scanner = new Scanner(System.in);
	}

	public String getUserInput(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

	public void displayMenu() {
		System.out.println("\n=== Contact Management App ===");
		System.out.println("1. Add New Contact");
		System.out.println("2. View All Contacts");
		System.out.println("3. Search Contact by Name");
		System.out.println("4. Exit");
	}

	public void displayContacts(List<Contact> contacts) {
		if (contacts.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}
		System.out.println("\n--- Saved Contacts ---");
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void displayError(String message) {
		System.err.println("Error: " + message);
	}
}
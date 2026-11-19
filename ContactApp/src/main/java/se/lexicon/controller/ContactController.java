package se.lexicon.controller;

import se.lexicon.data.ContactDAO;
import se.lexicon.exception.ExceptionHandler;
import se.lexicon.model.Contact;
import se.lexicon.view.ContactView;


public class ContactController {
    private final ContactDAO contactDAO;
    private final ContactView contactView;

    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    public void run() {
        contactView.displayMessage("Welcome.");

        boolean running = true;

        while (running) {
            try {
                contactView.displayMenu();

                String choice = contactView.getUserInput("Choose an option: ");

                switch (choice.trim()) {

                 /* TODO*/
                }

            } catch (Exception e) {
                ExceptionHandler.handle(e);
                contactView.displayError(ExceptionHandler.handle(e)
                );
            }
        }
    }

    private void addContact() throws Exception {
        String name = contactView.getUserInput("Enter contact name: ");
        String phoneNumber = contactView.getUserInput("Enter phone number (10 digits): ");

        Contact contact = new Contact(name, phoneNumber);

        contactDAO.save(contact);

        contactView.displayMessage("Contact saved successfully!");
    }

    private void searchContact() throws Exception {
        String name = contactView.getUserInput("Enter name to search: ");

        Contact contact = contactDAO.findByName(name);

        if (contact != null) {
            contactView.displayMessage("Contact found:\n" + contact);
        } else {
            contactView.displayMessage("No contact found with name: " + name);
        }
    }

    private void displayContacts() throws Exception {
        contactView.displayContacts(contactDAO.findAll());
    }

}

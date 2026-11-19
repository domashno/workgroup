package se.lexicon;

import se.lexicon.controller.ContactController;
import se.lexicon.data.ContactDAO;
import se.lexicon.data.FileContactDAOImpl;
import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.ExceptionHandler;
import se.lexicon.view.ContactView;

import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		try {
			ContactDAO contactDAO = new FileContactDAOImpl(Paths.get("data/contacts.txt"));
			ContactView contactView = new ContactView();
			ContactController controller = new ContactController(contactDAO, contactView);

			controller.run();
		} catch (ContactStorageException e) {
			System.err.println(ExceptionHandler.handle(e));
		}
	}
}

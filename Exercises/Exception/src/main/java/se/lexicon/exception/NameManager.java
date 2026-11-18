package se.lexicon.exception;

import java.util.ArrayList;
import java.util.List;

public class NameManager {
	private List<String> names = new ArrayList<>();

	public void addName(String name) throws DuplicateNameException {
		for (String existingName : names) {
			if (existingName.equalsIgnoreCase(name)) {
				throw new DuplicateNameException("The name '" + name + "' already exists in the list.");
			}
		}
		names.add(name);
		System.out.println("Added: " + name);
	}

	public String findName(String name) throws NameNotFoundException {
		for (String existingName : names) {
			if (existingName.equalsIgnoreCase(name)) {
				return existingName;
			}
		}
		throw new NameNotFoundException("The name '" + name + "' was not found in the list.");
	}
}

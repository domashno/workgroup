
package se.lexicon.exception;

public class ExceptionHandler {

    public static String handle(Exception e) {

        if (e instanceof DuplicateContactException) {
            return "Duplicate contact: " + e.getMessage();
        }

        if (e instanceof ContactStorageException) {
            return "Storage error: " + e.getMessage();
        }

        if (e instanceof IllegalArgumentException) {
            return "Invalid input: " + e.getMessage();
        }

        return "Unexpected error: " + e.getMessage();
    }
}


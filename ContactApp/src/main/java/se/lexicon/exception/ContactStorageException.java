
package se.lexicon.exception;

public class ContactStorageException extends Exception {

    public ContactStorageException(String message) {
        super(message);
    }

    public ContactStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

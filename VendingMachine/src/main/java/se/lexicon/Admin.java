package se.lexicon;

public class Admin {

    private static final String ADMIN_PIN = "1234";

    public boolean login(String pin) {
        return ADMIN_PIN.equals(pin);
    }
}
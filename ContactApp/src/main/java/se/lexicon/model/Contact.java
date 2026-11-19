package se.lexicon.model;

public class Contact {
    private String name;
    private String phoneNumber;
    public Contact(String name, String phoneNumber) {
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null ||
                !phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException(
                    "Phone number must contain exactly 10 digits"
            );
        }
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Name: " + name +
                ", Phone: " + phoneNumber;
    }
}

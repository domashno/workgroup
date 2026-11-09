
package se.lexicon;

public abstract class Product {

    // Fields
    private int id;
    private String name;
    private int price;
    private int quantity;

    // Constructor
    protected Product(int id, String name, int price, int quantity) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Reduce stock
    public void reduceStock() {
        if (quantity <= 0) {
            throw new IllegalStateException("Product is out of stock");
        }

        quantity--;
    }

    // Abstract method
    public abstract String getDescription();

    public void restock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Restock amount must be positive");
        }

        quantity += amount;
    }
}

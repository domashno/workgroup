package se.lexicon;


import java.util.ArrayList;
import java.util.List;

public class LexiconVendingMachine implements VendingMachine {

    private final List<Product> products;
    private int balance;

    private static final List<Integer> VALID_COINS =
            List.of(1, 2, 5, 10);

    public LexiconVendingMachine() {
        products = new ArrayList<>();
        balance = 0;
    }

    @Override
    public boolean insertCoin(int amount) {

        if (!VALID_COINS.contains(amount)) {
            return false;
        }

        balance += amount;
        return true;
    }

    @Override
    public Product selectProduct(int id) {

        for (Product product : products) {

            if (product.getId() == id) {

                if (product.getQuantity() <= 0) {
                    throw new IllegalStateException(
                            "Product is out of stock");
                }

                if (balance < product.getPrice()) {
                    throw new IllegalStateException(
                            "Insufficient balance");
                }

                balance -= product.getPrice();
                product.reduceStock();

                return product;
            }
        }

        throw new IllegalArgumentException(
                "Product not found");
    }

    @Override
    public int returnChange() {

        int change = balance;
        balance = 0;

        return change;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public List<Product> getProducts() {
        return List.copyOf(products);
    }
}

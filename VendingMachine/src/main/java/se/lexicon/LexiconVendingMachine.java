package se.lexicon;


import java.util.ArrayList;
import java.util.List;

public class LexiconVendingMachine implements VendingMachine {

    private static final int LOW_STOCK_THRESHOLD = 3;
    private final List<Product> products;
    private List<Product> purchasedHistory = new ArrayList<>();
    private int balance;

    private static final List<Integer> VALID_COINS =
            List.of(1, 2, 5, 10, 20, 50);

    public LexiconVendingMachine() {
        products = new ArrayList<>();
        // Pre-stocking the machine as per the scenario example
        products.add(new Snack(1, "Chips", 15, 5, 130));
        products.add(new Beverage(2, "Cola", 20, 3, 330));
        products.add(new Fruit(3, "Apple", 10, 8, "Sweden"));
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
    public Product selectProduct(int id) throws VendingException{

        for (Product product : products) {

            if (product.getId() == id) {

                if (product.getQuantity() <= 0) {
                    throw new VendingException(
                            "Product is out of stock");
                }

                if (balance < product.getPrice()) {
                    throw new VendingException(
                            "Insufficient balance");
                }

                balance -= product.getPrice();
                product.reduceStock();
                purchasedHistory.add(product);

                return product;
            }
        }

        throw new VendingException(
                "Product not found");
    }
    @Override
    public List<Product> getPurchasedHistory(){
        return new ArrayList<>(purchasedHistory);
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

    @Override
    public List<Product> getLowStockProducts() {

        List<Product> lowStockProducts = new ArrayList<>();

        for (Product product : products) {

            if (product.getQuantity() < LOW_STOCK_THRESHOLD) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }

    @Override
    public void restockProduct(int id, int amount)
            throws VendingException {

        if (amount <= 0) {
            throw new VendingException("Restock amount must be positive");
        }

        for (Product product : products) {
            if (product.getId() == id) {
                product.restock(amount);

                return;
            }
        }

        throw new VendingException("Product not found");
    }
}

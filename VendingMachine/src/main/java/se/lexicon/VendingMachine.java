package se.lexicon;
import java.util.List;

public interface VendingMachine {

    boolean insertCoin(int amount);
    Product selectProduct(int id) throws VendingException;
    int returnChange();
    int getBalance();
    List<Product> getProducts();
     List<Product> getPurchasedHistory();
}

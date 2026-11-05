

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import se.lexicon.LexiconVendingMachine;
import se.lexicon.Product;
import se.lexicon.VendingException;
import se.lexicon.VendingMachine;

public class VendingMachineTest {

	private VendingMachine machine;

	@BeforeEach
	public void setUp() {
		machine = new LexiconVendingMachine();
	}

	@Test
	@DisplayName("Test Case 1 — Insert Valid Coin")
	public void testInsertValidCoin() {
		assertEquals(0, machine.getBalance());

		boolean accepted = machine.insertCoin(10);

		assertTrue(accepted);
		assertEquals(10, machine.getBalance());
	}

	@Test
	@DisplayName("Test Case 2 — Reject Invalid Coin")
	public void testRejectInvalidCoin() {
		assertEquals(0, machine.getBalance());

		boolean accepted = machine.insertCoin(7);

		assertFalse(accepted);
		assertEquals(0, machine.getBalance());
	}

	@Test
	@DisplayName("Test Case 3 — Purchase Succeeds")
	public void testPurchaseSucceeds() throws VendingException {
		Product cola = machine.getProducts().stream().filter(p -> p.getId() == 2).findFirst().orElseThrow();
		assertEquals(20, cola.getPrice());
		assertEquals(3, cola.getQuantity());
		assertEquals(0, machine.getBalance());

		// When: insertCoin(20), purchaseProduct(id)
		machine.insertCoin(20);
		Product purchased = machine.selectProduct(2);

		// Then: purchase returns the product, balance = 0, quantity = 2
		assertNotNull(purchased);
		assertEquals("Cola", purchased.getName());
		assertEquals(0, machine.getBalance());
		assertEquals(2, cola.getQuantity());
	}

	@Test
	@DisplayName("Test Case 4 — Purchase Fails — Insufficient Balance")
	public void testPurchaseFailsInsufficientBalance() {
		// Given: product price = 20 (Cola, ID 2), balance = 10
		Product cola = machine.getProducts().stream().filter(p -> p.getId() == 2).findFirst().orElseThrow();
		machine.insertCoin(10);

		int initialQuantity = cola.getQuantity();

		// When: purchaseProduct(id) -> Then: purchase fails (throws exception)
		VendingException exception = assertThrows(VendingException.class, () -> {
			machine.selectProduct(2);
		});

		// Then: verify error message, balance stays 10, quantity unchanged
		assertTrue(exception.getMessage().contains("Insufficient balance"));
		assertEquals(10, machine.getBalance());
		assertEquals(initialQuantity, cola.getQuantity());
	}

	@Test
	@DisplayName("Test Case 5 — Purchase Fails — Out of Stock")
	public void testPurchaseFailsOutOfStock() throws VendingException {
		// Given: quantity = 0 (we drain stock manually first), balance >= price
		Product cola = machine.getProducts().stream().filter(p -> p.getId() == 2).findFirst().orElseThrow();

		// Drain the 3 items out of stock
		machine.insertCoin(50);
		machine.insertCoin(10); // Balance = 60
		machine.selectProduct(2);
		machine.selectProduct(2);
		machine.selectProduct(2); // Stock is now 0, balance left is 0 kr

		assertEquals(0, cola.getQuantity());

		// Add enough funds for another transaction attempt
		machine.insertCoin(20);

		// When: purchaseProduct(id) -> Then: purchase fails
		VendingException exception = assertThrows(VendingException.class, () -> {
			machine.selectProduct(2);
		});

		// Then: balance unchanged (stays 20), quantity stays 0
		assertTrue(exception.getMessage().contains("out of stock"));
		assertEquals(20, machine.getBalance());
		assertEquals(0, cola.getQuantity());
	}

	@Test
	@DisplayName("Test Case 6 — Return Change")
	public void testReturnChange() {
		// Given: balance = 50
		machine.insertCoin(50);
		assertEquals(50, machine.getBalance());

		// When: returnChange()
		int changeReturned = machine.returnChange();

		// Then: returns 50, balance = 0
		assertEquals(50, changeReturned);
		assertEquals(0, machine.getBalance());
	}

	@Test
	@DisplayName("Test Case 7 — Product List")
	public void testProductList() {
		// Given: machine has 3 products added (setup automatically configures 3)
		// When: getProducts()
		List<Product> productList = machine.getProducts();

		// Then: list size = 3
		assertNotNull(productList);
		assertEquals(3, productList.size());
	}
}
package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("payment1", 10, 5, new Money(1000, SEK), SweBank, "Alice");
		assertTrue("Timed payment should be added", testAccount.timedPaymentExists("payment1"));

		testAccount.removeTimedPayment("payment1");
		assertFalse("Timed payment should be removed", testAccount.timedPaymentExists("payment1"));	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("payment2", 2, 1, new Money(5000, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals("Account should have been debited", new Money(9995000, SEK), testAccount.getBalance());
		testAccount.tick();
		testAccount.tick();
		assertEquals("Account should have been debited again", new Money(9990000, SEK), testAccount.getBalance());
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(5000, SEK));
		assertEquals("Balance after withdrawal incorrect", new Money(9995000, SEK), testAccount.getBalance());

		testAccount.deposit(new Money(5000, SEK));
		assertEquals("Balance after deposit incorrect", new Money(10000000, SEK), testAccount.getBalance());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals("Balance should be as expected", new Money(10000000, SEK), testAccount.getBalance());
	}
}

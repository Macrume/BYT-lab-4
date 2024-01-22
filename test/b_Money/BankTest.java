package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Alice");
		try {
			SweBank.deposit("Alice", new Money(1000, SEK));
		} catch (AccountDoesNotExistException e) {
			fail("Account should have been created and found for deposit.");
		}
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Alice"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.deposit("Unknown", new Money(1000, SEK)));
	}


	//ten test się nie powiódł funkcja withdraw używała funkcji account.deposit zamiast account.withdraw
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(5000, SEK));
		SweBank.withdraw("Ulrika", new Money(3000, SEK));
		assertEquals(Integer.valueOf(2000), SweBank.getBalance("Ulrika"));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.withdraw("Unknown", new Money(1000, SEK)));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.getBalance("Unknown"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		SweBank.transfer("Ulrika", "Bob", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Bob"));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.transfer("Unknown", "Bob", new Money(1000, SEK)));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		fail("Write test case here");
	}
}

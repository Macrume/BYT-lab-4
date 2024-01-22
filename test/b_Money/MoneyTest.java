package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("Incorrect amount for SEK100", (Integer)10000, SEK100.getAmount());
		assertEquals("Incorrect amount for EUR10", (Integer)1000, EUR10.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("Incorrect currency for SEK100", SEK, SEK100.getCurrency());
		assertEquals("Incorrect currency for EUR10", EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("Incorrect toString for SEK100", "100.0 SEK", SEK100.toString());
		assertEquals("Incorrect toString for EUR10", "10.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("Incorrect universal value for SEK100", (Integer)1500, SEK100.universalValue());
		assertEquals("Incorrect universal value for EUR10", (Integer)1500, EUR10.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue("SEK100 should equal EUR10", SEK100.equals(EUR10));
		assertFalse("SEK200 should not equal EUR10", SEK200.equals(EUR10));
	}

	@Test
	public void testAdd() {
		Money sum = SEK100.add(EUR10);
		assertEquals("Incorrect sum", (Integer)20000, sum.getAmount());
	}

	@Test
	public void testSub() {
		Money diff = SEK200.sub(EUR10);
		assertEquals("Incorrect difference", (Integer)10000, diff.getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue("SEK0 should be zero", SEK0.isZero());
		assertFalse("EUR20 should not be zero", EUR20.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals("Incorrect negation", (Integer)10000, SEKn100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertEquals("SEK100 should be equal to EUR10", 0, SEK100.compareTo(EUR10));
		assertTrue("SEK200 should be greater than EUR10", SEK200.compareTo(EUR10) > 0);
	}
}

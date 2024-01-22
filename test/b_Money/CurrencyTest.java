package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("Incorrect name for SEK", "SEK", SEK.getName());
		assertEquals("Incorrect name for DKK", "DKK", DKK.getName());
		assertEquals("Incorrect name for EUR", "EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals("Incorrect rate for SEK", 0.15, SEK.getRate(), 0.00001);
		assertEquals("Incorrect rate for DKK", 0.20, DKK.getRate(), 0.00001);
		assertEquals("Incorrect rate for EUR", 1.5, EUR.getRate(), 0.00001);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.10);
		assertEquals("Failed to set new rate for SEK", 0.10, SEK.getRate(), 0.00001);
		SEK.setRate(0.15);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals("Incorrect universal value for SEK", Integer.valueOf(150), SEK.universalValue(1000));
		assertEquals("Incorrect universal value for DKK", Integer.valueOf(200), DKK.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals("Incorrect conversion from DKK to SEK", Integer.valueOf((int)(1000 * DKK.getRate() / SEK.getRate())), SEK.valueInThisCurrency(1000, DKK));
		assertEquals("Incorrect conversion from SEK to EUR", Integer.valueOf((int)(1000 * SEK.getRate() / EUR.getRate())), EUR.valueInThisCurrency(1000, SEK));;
	}

}

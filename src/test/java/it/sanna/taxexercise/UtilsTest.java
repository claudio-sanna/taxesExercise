package it.sanna.taxexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class UtilsTest {

	@Test
	public void testRoundingFunction() {
		//Test identity case
		assertEquals(BigDecimal.valueOf(0.05), Utils.roundToZeroFive(BigDecimal.valueOf(0.05)));
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), Utils.roundToZeroFive(BigDecimal.valueOf(0.01)));
		assertEquals(BigDecimal.valueOf(0.10).setScale(2), Utils.roundToZeroFive(BigDecimal.valueOf(0.1)));
		assertEquals(BigDecimal.valueOf(2.75), Utils.roundToZeroFive(BigDecimal.valueOf(2.75)));
		
		//Test simple case
		assertEquals(BigDecimal.valueOf(0.05), Utils.roundToZeroFive(BigDecimal.valueOf(0.049)));
		assertEquals(BigDecimal.valueOf(0.0).setScale(2), Utils.roundToZeroFive(BigDecimal.valueOf(0.0122)));
		assertEquals(BigDecimal.valueOf(0.10).setScale(2), Utils.roundToZeroFive(BigDecimal.valueOf(0.111)));
		assertEquals(BigDecimal.valueOf(2.75), Utils.roundToZeroFive(BigDecimal.valueOf(2.725)));
				
		//Test complex case
		assertEquals(BigDecimal.valueOf(0.05), Utils.roundToZeroFive(BigDecimal.valueOf(0.05)));
		assertEquals(BigDecimal.valueOf(0.15), Utils.roundToZeroFive(BigDecimal.valueOf(0.125)));
		assertEquals(BigDecimal.valueOf(0.0).setScale(2), Utils.roundToZeroFive(BigDecimal.valueOf(0.01)));
		assertEquals(BigDecimal.valueOf(0.15), Utils.roundToZeroFive(BigDecimal.valueOf(0.14122)));
		assertEquals(BigDecimal.valueOf(2.75), Utils.roundToZeroFive(BigDecimal.valueOf(2.7549964)));
	}

}

package it.sanna.taxexercise.data.tax;

import java.math.BigDecimal;
import java.util.function.Predicate;

import it.sanna.taxexercise.data.Item;

/**
 * Standard tax calculate based on the formula: (price * tax) / 100
 * @author c.sanna
 *
 */
public class StandardTax implements Tax {

	private BigDecimal taxValue;
    private Predicate<Item> testFunction; 
    
	public StandardTax(BigDecimal taxValue, Predicate<Item> testFunction) {
		if (testFunction == null) {
			throw new IllegalArgumentException("Test function cannot be null");
		}
		this.taxValue = taxValue;
		this.testFunction = testFunction;
	}

	@Override
	public BigDecimal calculateTax(BigDecimal netAmount) {
		BigDecimal tax = netAmount.multiply(taxValue).divide(BigDecimal.valueOf(100.0));
		return tax;
	}
	
	@Override
	public boolean isTaxValid(Item item) {
		return testFunction.test(item);
	}
	
	public BigDecimal getTaxValue() {
		return taxValue;
	}
}

package it.sanna.taxexercise.data.tax;

import java.math.BigDecimal;

import it.sanna.taxexercise.data.Item;

/**
 * Interface for Tax calculation. Provide a methods that tests the validity of the tax
 * and how to calculate it.
 * @author c.sanna
 *
 */
public interface Tax {

	public boolean isTaxValid(Item item);
	public BigDecimal calculateTax(BigDecimal netAmount);
}

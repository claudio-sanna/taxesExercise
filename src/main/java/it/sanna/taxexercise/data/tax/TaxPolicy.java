package it.sanna.taxexercise.data.tax;

import java.math.BigDecimal;

import it.sanna.taxexercise.data.Item;

/**
 * Interface used to extract gross amount based on item and netPrice
 * @author c.sanna
 *
 */
public interface TaxPolicy {

	public BigDecimal calculateGrossAmout(Item item, BigDecimal netPrice);
	
}

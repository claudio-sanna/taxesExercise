package it.sanna.taxexercise.data.tax;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import it.sanna.taxexercise.data.Item;

/**
 * Implementation of tax policy. This tax policy calculate taxes as a sum of different
 * single taxes applyed to the original net price. An optional rounding function can be provided.
 * @author c.sanna
 *
 */
public class SimpleRoundedTaxPolicy implements TaxPolicy {

	private List<Tax> taxes;
	private Function<BigDecimal, BigDecimal> roundFunction;

	public SimpleRoundedTaxPolicy() {
		taxes = new LinkedList<>();
	}

	public SimpleRoundedTaxPolicy(Function<BigDecimal, BigDecimal> roundFunction) {
		this.roundFunction = roundFunction;
		taxes = new LinkedList<>();
	}

	public void addTax(Tax tax) {
		taxes.add(tax);
	}

	/**
	 * Calculate gross amount from price amount using the provided tax services
	 * and an optional round function.
	 * @param item - item 
	 * @param netPrice - item net price 
	 * @return the gross amount based on the item characteristics and its net price
	 */
	@Override
	public BigDecimal calculateGrossAmout(Item item, BigDecimal netPrice) {
		BigDecimal grossAmount = netPrice;
		BigDecimal taxAmount = BigDecimal.ZERO;
		for (Tax tax : taxes) {
			if (tax.isTaxValid(item)) {
				taxAmount = taxAmount.add(tax.calculateTax(netPrice));
			}
		}

		if (roundFunction != null && !taxAmount.equals(BigDecimal.ZERO)) {
			grossAmount = grossAmount.add(roundFunction.apply(taxAmount));
		}
		return grossAmount;
	}

}

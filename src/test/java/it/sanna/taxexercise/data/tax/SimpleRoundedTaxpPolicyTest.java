package it.sanna.taxexercise.data.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.sanna.taxexercise.Utils;
import it.sanna.taxexercise.data.Item;
import it.sanna.taxexercise.data.ItemType;

public class SimpleRoundedTaxpPolicyTest {

	@Test
	public void testPolicy()  {
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		
		Item item = new Item();
		item.setImported(false);
		item.setType(ItemType.BOOK);
		assertEquals(BigDecimal.valueOf(10.15), 
				taxPolicy.calculateGrossAmout(item, BigDecimal.valueOf(10.15)));

		item.setImported(true);
		item.setType(ItemType.BOOK);
		assertEquals(BigDecimal.valueOf(10.65), 
				taxPolicy.calculateGrossAmout(item, BigDecimal.valueOf(10.15)));

		
		item.setImported(false);
		item.setType(ItemType.OTHER);
		assertEquals(BigDecimal.valueOf(11.15), 
				taxPolicy.calculateGrossAmout(item, BigDecimal.valueOf(10.15)));

		
		item.setImported(true);
		item.setType(ItemType.OTHER);
		assertEquals(BigDecimal.valueOf(11.65), 
				taxPolicy.calculateGrossAmout(item, BigDecimal.valueOf(10.15)));
	}
}

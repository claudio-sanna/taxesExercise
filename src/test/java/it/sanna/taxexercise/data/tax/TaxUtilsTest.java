package it.sanna.taxexercise.data.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.sanna.taxexercise.data.Item;
import it.sanna.taxexercise.data.ItemType;


public class TaxUtilsTest {

	@Test
	public void testBasicSalesTax() {
		Item item = new Item();
		item.setType(ItemType.BOOK);
		assertFalse(TaxUtils.BASIC_SALE_TAX.isTaxValid(item));
		item.setType(ItemType.FOOD);
		assertFalse(TaxUtils.BASIC_SALE_TAX.isTaxValid(item));
		item.setType(ItemType.MEDICAL);
		assertFalse(TaxUtils.BASIC_SALE_TAX.isTaxValid(item));
		item.setType(ItemType.OTHER);
		assertTrue(TaxUtils.BASIC_SALE_TAX.isTaxValid(item));
		
		assertEquals(BigDecimal.valueOf(1), TaxUtils.BASIC_SALE_TAX.calculateTax(BigDecimal.valueOf(10)));
	}

	@Test
	public void testImportTax() {
		Item item = new Item();
		item.setImported(true);
		assertTrue(TaxUtils.IMPORT_TAX.isTaxValid(item));
		
		item.setImported(false);
		assertFalse(TaxUtils.IMPORT_TAX.isTaxValid(item));

		assertEquals(BigDecimal.valueOf(0.5), TaxUtils.IMPORT_TAX.calculateTax(BigDecimal.valueOf(10)));
	}
	
}

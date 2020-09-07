package it.sanna.taxexercise.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.sanna.taxexercise.Utils;
import it.sanna.taxexercise.data.tax.SimpleRoundedTaxPolicy;
import it.sanna.taxexercise.data.tax.TaxUtils;

public class ReceiptTest {

	private static Receipt receipt;
	
	@Test
	public void testItemAdd() {
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		receipt = new Receipt(taxPolicy);
		assertEquals(0, receipt.getItems().size());
		Item item = new Item();
		item.setName("item1");
		item.setImported(false);
		item.setType(ItemType.OTHER);
		receipt.addItem(item, BigDecimal.valueOf(10.00), 1);
		assertEquals(1, receipt.getItems().size());
		assertEquals(BigDecimal.valueOf(11.00).setScale(2), 
				receipt.getItems().get(0).getUnitGrossPrice());
		assertEquals(1, 
				receipt.getItems().get(0).getQuantity());		
	}
	
	@Test
	public void testTotalQuantity() {
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		receipt = new Receipt(taxPolicy);
		assertEquals(0, receipt.getItems().size());
		Item item = new Item();
		item.setName("item1");
		item.setImported(false);
		item.setType(ItemType.OTHER);
		receipt.addItem(item, BigDecimal.valueOf(10.00), 1);
		assertEquals(BigDecimal.valueOf(11.00).setScale(2), receipt.getTotalGrossPrice());
		assertEquals(BigDecimal.valueOf(10.0), receipt.getTotalNetPrice());
		assertEquals(BigDecimal.valueOf(1.0).setScale(2), receipt.getTaxes());
	}
	
}

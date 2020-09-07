package it.sanna.taxexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.sanna.taxexercise.data.Receipt;
import it.sanna.taxexercise.data.tax.SimpleRoundedTaxPolicy;
import it.sanna.taxexercise.data.tax.TaxUtils;
import it.sanna.taxexercise.service.ReceiptParser;
public class ExerciseOutputTest {

	@Test
	public void testOne()  {
		StringBuilder exampleReceipt = new StringBuilder("");
		exampleReceipt.append("1, book, 12.49\n");
		exampleReceipt.append("1, music CD, 14.99\n");
		exampleReceipt.append("1, chocolate bar, 0.85\n"); 		
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		
		assertEquals(BigDecimal.valueOf(12.49), 
				receipt.getItems().get(0).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(16.49), 
				receipt.getItems().get(1).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(0.85), 
				receipt.getItems().get(2).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(1.50).doubleValue(), 
				receipt.getTaxes().doubleValue(), 0.0001);
		assertEquals(BigDecimal.valueOf(29.83), 
				receipt.getTotalGrossPrice());		
	}
	
	@Test
	public void testTwo() {
		StringBuilder exampleReceipt = new StringBuilder("");
		exampleReceipt.append("1, imported box of chocolates, 10.00\n");
		exampleReceipt.append("1, imported bottle of perfume, 47.50\n"); 
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		
		assertEquals(BigDecimal.valueOf(10.50).doubleValue(), 
				receipt.getItems().get(0).getUnitGrossPrice().doubleValue(),
				0.0001);
		assertEquals(BigDecimal.valueOf(54.65), 
				receipt.getItems().get(1).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(7.65), receipt.getTaxes());
		assertEquals(BigDecimal.valueOf(65.15), receipt.getTotalGrossPrice());	
	}
	
	@Test
	public void testThree() {
		StringBuilder exampleReceipt = new StringBuilder("");	
		exampleReceipt.append("1, imported bottle of perfume, 27.99\n"); 
		exampleReceipt.append("1, bottle of perfume, 18.99\n"); 
		exampleReceipt.append("1, packet of headache pills, 9.75\n");
		exampleReceipt.append("1, box of imported chocolates, 11.25\n");
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);	
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		
		assertEquals(BigDecimal.valueOf(32.19), 
				receipt.getItems().get(0).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(20.89), 
				receipt.getItems().get(1).getUnitGrossPrice());
		assertEquals(BigDecimal.valueOf(9.75), 
				receipt.getItems().get(2).getUnitGrossPrice());
		//Incorrect problem values? the value 0.5625 is rounded to 0.6
		assertEquals(BigDecimal.valueOf(11.80).doubleValue(), 
				receipt.getItems().get(3).getUnitGrossPrice().doubleValue(),
				0.0001);
		//Remaining 0.05 cents due to wrong rounding
		assertEquals(BigDecimal.valueOf(6.65), 
				receipt.getTaxes());
		//Remaining 0.05 cents due to wrong rounding
		assertEquals(BigDecimal.valueOf(74.63), 
				receipt.getTotalGrossPrice());	
	}
}

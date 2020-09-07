package it.sanna.taxexercise;

import it.sanna.taxexercise.data.Receipt;
import it.sanna.taxexercise.data.tax.SimpleRoundedTaxPolicy;
import it.sanna.taxexercise.data.tax.TaxUtils;
import it.sanna.taxexercise.service.ReceiptParser;
import it.sanna.taxexercise.service.ReceiptPrinter;

public class Main {
	
	public static void main(String[] args) {
		//Testing all the three provided cases; using stdout for simplicity
		//All the cases are presented as input / output
		firstTestCase();
		System.out.println("");
		System.out.println("");
		secondTestCase();
		System.out.println("");
		System.out.println("");
		thirdTestCase();
	}
	

	public static void firstTestCase() {
		System.out.println("Testing first test case");
		System.out.println("");
		StringBuilder exampleReceipt = new StringBuilder("");
		exampleReceipt.append("1, book, 12.49\n");
		exampleReceipt.append("1, music CD, 14.99\n");
		exampleReceipt.append("1, chocolate bar, 0.85\n"); 		
		System.out.println(exampleReceipt.toString());
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		String print = ReceiptPrinter.printReceipt(receipt);
		System.out.println(print);	
	}

	public static void secondTestCase() {
		System.out.println("Testing second test case");
		System.out.println("");
		StringBuilder exampleReceipt = new StringBuilder("");
		exampleReceipt.append("1, imported box of chocolates, 10.00\n");
		exampleReceipt.append("1, imported bottle of perfume, 47.50\n");
		System.out.println(exampleReceipt.toString());
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);	
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		String print = ReceiptPrinter.printReceipt(receipt);
		System.out.println(print);
	}
	
	/**
	 * NOTE: It seems that the last item in the provided output is 5cents off as
	 *  the provided tax (5%) of 11.25 results to be 0,5625 rounded to 0,55 as per rounding
	 *  rule. The solution however assumes a 0,6 tax.
	 */
	public static void thirdTestCase() {
		System.out.println("Testing third test case");
		System.out.println("");
		StringBuilder exampleReceipt = new StringBuilder("");	
		exampleReceipt.append("1, imported bottle of perfume, 27.99\n"); 
		exampleReceipt.append("1, bottle of perfume, 18.99\n"); 
		exampleReceipt.append("1, packet of headache pills, 9.75\n");
		exampleReceipt.append("1, box of imported chocolates, 11.25\n");
		System.out.println(exampleReceipt.toString());
		SimpleRoundedTaxPolicy taxPolicy = new SimpleRoundedTaxPolicy(Utils::roundToZeroFive);
		taxPolicy.addTax(TaxUtils.BASIC_SALE_TAX);
		taxPolicy.addTax(TaxUtils.IMPORT_TAX);	
		Receipt receipt = new Receipt(taxPolicy);
		ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());	
		String print = ReceiptPrinter.printReceipt(receipt);
		System.out.println(print);
	}
	
}

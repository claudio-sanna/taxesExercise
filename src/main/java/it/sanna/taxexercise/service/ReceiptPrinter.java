package it.sanna.taxexercise.service;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.sanna.taxexercise.data.Receipt;
import it.sanna.taxexercise.data.ReceiptItem;

/**
 * Print receipt to stdout
 * @author c.sanna
 *
 */
public class ReceiptPrinter {
	
	public static final int LINE_LENGTH = 50;
	
	public static String printReceipt(Receipt receipt) {
		StringBuilder receiptPrint = new StringBuilder();
		receiptPrint.append(createReceiptHeader(receipt));
		receiptPrint.append(printItemList(receipt));
		receiptPrint.append(createReceiptFooter(receipt));		
		return receiptPrint.toString();
	}
	
	public static String printItemList(Receipt receipt) {
		if (receipt.getItems() == null) {
			return "";
		}
		String itemList = receipt.getItems().stream()
				.map(ReceiptPrinter::printItem)
				.collect(Collectors.joining("\n"));
		return itemList + "\n";
	}
	
	public static StringBuilder printItem(ReceiptItem item) {
		StringBuilder itemString = new StringBuilder("- ");
		itemString.append(item.getQuantity());		
		itemString.append("       ");
		String name = item.getItem().getName();
		itemString.append(StringUtils.rightPad(name, LINE_LENGTH - itemString.length() - 5));
		itemString.append(StringUtils.leftPad(item.getUnitGrossPrice().toString(),5));
		return itemString;
	}
	
	public static StringBuilder createReceiptFooter(Receipt receipt) {
		StringBuilder header = new StringBuilder("");
		header.append(StringUtils.leftPad("", LINE_LENGTH,"-"));
		header.append("\n");
		String taxesLine = "Sales Taxes: " + receipt.getTaxes();
		taxesLine = StringUtils.leftPad(taxesLine, LINE_LENGTH);
		header.append(taxesLine);
		header.append("\n");
		String totalLine = "Total: " + receipt.getTotalGrossPrice();
		totalLine = StringUtils.leftPad(totalLine , LINE_LENGTH);
		header.append(totalLine);		
		header.append("\n");
		header.append(StringUtils.leftPad("", LINE_LENGTH,"-"));
		header.append("\n");
		return header;	
	}
	
	public static StringBuilder createReceiptHeader(Receipt receipt) {
		StringBuilder header = new StringBuilder("");
		header.append("---------------------RECEIPT----------------------");
		header.append("\n");
		header.append("Quantity Product price\n\n");
		return header;
	}
}

package it.sanna.taxexercise.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.sanna.taxexercise.data.Item;
import it.sanna.taxexercise.data.ItemType;
import it.sanna.taxexercise.data.Receipt;
import it.sanna.taxexercise.data.ReceiptItem;

public class ReceiptParserTest {

	@Test
	public void testParseItemType() {
		assertEquals(ItemType.FOOD, ReceiptParser.parseItemType("box of chocolates"));
		assertEquals(ItemType.BOOK, ReceiptParser.parseItemType("book"));
		assertEquals(ItemType.BOOK, ReceiptParser.parseItemType("books"));
		assertEquals(ItemType.FOOD, ReceiptParser.parseItemType("chocolate bar"));
		assertEquals(ItemType.MEDICAL, ReceiptParser.parseItemType("packet of headache pills"));
		assertEquals(ItemType.OTHER, ReceiptParser.parseItemType("music CD"));
		assertEquals(ItemType.OTHER, ReceiptParser.parseItemType("imported bottle of perfume"));
	}

	@Test
	public void testImportedItem() {
		assertFalse(ReceiptParser.isImportedItem("chocolate bar"));
		assertTrue(ReceiptParser.isImportedItem("imported bottle of perfume"));
		assertFalse(ReceiptParser.isImportedItem("packet of headache pills"));
		assertFalse(ReceiptParser.isImportedItem("music CD"));
		assertFalse(ReceiptParser.isImportedItem("book"));
		assertTrue(ReceiptParser.isImportedItem("imported box of chocolates"));
		assertTrue(ReceiptParser.isImportedItem("box of imported chocolates"));		 		
	}
	
	@Test
	public void testParseReceiptItem() {
		ReceiptItem receiptItem = ReceiptParser.parseReceiptItem("2, imported box of chocolates, 10.33");
		assertEquals(2, receiptItem.getQuantity());
		assertEquals(BigDecimal.valueOf(10.33), receiptItem.getUnitNetPrice());
		assertEquals("imported box of chocolates", receiptItem.getItem().getName());
		assertEquals(ItemType.FOOD, receiptItem.getItem().getType());
		assertEquals(true, receiptItem.getItem().isImported());
	}

	@Test
	public void testInvalidValuesItemReceiptParse() {
		assertThrows(IllegalArgumentException.class, 
				() -> ReceiptParser.parseReceiptItem("1, box of chocolates"));
	}

	@Test
	public void testReceiptParse() {
		Item item = ReceiptParser.parseItem(" box of chocolates ");
		assertEquals("box of chocolates", item.getName());
		assertEquals(false, item.isImported());
		assertEquals(ItemType.FOOD, item.getType());		
	}
	
	@Test
	public void testParseReceipt() {
		StringBuilder exampleReceipt = new StringBuilder("1, box of chocolates, 10.10\n");
		exampleReceipt.append("1, music CD, 7.5\n");
		exampleReceipt.append("1, book, 14.99\n");
		Receipt receipt = new Receipt();
		receipt = ReceiptParser.fillReceipt(receipt, exampleReceipt.toString());
		assertEquals(3, receipt.getItems().size());
		assertEquals(BigDecimal.valueOf(32.59), receipt.getTotalNetPrice());
		assertEquals(BigDecimal.valueOf(32.59), receipt.getTotalGrossPrice());
	}

}

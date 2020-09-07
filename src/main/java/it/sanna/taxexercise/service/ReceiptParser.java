package it.sanna.taxexercise.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.sanna.taxexercise.data.Item;
import it.sanna.taxexercise.data.ItemType;
import it.sanna.taxexercise.data.Receipt;
import it.sanna.taxexercise.data.ReceiptItem;

/**
 * Class for parsing receipt. For simplicity each receipt is made by one item
 * per row divided in quantity / item name / price each comma separated
 * 
 * @author c.sanna
 *
 */
public class ReceiptParser {

	public static final String IMPORTED_ITEM_REGEX = "imported";

	/**
	 * Parse receipt and populate receipt from string
	 * @param receiptText
	 * @return the complete receipt
	 */
	public static Receipt fillReceipt(Receipt receipt, String receiptText) {
		String[] itemsText = receiptText.split("\n");
		for (String itemText : itemsText) {
			ReceiptItem item = parseReceiptItem(itemText);
			receipt.addItem(item.getItem(), item.getUnitNetPrice(), item.getQuantity());
		}
		return receipt;
	}

	/**
	 * parse receipt data from string
	 * @param itemReceiptText
	 * @return a receipt item
	 */
	public static ReceiptItem parseReceiptItem(String itemReceiptText) {
		String[] itemComponents = itemReceiptText.split(",");

		if (itemComponents.length != 3) {
			throw new IllegalArgumentException("Invalid item provided: " + itemReceiptText);
		}

		// Get quantity
		Integer quantity = Integer.parseInt(itemComponents[0]);

		//Get item
		Item item = parseItem(itemComponents[1]);

		// Get price
		Double price = Double.parseDouble(itemComponents[2]);
		
		//Create receipt item
		ReceiptItem receiptItem = new ReceiptItem(item, BigDecimal.valueOf(price), quantity);
		return receiptItem;
	}
	
	/**
	 * Parse item from string
	 * @param itemText
	 * @return parsed item
	 */
	public static Item parseItem(String itemText) {
		if (itemText == null) {
			throw new NullPointerException();
		}
		
		// Get item data
		Item item = new Item();
		item.setName(itemText.trim());
		// Simplified string parser base on word match, assumes that input are
		// standardized
		ItemType itemType = parseItemType(itemText);
		item.setType(itemType);

		// Check if is imported item
		if (isImportedItem(itemText)) {
			item.setImported(true);
		}
		
		return item;
	}

	/**
	 * Extract item type from a string representing an Item. The string if checked
	 * against a series of keywords for each itemType; if no match is found
	 * ItemType.Other is returned.
	 * 
	 * @param item to be checked
	 * @return the itemType of the provided item or OTHER if no match.
	 * 
	 */
	protected static ItemType parseItemType(String item) {
		for (ItemType itemType : ItemType.values()) {
			// Skip other value
			if (itemType.equals(ItemType.OTHER)) {
				continue;
			}

			Pattern p = Pattern.compile(itemType.getKeywords());
			Matcher m = p.matcher(item);
			if (m.find()) {
				return itemType;
			}
		}
		return ItemType.OTHER;
	}

	/**
	 * Identify if the item is an imported item
	 * 
	 * @param item to be checked
	 * @return true if the item contains the "imported" keyword, otherwise false.
	 */
	protected static boolean isImportedItem(String item) {
		return item.contains("imported");
	}
}

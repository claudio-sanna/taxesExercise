package it.sanna.taxexercise.data.tax;

import static it.sanna.taxexercise.data.ItemType.BOOK;
import static it.sanna.taxexercise.data.ItemType.FOOD;
import static it.sanna.taxexercise.data.ItemType.MEDICAL;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import it.sanna.taxexercise.data.Item;
import it.sanna.taxexercise.data.ItemType;

public class TaxUtils {

	public static final List<ItemType> FREE_TAX_ITEM_TYPE = Arrays.asList(BOOK,FOOD,MEDICAL);
	public static final BigDecimal BASIC_SALE_TAX_VALUE = BigDecimal.valueOf(10); 
	public static final BigDecimal IMPORT_TAX_VALUE = BigDecimal.valueOf(5);
	
	public static final Tax BASIC_SALE_TAX  = new StandardTax(BASIC_SALE_TAX_VALUE, TaxUtils::isFreeTaxItem);
	public static final Tax IMPORT_TAX  = new StandardTax(IMPORT_TAX_VALUE, TaxUtils::isItemImported);
	
	public static boolean isFreeTaxItem(Item item) {
		return !FREE_TAX_ITEM_TYPE.contains(item.getType());
	}
	
	public static boolean isItemImported(Item item) {
		return item.isImported();
	}
}

package it.sanna.taxexercise.data;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class used as a wrapper to enhance an Item with informations tied to a Receipt 
 * @author c.sanna
 *
 */
public class ReceiptItem {

	private BigDecimal unitNetPrice;
	private BigDecimal unitGrossPrice;
	private int quantity;
	private Item item;

	public ReceiptItem(Item item, BigDecimal netPrice, int quantity) {
		this.item = item;
		this.unitNetPrice = netPrice;
		this.quantity = quantity;
	}
	
	public BigDecimal getUnitNetPrice() {
		return unitNetPrice;
	}
	
	public void setUnitNetPrice(BigDecimal unitNetPrice) {
		this.unitNetPrice = unitNetPrice;
	}
	public BigDecimal getUnitGrossPrice() {
		return unitGrossPrice;
	}
	public void setUnitGrossPrice(BigDecimal unitGrossPrice) {
		this.unitGrossPrice = unitGrossPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

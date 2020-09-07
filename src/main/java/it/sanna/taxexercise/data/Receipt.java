package it.sanna.taxexercise.data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

import it.sanna.taxexercise.data.tax.TaxPolicy;

/**
 * Class that collects different items as weel as a tax policy to apply to each one
 * @author c.sanna
 *
 */
public class Receipt {

	private List<ReceiptItem> items;
	private TaxPolicy taxPolicy;

	public Receipt() {
		items = new LinkedList<>();
	}
	
	public Receipt(TaxPolicy taxPolicy) {
		items = new LinkedList<>();
		this.taxPolicy = taxPolicy;
	}

	/**
	 * Add new tax policy and recalculate each item gross price
	 * @param taxPolicy to set
	 */
	public void addTaxPolicy(TaxPolicy taxPolicy) {
		this.taxPolicy = taxPolicy;
		items.forEach(item -> {
			item.setUnitGrossPrice(taxPolicy.calculateGrossAmout(item.getItem(), item.getUnitNetPrice()));
		});
	}

	/**
	 * Add item to receipt and calculate its gross price due to @taxPolicy provided.
	 * 
	 * @param item
	 */
	public void addItem(Item item, BigDecimal netPrice, int quantity) {
		if (item == null) {
			throw new NullPointerException("Invalid item added to receipt: null");
		}

		//Calculate gross price if needed
		ReceiptItem receiptItem = new ReceiptItem(item, netPrice, quantity);
		BigDecimal grossPrice = netPrice;
		if (taxPolicy != null) {
			grossPrice = taxPolicy.calculateGrossAmout(item, netPrice);
		}

		receiptItem.setUnitGrossPrice(grossPrice);
		items.add(receiptItem);
	}

	public BigDecimal getTotalNetPrice() {
		return items.stream().map((item) -> {
			return item.getUnitNetPrice()
					.multiply(BigDecimal.valueOf(item.getQuantity()));
		}).collect(Collectors.reducing(BigDecimal.ZERO, (a, b) -> a.add(b)));
	}

	public BigDecimal getTaxes() {
		return getTotalGrossPrice().subtract(getTotalNetPrice());
	}

	public BigDecimal getTotalGrossPrice() {
		return items.stream().map((item) -> {
			return item.getUnitGrossPrice()
					.multiply(BigDecimal.valueOf(item.getQuantity()));
		}).collect(Collectors.reducing(BigDecimal.ZERO, (a, b) -> a.add(b)));
	}

	public List<ReceiptItem> getItems() {
		return items;
	}

	public void setItems(List<ReceiptItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

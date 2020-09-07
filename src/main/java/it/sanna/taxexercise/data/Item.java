package it.sanna.taxexercise.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing an actual item with property that defines its nature
 * @author c.sanna
 *
 */
public class Item {

	private String name;
	private boolean isImported;
	private ItemType type;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public ItemType getType() {
		return type;
	}
	
	public void setType(ItemType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

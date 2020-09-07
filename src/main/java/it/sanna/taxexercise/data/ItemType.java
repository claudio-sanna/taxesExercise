package it.sanna.taxexercise.data;

public enum ItemType {
	FOOD("chocolate"),
	BOOK("book"),
	MEDICAL("headache pills"),
	OTHER("");

	private final String keywords;
	
	private ItemType(String keywords) {
		this.keywords = keywords;
	}
	
	public String getKeywords() {
		return this.keywords;
	}
}

package net.petrusha.homebudget.model;

public abstract class ValuebleItem extends BudgetItem {
	
	private static final long serialVersionUID = 3164902623122623366L;
	
	private Price price = new Price();

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}

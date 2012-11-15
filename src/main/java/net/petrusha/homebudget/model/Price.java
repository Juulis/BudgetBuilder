package net.petrusha.homebudget.model;

import java.io.Serializable;


public class Price implements Serializable {

	private static final long serialVersionUID = -178982312580366993L;

	private Double ammount = 0.0;
	private String currencyCode = "";
	
	public Price() {
		
	}
	
	public Price(Double ammount, String currencyCode) {
		super();
		this.ammount = ammount;
		this.currencyCode = currencyCode;
	}

	public Double getAmmount() {
		return ammount;
	}
	
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public Price plus(Price price) {
		String currencyCode = getCurrencyCode(price);
		return new Price(ammount + price.getAmmount(), currencyCode);
	}

	public Price minus(Price price) {
		String currencyCode = getCurrencyCode(price);
		return new Price(ammount - price.getAmmount(), currencyCode);
	}
	
	protected String getCurrencyCode(Price price) {
		String currencyCode = null;
		if (this.currencyCode.isEmpty()) {
			currencyCode = price.getCurrencyCode();
		} else {
			currencyCode = this.currencyCode;
		}
		if (!currencyCode.equals(price.getCurrencyCode()) && price.getAmmount() > 0) {
			throw new IllegalArgumentException("Currencies must be equals: " + this + "+" +price);
		}
		return currencyCode;
	}
	
	@Override
	public String toString() {
		return "Price [ammount=" + ammount + ", currencyCode=" + currencyCode
				+ "]";
	}
	
	
	
}

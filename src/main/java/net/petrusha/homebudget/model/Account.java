package net.petrusha.homebudget.model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private static final long serialVersionUID = -5310269317976933390L;
	
	public static enum AccountType {
		CASH, CREDIT, BANK, OTHER
	}
	
	private String id;
	private String title;
	private double ammount;
	private String currency;
	private AccountType type;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getAmmount() {
		return ammount;
	}
	
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
		
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AccountType getType() {
		return type;
	}
	
	public void setType(AccountType type) {
		this.type = type;
	}

	public int compareTo(Account o) {
		return this.getTitle().compareTo(o.getTitle());
	}
	
}
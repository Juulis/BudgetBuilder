package net.petrusha.homebudget.gwt.budget.client.model;

import java.io.Serializable;
import java.util.Date;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = -8055094764402135518L;

	private String category;
	private String title;
	private String description;
	private Date date;
	private String ammount;
	private String currencyCode;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	
	
}
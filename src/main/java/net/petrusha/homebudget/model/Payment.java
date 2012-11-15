package net.petrusha.homebudget.model;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = -5289813778001185538L;

	private Date paymentDate;
	
	private Price price = new Price();

	public Payment() {
		
	}
	
	public Payment(Price price, Date date) {
		this(price);
		this.paymentDate = date;
		
	}
	
	public Payment(Price price) {
		this.price = price;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	
	
	
}
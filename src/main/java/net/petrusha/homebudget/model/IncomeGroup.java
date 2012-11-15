package net.petrusha.homebudget.model;

public class IncomeGroup extends PaymentCategory {

	private static final long serialVersionUID = -6039665652771173864L;

	private PaymentSource source;

	public PaymentSource getSource() {
		return source;
	}

	public void setSource(PaymentSource source) {
		this.source = source;
	}
	
	
}

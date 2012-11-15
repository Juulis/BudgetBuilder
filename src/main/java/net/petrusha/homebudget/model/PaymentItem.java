package net.petrusha.homebudget.model;

public class PaymentItem extends BudgetItem {

	private static final long serialVersionUID = -2121808795330055504L;

	private Payment plan = new Payment();
	private Payment fact = new Payment();
	
	protected PaymentItem(Payment plan, Payment fact) {
		this();
		this.plan = plan;
		this.fact = fact;
	}
	
    public PaymentItem() {
		super();
	}
	
	public Payment getPlan() {
		return plan;
	}
	public void setPlan(Payment plan) {
		this.plan = plan;
	}
	public Payment getFact() {
		return fact;
	}
	public void setFact(Payment fact) {
		this.fact = fact;
	}
	
	public PaymentItem sum(PaymentItem item) {
		
		Price planPrice = this.plan.getPrice().plus(item.getPlan().getPrice());
		Price factPrice = this.fact.getPrice().plus(item.getFact().getPrice());
		
		return new PaymentItem(new Payment(planPrice), new Payment(factPrice));
	}
	
}

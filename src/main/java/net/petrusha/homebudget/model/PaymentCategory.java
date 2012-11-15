package net.petrusha.homebudget.model;


import java.util.LinkedList;
import java.util.List;

public class PaymentCategory extends BudgetItem {

	private static final long serialVersionUID = 669512416027981738L;
	
	private List<PaymentItem> items = new LinkedList<PaymentItem>();

	public PaymentCategory() {
		super();
	}
	
	public PaymentCategory(String name) {
		this();
		setName(name);
	}
	
	public List<PaymentItem> getItems() {
		return items;
	}

	public void setItems(List<PaymentItem> items) {
		this.items = items;
	}
	
	public void addItem(PaymentItem item) {
		items.add(item);
	}
	
}

package net.petrusha.homebudget.model;

import java.util.List;


public class BudgetCalculator {
	private static final BudgetCalculator instance = new BudgetCalculator();
	private BudgetCalculator() {
		
	}
	
	public static BudgetCalculator getInstance() {
		return instance;
	}
	
	public PaymentItem getSumOfCategories(List<PaymentCategory> categories) {
		PaymentItem sumItem = new PaymentItem();
		for(PaymentCategory category: categories) {
			sumItem = sumItem.sum(getSumOfCategory(category));
		}
		
		return sumItem;
	}
	
	public PaymentItem getSumOfCategory(PaymentCategory category) {
		PaymentItem sumItem = new PaymentItem();
		
		List<PaymentItem> items = category.getItems();
		for (PaymentItem item : items) {
			sumItem = sumItem.sum(item);
		}
		
		return sumItem;
	}
	
}

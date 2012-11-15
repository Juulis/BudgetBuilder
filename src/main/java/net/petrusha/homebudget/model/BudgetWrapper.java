package net.petrusha.homebudget.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BudgetWrapper {
	
	private BudgetCalculator calculator = BudgetCalculator.getInstance();
	
	private Budget budget;
	
	
	public BudgetWrapper(Budget budget) {
		this.budget = budget;
	}
	
	public Budget getBudget() {
		return budget;
	}
	
	public PaymentCategory getIncomesCategory(String name) {
		ArrayList<PaymentCategory> categories = budget.getIncomesCategories();
		return getCategoryByName(name, categories);
	}

	
	public PaymentCategory getExpensesCategory(String name) {
		ArrayList<PaymentCategory> categories = budget.getExpensesCategories();
		return getCategoryByName(name, categories);
	}
	
	protected PaymentCategory getCategoryByName(String name, List<PaymentCategory> categories) {
		PaymentCategory category = null;
		for (PaymentCategory paymentCategory : categories) {
			if (paymentCategory.getName().equalsIgnoreCase(name)) {
				category = paymentCategory;
				break;
			}
		}
		
		if (category == null) {
			category = new PaymentCategory(name);
			categories.add(category);
		}
		
		return category;
	}
	
	

	public void addPlannedIncome(String categoryName, String title, String description, 
			Date date, Double ammount, String currencyCode) {
		
		PaymentCategory category = getIncomesCategory(categoryName);
		Payment payment = new Payment(new Price(ammount, currencyCode), date);
		addPlannedPayment(category, title, description, payment);
		
	}
	
	public void addPlannedExpense(String categoryName, String title, String description, 
			Date date, Double ammount, String currencyCode) {
		
		PaymentCategory category = getExpensesCategory(categoryName);
		Payment payment = new Payment(new Price(ammount, currencyCode), date);
		addPlannedPayment(category, title, description, payment);
		
	}
	
	protected void addPlannedPayment(PaymentCategory category, String title, String description, Payment payment) {
		PaymentItem	paymentItem = newPaymentItem(title, description);
		paymentItem.setPlan(payment);
		category.addItem(paymentItem);
	}
	
	protected PaymentItem newPaymentItem(String title, String description) {
		PaymentItem paymentItem = new PaymentItem();
		paymentItem.setName(title);
		paymentItem.setDescription(description);
		
		return paymentItem;
	}

	public String getName() {
		String name = budget.getName();
		if (name == null || name.isEmpty()) {
			name = "Budget";
		}
		return name;
	}
	
	public void setName(String name) {
		budget.setName(name);
	}
	
	public void setStartDate(Date startDate) {
		budget.setStartDate(startDate);
	}

	public Date getStartDate() {
		Date startDate = budget.getStartDate();
		if (startDate == null) {
			startDate = new Date();
		}
		return startDate;
	}
	
	public void setEndDate(Date endDate) {
		budget.setEndDate(endDate);
	}

	public Date getEndDate() {
		Date endDate = budget.getEndDate();
		if (endDate == null) {
			endDate = new Date();
		}
		return endDate;
	}
	
	public void setBaseCurrency(String baseCurrency) {
		budget.setBaseCurrency(baseCurrency);
	}

	public String getBaseCurrency() {
		String currensyCode = budget.getBaseCurrency();
		if (currensyCode == null || currensyCode.isEmpty()) {
			currensyCode = "USD";
		}
		return currensyCode;
	}
	
	public Price getPlannedIncomesAmmount() {
		return budget.getPlannedIcomes();
	}
	
	public Price getPlannedExpensesAmmount() {
		return budget.getPlannedExpenses();
	}
	
	public Price getPlannedTotalAmmount() {
		return budget.getPlannedTotal();
	}
	
	
	public Price getRealIncomesAmmount() {
		return budget.getRealIcomes();
	}
	
	public Price getRealExpensesAmmount() {
		return budget.getRealExpenses();
	}
	
	public Price getRealTotalAmmount() {
		return budget.getRealTotal();
	}
	
	public Price getMarginIncomesAmmount() {
		return budget.getMarginIcomes();
	}
	
	public Price getMarginExpensesAmmount() {
		return budget.getMarginExpenses();
	}
	
	public Price getMarginTotalAmmount() {
		return budget.getMarginTotal();
	}

	
	public ArrayList<PaymentCategory> getIncomesCategories() {
		return new ArrayList<PaymentCategory>(budget.getIncomesCategories());
	}
	
	public ArrayList<PaymentCategory> getExpensesCategories() {
		return new ArrayList<PaymentCategory>(budget.getExpensesCategories());
	}

	
}

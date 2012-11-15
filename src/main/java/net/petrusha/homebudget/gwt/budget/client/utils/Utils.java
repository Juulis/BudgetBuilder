package net.petrusha.homebudget.gwt.budget.client.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.petrusha.homebudget.model.Budget;
import net.petrusha.homebudget.model.Payment;
import net.petrusha.homebudget.model.PaymentCategory;
import net.petrusha.homebudget.model.PaymentItem;
import net.petrusha.homebudget.model.Price;

public class Utils {

	public static Budget getTestBudget() {
		Budget budget = new Budget();
		
		budget.setStartDate(new Date());
		budget.setEndDate(new Date());
		
		budget.setName("Test Budget");
		budget.setDescription("Test Budget Description");
		
		budget.setIncomesCategories(getTestPaymentCategories("Income Category"));
		budget.setExpensesCategories(getTestPaymentCategories("Expenses Category"));
		
		return budget;
	}

	private static ArrayList<PaymentCategory> getTestPaymentCategories(String baseName) {
		ArrayList<PaymentCategory> incomes = new ArrayList<PaymentCategory>();
		for (int i = 0 ; i < 3 ; i++) {
			PaymentCategory paymentCategory = new PaymentCategory();
			paymentCategory.setName(baseName + " " + i);
			paymentCategory.setDescription(baseName + " Description " + i);
			paymentCategory.setItems(getPaymentItems(paymentCategory.getName()));
			incomes.add(paymentCategory);
		}
		
		return incomes;
	}

	private static List<PaymentItem> getPaymentItems(String gropeName) {
		ArrayList<PaymentItem> items = new ArrayList<PaymentItem>();
		
		for (int i = 0 ; i < 3 ; i++) {
			PaymentItem item = new PaymentItem();
			items.add(item);
			
			item.setName("Item " + i);
			item.setDescription("Item Description " + i);
			Payment plan = new Payment();
			plan.setPaymentDate(new Date());
			plan.setPrice(new Price(i*1000.0, "UAH"));
			item.setPlan(plan);
			
			if (i < 1) {
				Payment fact = new Payment();
				fact.setPaymentDate(new Date());
				fact.setPrice(new Price(i*1000.0, "UAH"));
				item.setFact(fact);
			}
		}
		return items;
	}
	
}

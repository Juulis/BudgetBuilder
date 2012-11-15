package net.petrusha.homebudget.model;

import java.util.ArrayList;
import java.util.Date;

public class Budget extends BudgetItem {

	private static final long serialVersionUID = -4089282305379629880L;
	
	private String baseCurrency = "UAH";
	
	private Date startDate;
	private Date endDate;
	
	private ArrayList<PaymentCategory> incomeCategories = new ArrayList<PaymentCategory>();
	private ArrayList<PaymentCategory> expensesCategories = new ArrayList<PaymentCategory>();
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public ArrayList<PaymentCategory> getIncomesCategories() {
		return incomeCategories;
	}
	public void setIncomesCategories(ArrayList<PaymentCategory> incomeCategories) {
		this.incomeCategories.clear();
		this.incomeCategories.addAll(incomeCategories);
	}
	public ArrayList<PaymentCategory> getExpensesCategories() {
		return expensesCategories;
	}
	public void setExpensesCategories(ArrayList<PaymentCategory> expensesCategories) {
		this.expensesCategories.clear();
		this.expensesCategories.addAll(expensesCategories);
	}
	
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
	public Price getPlannedIcomes() {
		PaymentItem sumOfCategories = BudgetCalculator.getInstance().getSumOfCategories(incomeCategories);
		return sumOfCategories.getPlan().getPrice();
	}
	public Price getPlannedExpenses() {
		PaymentItem sumOfCategories = BudgetCalculator.getInstance().getSumOfCategories(expensesCategories);
		return sumOfCategories.getPlan().getPrice();
	}
	
	public Price getPlannedTotal() {
		Price incomes = getPlannedIcomes();
		return incomes.minus(getPlannedExpenses());
	}
	public Price getRealIcomes() {
		PaymentItem sumOfCategories = BudgetCalculator.getInstance().getSumOfCategories(incomeCategories);
		return sumOfCategories.getFact().getPrice();
	}
	public Price getRealExpenses() {
		PaymentItem sumOfCategories = BudgetCalculator.getInstance().getSumOfCategories(expensesCategories);
		return sumOfCategories.getFact().getPrice();
	}
	public Price getRealTotal() {
		Price incomes = getRealIcomes();
		return incomes.minus(getRealExpenses());
	}
	
	public Price getMarginIcomes() {
		Price planned = getPlannedIcomes();
		return planned.minus(getRealIcomes());
	}
	public Price getMarginExpenses() {
		Price planned = getPlannedExpenses();
		return planned.minus(getRealExpenses());
	}
	public Price getMarginTotal() {
		Price planned = getPlannedTotal();
		return planned.minus(getRealTotal());
	}
	
}

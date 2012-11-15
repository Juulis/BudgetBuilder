package net.petrusha.homebudget.gwt.budget.client.view.impl;

import net.petrusha.homebudget.gwt.budget.client.view.IBudgetView;
import net.petrusha.homebudget.gwt.main.client.view.impl.ReverseCompositeView;
import net.petrusha.homebudget.model.Budget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BudgetView extends ReverseCompositeView<IBudgetView.IBudgetPresenter> implements IBudgetView {

	interface BudgetViewUiBinder extends UiBinder<Widget, BudgetView> {}
	private static BudgetViewUiBinder uiBinder = GWT.create(BudgetViewUiBinder.class);
	
	
	@UiField
	HorizontalPanel header;
	
	@UiField
	Label startDate, endDate;
	
	@UiField
	VerticalPanel budgetTable;

	@UiField
	SimplePanel incomesPanel, expensesPanel;
	
	public BudgetView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public void build(Budget budget) {
		
		
	}


	@Override
	public void setStartDate(String dateString) {
		startDate.setText(dateString);
	}


	@Override
	public void setEndDate(String dateString) {
		endDate.setText(dateString);
	}


	@Override
	public void setIncomes(IsWidget incomes) {
		incomesPanel.setWidget(incomes);
		
	}


	@Override
	public void setExpenses(IsWidget expenses) {
		expensesPanel.setWidget(expenses);
	}
	
	
}

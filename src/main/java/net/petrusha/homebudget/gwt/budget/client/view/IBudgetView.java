package net.petrusha.homebudget.gwt.budget.client.view;

import net.petrusha.homebudget.model.Budget;

import com.google.gwt.user.client.ui.IsWidget;

public interface IBudgetView extends IsWidget {
	
	public interface IBudgetPresenter {
		
	}

	void build(Budget budget);

	void setStartDate(String dateString);

	void setEndDate(String dateString);

	void setIncomes(IsWidget incomes);

	void setExpenses(IsWidget expenses);
	
}

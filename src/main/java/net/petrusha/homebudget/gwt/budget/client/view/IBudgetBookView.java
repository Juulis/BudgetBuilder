package net.petrusha.homebudget.gwt.budget.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IBudgetBookView extends IsWidget {

	public interface IBudgetBookPresenter {

		void startNewBudget();
		
	}

	void setPageTitle(String title);

	void setBudgetView(IsWidget budgetView);
	
	
}

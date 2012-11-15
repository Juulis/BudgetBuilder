package net.petrusha.homebudget.gwt.budget.client;

import net.petrusha.homebudget.model.Budget;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BudgetServiceAsync {

	void saveBudget(Budget budget, AsyncCallback<Void> callback);

}

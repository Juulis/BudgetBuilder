package net.petrusha.homebudget.gwt.budget.client;

import net.petrusha.homebudget.model.Budget;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("budget")
public interface BudgetService extends RemoteService {

	void saveBudget(Budget budget);
	
}

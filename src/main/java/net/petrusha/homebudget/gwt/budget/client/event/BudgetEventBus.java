package net.petrusha.homebudget.gwt.budget.client.event;

import net.petrusha.homebudget.gwt.budget.client.BudgetModule;
import net.petrusha.homebudget.gwt.budget.client.presenter.BudgetBookPresenter;
import net.petrusha.homebudget.gwt.budget.client.presenter.BudgetPlanningFormPresenter;
import net.petrusha.homebudget.gwt.budget.client.presenter.BudgetPresenter;
import net.petrusha.homebudget.gwt.utils.client.event.CommonEvents;
import net.petrusha.homebudget.model.Budget;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

@Events(startPresenter = BudgetBookPresenter.class,  module = BudgetModule.class )
public interface BudgetEventBus extends CommonEvents {

	@Event(handlers = BudgetBookPresenter.class)
	void goToBudgets();
	
	@Event(handlers = BudgetBookPresenter.class)
	void setBudgetView(IsWidget view);
	
	@Event(handlers = BudgetPlanningFormPresenter.class)
	void startPlanning();
	
	@Event(handlers = BudgetPresenter.class)
	void showBudget(Budget budget);
	
	@Event(forwardToParent = true)
	void setDashboardCenter(IsWidget view);

	
}

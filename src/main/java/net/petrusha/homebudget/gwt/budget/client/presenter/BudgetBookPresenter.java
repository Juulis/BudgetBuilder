package net.petrusha.homebudget.gwt.budget.client.presenter;

import net.petrusha.homebudget.gwt.budget.client.BudgetServiceAsync;
import net.petrusha.homebudget.gwt.budget.client.event.BudgetEventBus;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetBookView;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetBookView.IBudgetBookPresenter;
import net.petrusha.homebudget.gwt.budget.client.view.impl.BudgetBookView;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter( view = BudgetBookView.class )
public class BudgetBookPresenter extends
		BasePresenter<IBudgetBookView, BudgetEventBus> implements
		IBudgetBookPresenter {
	
	@Inject
	BudgetServiceAsync service;
	
	public void onGoToBudgets() {
		prepareView();
		eventBus.setDashboardCenter(view);
	}
	
	private void prepareView() {
		
	}

	@Override
	public void startNewBudget() {
		eventBus.startPlanning();
		//eventBus.showBudget(Utils.getTestBudget());
	}
	
	public void onSetBudgetView(IsWidget budgetView){
		view.setBudgetView(budgetView);
	}
	
	public void setBudgetSevice(BudgetServiceAsync service) {
		this.service = service;
	}
}

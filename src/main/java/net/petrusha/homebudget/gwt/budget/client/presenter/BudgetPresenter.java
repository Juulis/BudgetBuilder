package net.petrusha.homebudget.gwt.budget.client.presenter;

import java.util.Date;

import net.petrusha.homebudget.gwt.budget.client.BudgetServiceAsync;
import net.petrusha.homebudget.gwt.budget.client.event.BudgetEventBus;
import net.petrusha.homebudget.gwt.budget.client.utils.BudgetWidgetsFactory;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetView;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetView.IBudgetPresenter;
import net.petrusha.homebudget.gwt.budget.client.view.impl.BudgetView;
import net.petrusha.homebudget.model.Budget;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter( view = BudgetView.class)
public class BudgetPresenter extends BasePresenter<IBudgetView, BudgetEventBus>
		implements IBudgetPresenter {
	
	
	
	private Budget budget;
	
	private DateTimeFormat dateFormater = DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_DAY);
	
	@Inject
	private BudgetServiceAsync service; 
	
	private BudgetWidgetsFactory widgetFactory = BudgetWidgetsFactory.getInstance();
	
	public BudgetPresenter() {
		
	}
	
	public void onShowBudget(Budget budget) {
		this.budget = budget;
		
		prepareView();
	
		eventBus.setBudgetView(view);
	}

	protected void prepareView() {
		view.setStartDate(getDateString(budget.getStartDate()));
		view.setEndDate(getDateString(budget.getEndDate()));
		
		view.setIncomes(widgetFactory.getPaymentCategorysView(budget.getIncomesCategories()));
		view.setExpenses(widgetFactory.getPaymentCategorysView(budget.getExpensesCategories()));
		
	}
		
	protected String getDateString(Date date) {
		if (date != null) {
			return dateFormater.format(date);
		}
		return "N/A";
	}
	
	
	
	
	public void saveCurrentBudget() {
		
		
	}
	
	
}

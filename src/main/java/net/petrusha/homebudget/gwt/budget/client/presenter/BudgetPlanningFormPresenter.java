package net.petrusha.homebudget.gwt.budget.client.presenter;

import java.util.Date;

import net.petrusha.homebudget.gwt.budget.client.event.BudgetEventBus;
import net.petrusha.homebudget.gwt.budget.client.model.PaymentDTO;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetPlanningFormView;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetPlanningFormView.IBudgetPlanningFormPresenter;
import net.petrusha.homebudget.gwt.budget.client.view.impl.BudgetPlanningFormView;
import net.petrusha.homebudget.model.Budget;
import net.petrusha.homebudget.model.BudgetWrapper;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = BudgetPlanningFormView.class)
public class BudgetPlanningFormPresenter extends
		BasePresenter<IBudgetPlanningFormView, BudgetEventBus> implements
		IBudgetPlanningFormPresenter {

	protected BudgetWrapper budget = new BudgetWrapper(new Budget());
	
	protected boolean isInitiated;
	
	protected WizardStep currentStep = WizardStep.START; 
	
	public BudgetPlanningFormPresenter() {
		super();
	}
	
	protected void init() {
		
		/*
		 * Title 
		 */
		HasValue<String> titleEditor = view.getTitleEditor();
		titleEditor.addValueChangeHandler(new BudgetFieldChangeHandler<String>() {
			@Override
			public void onValueChange(String value) {
				budget.setName(value);
			}
		});
		
		/*
		 * Currency
		 */
		HasValue<String> currencyEditor = view.getBaseCurrencyEditor();
		currencyEditor.addValueChangeHandler(new BudgetFieldChangeHandler<String>() {
			@Override
			public void onValueChange(String value) {
				budget.setBaseCurrency(value);
				
			}
		});
		
		/*
		 * Period
		 */
		HasValue<Date> fromEditor = view.getFromDateEditor();
		fromEditor.addValueChangeHandler(new BudgetFieldChangeHandler<Date>() {
			@Override
			public void onValueChange(Date value) {
				budget.setStartDate(value);
			}
		});
		
		HasValue<Date> toEditor = view.getToDateEditor();
		toEditor.addValueChangeHandler(new BudgetFieldChangeHandler<Date>() {
			@Override
			public void onValueChange(Date value) {
				budget.setEndDate(value);
			}
		});
		
		
		HasClickHandlers addIncomeButton = view.getAddIncomeButton();
		addIncomeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addIncomeToBudget(view);	
			}
		});
		
		HasClickHandlers addExpenseButton = view.getAddExpenseButton();
		addExpenseButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addExpenseToBudget(view);	
			}
		});
		
		isInitiated = true;
	}

	
	protected void addIncomeToBudget(IBudgetPlanningFormView view) {
		HasValue<PaymentDTO> incomeEditor = view.getIncomeEditor();
		PaymentDTO paymentDTO = incomeEditor.getValue();
		
		budget.addPlannedIncome(
				paymentDTO.getCategory(), 
				paymentDTO.getTitle(),
				paymentDTO.getDescription(),
				paymentDTO.getDate(), 
				Double.valueOf(paymentDTO.getAmmount()),
				paymentDTO.getCurrencyCode());
		
		view.refreshBudgetInfo(budget);
		view.refreshIncomes(budget);
	}
	
	protected void addExpenseToBudget(IBudgetPlanningFormView view) {
		HasValue<PaymentDTO> editor = view.getExpenseEditor();
		PaymentDTO paymentDTO = editor.getValue();
		
		budget.addPlannedExpense( 
				paymentDTO.getCategory(), 
				paymentDTO.getTitle(),
				paymentDTO.getDescription(),
				paymentDTO.getDate(), 
				Double.valueOf(paymentDTO.getAmmount()),
				paymentDTO.getCurrencyCode());
		
		view.refreshBudgetInfo(budget);
		view.refreshExpenses(budget);
	}

	public void onStartPlanning() {
		if (!isInitiated) {
			init();
		}
		
		prepareView();
		goToWizardStep(WizardStep.START);
		eventBus.setDashboardCenter(view);
	}
	
	protected IBudgetPlanningFormView prepareView() {
		view.refreshAll(budget); 
		return view;
	}

	
	
	@Override
	public void goToWizardStep(WizardStep step) {
		currentStep = step;
		view.showPrevButton(step.hasPrev());
		view.showNextButton(step.hasNext());
		
		
		switch (step) {
			case START:
				view.showStartStep(budget);
				break;
	        case INCOMES:
	        	view.showIncomesStep(budget);
				break;	
			case EXPENSES:
				view.showExpensesStep(budget);
				break;
			case FINISH:
				view.showFinishStep(budget);
				break;
			default:
				break;
		}
		
	}
	
	protected abstract class BudgetFieldChangeHandler<T> implements ValueChangeHandler<T> {
		@Override
		public void onValueChange(ValueChangeEvent<T> event) {
			onValueChange(event.getValue());
			view.refreshBudgetInfo(budget);
		}
		
		public abstract void onValueChange(T value);
	}

	@Override
	public void goNextStep() {
		if (currentStep.hasNext()) {
			goToWizardStep(currentStep.getNext());	
		}
	}

	@Override
	public void goPrevStep() {
		if (currentStep.hasPrev()) {
			goToWizardStep(currentStep.getPrev());
		}
	}

	@Override
	public String getBaseCurrency() {
		return budget.getBaseCurrency();
	}
	
}

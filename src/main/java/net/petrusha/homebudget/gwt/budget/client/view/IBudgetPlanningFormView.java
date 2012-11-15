package net.petrusha.homebudget.gwt.budget.client.view;

import java.util.Date;

import net.petrusha.homebudget.gwt.budget.client.model.PaymentDTO;
import net.petrusha.homebudget.model.BudgetWrapper;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface IBudgetPlanningFormView extends IsWidget {

	public interface IBudgetPlanningFormPresenter {
        public static enum WizardStep {
        	START, INCOMES, EXPENSES, FINISH;
      
        	public boolean hasNext() {
        		return getNext() != null;
        	}
        	
        	public WizardStep getNext() {
        		WizardStep next = null;
        		if (ordinal() < values().length - 1) {
        			next = values()[ordinal() + 1];
        		}
        		return next;
        	}
        	
        	public boolean hasPrev() {
        		return getPrev() != null;
        	}
        	
        	public WizardStep getPrev() {
              	WizardStep prev = null;
        		if (ordinal() > 0) {
        			prev = values()[ordinal() - 1];
        		}
        		return prev;
        	}
        }
		
		void goToWizardStep(WizardStep step);

		void goNextStep();

		void goPrevStep();

		String getBaseCurrency();
		
	}

	void showStartStep(BudgetWrapper budget);
	
	void showIncomesStep(BudgetWrapper budget);

	void showExpensesStep(BudgetWrapper budget);

	void showFinishStep(BudgetWrapper budget);

	HasValue<String> getTitleEditor();

	void refreshBudgetInfo(BudgetWrapper budget);

	HasValue<Date> getFromDateEditor();

	HasValue<Date> getToDateEditor();

	void showPrevButton(boolean hasPrev);

	void showNextButton(boolean hasNext);

	HasClickHandlers getAddIncomeButton();
	
	HasClickHandlers getAddExpenseButton();

	HasValue<PaymentDTO> getIncomeEditor();
	
	HasValue<PaymentDTO> getExpenseEditor();

	HasValue<String> getBaseCurrencyEditor();

	void refreshIncomes(BudgetWrapper budget);
	
	void refreshExpenses(BudgetWrapper budget);

	void refreshAll(BudgetWrapper budget);

	
	
}

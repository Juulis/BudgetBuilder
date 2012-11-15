package net.petrusha.homebudget.gwt.budget.client.view.impl;

import java.util.Date;

import net.petrusha.homebudget.gwt.budget.client.model.PaymentDTO;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetPlanningFormView;
import net.petrusha.homebudget.gwt.budget.client.view.IBudgetPlanningFormView.IBudgetPlanningFormPresenter.WizardStep;
import net.petrusha.homebudget.gwt.budget.client.widget.BudgetInfo;
import net.petrusha.homebudget.gwt.budget.client.widget.CategoriesPlaningWidget;
import net.petrusha.homebudget.gwt.main.client.view.impl.ReverseCompositeView;
import net.petrusha.homebudget.gwt.utils.client.ClientUtils;
import net.petrusha.homebudget.model.BudgetWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BudgetPlanningFormView extends ReverseCompositeView<IBudgetPlanningFormView.IBudgetPlanningFormPresenter> implements IBudgetPlanningFormView {

	interface BudgetPlanningFormViewUiBinder extends UiBinder<Widget, BudgetPlanningFormView> {}
	private static BudgetPlanningFormViewUiBinder uiBinder = GWT.create(BudgetPlanningFormViewUiBinder.class);
	
	
	interface Style extends CssResource {
		String active();
		
		String disabled();
	}
	
	@UiField
	Style style;
	
	@UiField
	Anchor startStepAnchor, incomesStepAnchor, expensesStepAnchor, finishStepAnchor; 
	
	@UiField
	BudgetInfo budgetInfo;
	
	@UiField
	HTMLPanel wizardPlaceHolder;
	
	@UiField
	HTMLPanel startStepPanel, finishStepPanel;
	
	@UiField
	SimplePanel incomesStepPanel, expensesStepPanel;
	
	@UiField
	SimplePanel startDateField, endDateField;
	
	@UiField
	protected TextBox titleBox;
	@UiField
	protected SuggestBox baseCurrencyBox;
	
	protected DateBox startDateBox;
	protected DateBox endDateBox;
	
	@UiField
	protected RadioButton useTemplate;
	@UiField
	protected RadioButton usePreviousBudget;
	@UiField
	protected ListBox templates;
	
	@UiField
	protected CategoriesPlaningWidget incomesPlaningWidget;
	
	@UiField
	protected CategoriesPlaningWidget expensesPlaningWidget;
	
	
	@UiField
	protected Button nextButton, prevButton;
	
	public BudgetPlanningFormView() {
		initWidget(uiBinder.createAndBindUi(this));
	
		startDateField.setWidget(startDateBox = new DateBox());
		endDateField.setWidget(endDateBox = new DateBox());
		
		MultiWordSuggestOracle currencyOracle = (MultiWordSuggestOracle) baseCurrencyBox.getSuggestOracle();
		currencyOracle.addAll(ClientUtils.getCurrencyList());
		
	}

	@UiHandler("startStepAnchor")
	public void clickToStartStep(ClickEvent click) {
		presenter.goToWizardStep(WizardStep.START);
	}
	
	@UiHandler("incomesStepAnchor")
	public void clickToIncomeStep(ClickEvent click) {
		presenter.goToWizardStep(WizardStep.INCOMES);
	}
	
	@UiHandler("expensesStepAnchor")
	public void clickToExpensesStep(ClickEvent click) {
		presenter.goToWizardStep(WizardStep.EXPENSES);
	}
	
	@UiHandler("finishStepAnchor")
	public void clickToFinishStep(ClickEvent click) {
		presenter.goToWizardStep(WizardStep.FINISH);
	}

	@UiHandler("nextButton")
	public void clickNext(ClickEvent clickEvent) {
		presenter.goNextStep();
	}
	
	@UiHandler("prevButton")
	public void clickPrev(ClickEvent clickEvent) {
		presenter.goPrevStep();
	}
	
	@Override
	public void showStartStep(BudgetWrapper budget) {
		hideAllSteps();
		setActive(startStepAnchor);
		startStepPanel.setVisible(true);
	}

	@Override
	public void showIncomesStep(BudgetWrapper budget) {
		refreshIncomes(budget);
		hideAllSteps();
		setActive(incomesStepAnchor);
		incomesStepPanel.setVisible(true);
	}

	@Override
	public void showExpensesStep(BudgetWrapper budget) {
		refreshExpenses(budget);
		hideAllSteps();
		setActive(expensesStepAnchor);
		expensesStepPanel.setVisible(true);
	}

	@Override
	public void showFinishStep(BudgetWrapper budget) {
		hideAllSteps();
		setActive(finishStepAnchor);
		finishStepPanel.setVisible(true);
	}
	
	private void hideAllSteps() {
		startStepPanel.setVisible(false);
		incomesStepPanel.setVisible(false);
		expensesStepPanel.setVisible(false);
		finishStepPanel.setVisible(false);
	}
	
	private void setActive(UIObject uiObject) {
		deactive(startStepAnchor, incomesStepAnchor, expensesStepAnchor, finishStepAnchor);
		uiObject.addStyleName(style.active());
	}
	
	private void deactive(UIObject... uiObjects) {
		for (UIObject uiObject : uiObjects) {
			uiObject.removeStyleName(style.active());
		}
	}

	@Override
	public void refreshBudgetInfo(BudgetWrapper budget) {
		budgetInfo.render(budget);
	}
	
	

	public IsWidget buildBudgetInfo(BudgetWrapper budget) {
		return new BudgetInfo(budget);
	}
	
	@Override
	public HasValue<String> getTitleEditor() {
		return titleBox;
	}

	@Override
	public HasValue<Date> getFromDateEditor() {
		return startDateBox;
	}

	@Override
	public HasValue<Date> getToDateEditor() {
		return endDateBox;
	}

	@Override
	public void showPrevButton(boolean hasPrev) {
		prevButton.setVisible(hasPrev);
		
	}

	@Override
	public void showNextButton(boolean hasNext) {
		nextButton.setVisible(hasNext);
	}

	@Override
	public HasClickHandlers getAddIncomeButton() {
		return incomesPlaningWidget.getAddButton();
	}

	@Override
	public HasClickHandlers getAddExpenseButton() {
		return expensesPlaningWidget.getAddButton();
	}

	@Override
	public HasValue<PaymentDTO> getIncomeEditor() {
		return incomesPlaningWidget.getPaymentEditor();
	}
	
	@Override
	public HasValue<PaymentDTO> getExpenseEditor() {
		return expensesPlaningWidget.getPaymentEditor();
	}

	@Override
	public HasValue<String> getBaseCurrencyEditor() {
		return baseCurrencyBox;
	}

	@Override
	public void refreshIncomes(BudgetWrapper budget) {
		incomesPlaningWidget.refresh(budget.getBaseCurrency(), budget.getIncomesCategories());
	}
	
	@Override
	public void refreshExpenses(BudgetWrapper budget) {
		expensesPlaningWidget.refresh(budget.getBaseCurrency(), budget.getExpensesCategories());
	}

	@Override
	public void refreshAll(BudgetWrapper budget) {
		refreshBudgetInfo(budget);
		refreshIncomes(budget);
		refreshExpenses(budget);
	}

	
	
}

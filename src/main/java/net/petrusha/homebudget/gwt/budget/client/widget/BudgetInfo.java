package net.petrusha.homebudget.gwt.budget.client.widget;

import net.petrusha.homebudget.model.BudgetWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class BudgetInfo extends Composite implements Editor<BudgetWrapper> {

	protected interface Driver extends SimpleBeanEditorDriver<BudgetWrapper, BudgetInfo> {}
	private Driver driver = GWT.create(Driver.class);
	
	interface BudgetInfoUiBinder extends UiBinder<Widget, BudgetInfo> {}
	private static BudgetInfoUiBinder uiBinder = GWT.create(BudgetInfoUiBinder.class);
	
	@UiField
	protected Label nameEditor; 
	@UiField
	protected DateLabel startDateEditor;
	@UiField
	protected DateLabel endDateEditor;
	
	@UiField()
	@Ignore
	protected PriceInfo plannedIncomes;
	
	@UiField()
	@Ignore
	protected PriceInfo plannedExpenses;
	
	@UiField()
	@Ignore
	protected PriceInfo plannedTotal;
	
	
	@UiField()
	@Ignore
	protected PriceInfo realIncomes;
	
	@UiField()
	@Ignore
	protected PriceInfo realExpenses;
	
	@UiField()
	@Ignore
	protected PriceInfo realTotal;
	
	
	@UiField()
	@Ignore
	protected PriceInfo marginIncomes;
	
	@UiField()
	@Ignore
	protected PriceInfo marginExpenses;
	
	@UiField()
	@Ignore
	protected PriceInfo marginTotal;
	
	
	
	public BudgetInfo() {
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(this);
	}
	
	public BudgetInfo(BudgetWrapper budget) {
		this();
		render(budget);
	}
	
	public void render(BudgetWrapper budget) {
		setBudget(budget);
	}
	
	public void setBudget(BudgetWrapper budget) {
		driver.edit(budget);
		
		((PriceInfo)plannedIncomes).setValue(budget.getPlannedIncomesAmmount());
		((PriceInfo)plannedExpenses).setValue(budget.getPlannedExpensesAmmount());
		((PriceInfo)plannedTotal).setValue(budget.getPlannedTotalAmmount());
		
		((PriceInfo)realIncomes).setValue(budget.getRealIncomesAmmount());
		((PriceInfo)realExpenses).setValue(budget.getRealExpensesAmmount());
		((PriceInfo)realTotal).setValue(budget.getRealTotalAmmount());
		
		((PriceInfo)marginIncomes).setValue(budget.getMarginIncomesAmmount());
		((PriceInfo)marginExpenses).setValue(budget.getMarginExpensesAmmount());
		((PriceInfo)marginTotal).setValue(budget.getMarginTotalAmmount());
		
	}
	
}

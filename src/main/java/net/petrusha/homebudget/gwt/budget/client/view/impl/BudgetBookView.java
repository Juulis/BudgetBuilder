package net.petrusha.homebudget.gwt.budget.client.view.impl;


import net.petrusha.homebudget.gwt.budget.client.view.IBudgetBookView;
import net.petrusha.homebudget.gwt.main.client.view.impl.ReverseCompositeView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BudgetBookView extends ReverseCompositeView<IBudgetBookView.IBudgetBookPresenter> implements
		IBudgetBookView {
	
	interface BudgetBookViewUiBinder extends UiBinder<Widget, BudgetBookView> {}
	private static BudgetBookViewUiBinder uiBinder = GWT.create(BudgetBookViewUiBinder.class);
	
	
	@UiField
	Anchor startNewBudgetButton;
	
	@UiField
	SimplePanel budgetPlaceHolder;
	
	
	public BudgetBookView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public void setPageTitle(String title) {
		
	}
	
	@UiHandler("startNewBudgetButton")
	public void onStartNewBudgetButtonPresed(ClickEvent e) {
		presenter.startNewBudget();
	}


	@Override
	public void setBudgetView(IsWidget budgetView) {
		budgetPlaceHolder.setWidget(budgetView);
	}

	
}

package net.petrusha.homebudget.gwt.main.client.view.impl;

import java.util.ArrayList;

import net.petrusha.homebudget.gwt.main.client.view.IControlPanelView;
import net.petrusha.homebudget.gwt.main.client.view.IControlPanelView.IControlPanelPresenter;
import net.petrusha.homebudget.gwt.widget.client.DialogHelper;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ControlPanelView extends
		ReverseCompositeView<IControlPanelPresenter> implements
		IControlPanelView {

	interface ControlPanelViewUiBinder extends UiBinder<Widget, ControlPanelView> {}
	private static ControlPanelViewUiBinder uiBinder = GWT.create(ControlPanelViewUiBinder.class);
	
	private DialogHelper dialogHelper = new DialogHelper();
	
	@UiField
	Anchor newCurrancyButton, deleteCurrenciesButton;
	
	@UiField
	FlowPanel currenciesList;
	
	public ControlPanelView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("newCurrancyButton")
	void onNewCurrancyButtonClick(ClickEvent e) {
		presenter.newCurrancy();
	}

	public void showCurrencyForm() {
		CurrencyFormView currencyFormView = new CurrencyFormView(presenter);
		dialogHelper.showDialog("New Currency Form", 
				currencyFormView, currencyFormView.saveButton, 
				currencyFormView.cancelButton);
	}

	public void setCurrencies(ArrayList<Currency> currencies) {
		currenciesList.clear();
		for (Currency currency: currencies) {
			currenciesList.add(new Label(currency.getCode()));
		}
	}
	
}

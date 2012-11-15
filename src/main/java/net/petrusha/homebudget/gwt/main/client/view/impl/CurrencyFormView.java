package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IControlPanelView.ICurrencyManager;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CurrencyFormView extends Composite implements IsWidget {
	
	interface CurrencyFormViewUiBinder extends UiBinder<Widget, CurrencyFormView> {}
	private static CurrencyFormViewUiBinder uiBinder = GWT.create(CurrencyFormViewUiBinder.class);
	
	private ICurrencyManager currencyManager;
	
	private Currency currency;
	
	@UiField
	Label codeLabel;
	
	@UiField
	TextBox codeInput;
	
	@UiField
	Button saveButton, cancelButton;
	
	public CurrencyFormView(ICurrencyManager currencyManager) {
		this.currencyManager = currencyManager;
		initWidget(uiBinder.createAndBindUi(this));
		
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				fillCurrency();
				CurrencyFormView.this.currencyManager.saveCurrency(currency);
			}
		});
		
	}
	
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}
	
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}
	
	
	private void fillCurrency() {
		if (currency == null) {
		    currency = new Currency(); 
		}
		currency.setCode(codeInput.getValue());
	}
	
}

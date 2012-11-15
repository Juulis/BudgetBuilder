package net.petrusha.homebudget.gwt.main.client.view.impl;

import java.util.ArrayList;

import net.petrusha.homebudget.gwt.main.client.Messages;
import net.petrusha.homebudget.gwt.main.client.view.IAccountsView.IAccountsPresenter;
import net.petrusha.homebudget.gwt.widget.client.DropDown;
import net.petrusha.homebudget.gwt.widget.client.DropDown.IDropDownRenderer;
import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Account.AccountType;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;



class AccountFormView extends ReverseCompositeView<IAccountsPresenter> {
	
	interface AccountFormViewUiBinder extends UiBinder<Widget, AccountFormView> {}
	private static AccountFormViewUiBinder uiBinder = GWT.create(AccountFormViewUiBinder.class);
	
	private Account account; 
	
	private Messages messages = GWT.create(Messages.class);
	
	@UiField
	Label nameLabel, typeLabel, currencyLabel, amountLabel;
	
	@UiField
	ListBox typeInput; 
	
	@UiField
	DropDown<Currency> currencyInput;
	
	@UiField
	TextBox nameInput, amountInput;
	
	@UiField
	Button saveButton, cancelButton;
	
	public AccountFormView(Account account, final IAccountsPresenter presenter) {
		this.account = account;
		this.presenter = presenter;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		fillForm();
		
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.saveAccount(fillAccount());
			}
		});
		
	}
	
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}
	
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}
	
	private void fillForm() {
		nameLabel.setText(messages.name());
		typeLabel.setText(messages.type());
		currencyLabel.setText(messages.currency());
		amountLabel.setText(messages.amount());
		
		nameInput.setValue(account.getTitle());
		
		ArrayList<Currency> currencies = presenter.getCurrencies();
		
		currencyInput.setItems(currencies, new IDropDownRenderer<Currency>() {
			public String getItemTitle(Currency item) {
				return item.getCode();
			}
		});
		
		for (Currency currency: currencies) {
			currencyInput.addItem(currency.getCode(), currency.getCode());
			if (currency.getCode().equals(account.getCurrency())) {
				currencyInput.setSelectedIndex(currencyInput.getItemCount() - 1);
			}
		}
		
		for (AccountType type: AccountType.values()) {
			typeInput.addItem(type.name(), type.name());
			if (type.equals(account.getType())) {
				typeInput.setSelectedIndex(typeInput.getItemCount() - 1);
			}
		}
		
		amountInput.setValue(String.valueOf(account.getAmmount()));
	}
	
	private Account fillAccount(){
		account.setTitle(nameInput.getValue());
	
		String currencyCode = currencyInput.getValue(currencyInput.getSelectedIndex());
		account.setCurrency(currencyCode);
		String typeValue = typeInput.getValue(typeInput.getSelectedIndex());
		account.setType(AccountType.valueOf(typeValue));
		
		account.setAmmount(Double.valueOf(amountInput.getValue()));
		
		return account;
	}
	
}

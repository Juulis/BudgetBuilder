package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IAccountsView.IAccountView;
import net.petrusha.homebudget.gwt.main.client.view.IAccountsView.IAccountsPresenter;
import net.petrusha.homebudget.model.Account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AccountView extends ReverseCompositeView<IAccountsPresenter> implements
		IAccountView {

	
	
	interface AccountViewUiBinder extends UiBinder<Widget, AccountView> {}
	private static AccountViewUiBinder uiBinder = GWT.create(AccountViewUiBinder.class);
	
	private Account account;
		
	@UiField
	CheckBox check;
	
	@UiField
	Anchor title; 
	
	@UiField
	Label ammount, type, currency;
	
	public AccountView(Account account, IAccountsPresenter presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		this.presenter = presenter;
		setAccount(account);
	}

	public void setAccount(final Account account) {
		this.account = account;
		title.setHTML(account.getTitle());
		ammount.setText(String.valueOf(account.getAmmount()));
		type.setText(account.getType().name());
		currency.setText(account.getCurrency());
	}
	
	
	
	@UiHandler("check")
	public void onChecked(ValueChangeEvent<Boolean> e) {
		if (check.getValue()) {
			presenter.addToSelected(account);
		} else {
			presenter.removeFromSelected(account);
		}
	}
	
	@UiHandler("title")
	public void onClickTitle(ClickEvent e) {
		presenter.editAccount(account);
	}
	


}

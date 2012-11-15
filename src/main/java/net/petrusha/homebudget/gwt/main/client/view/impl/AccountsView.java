package net.petrusha.homebudget.gwt.main.client.view.impl;


import java.util.ArrayList;

import net.petrusha.homebudget.gwt.main.client.Messages;
import net.petrusha.homebudget.gwt.main.client.view.IAccountsView;
import net.petrusha.homebudget.model.Account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AccountsView extends ReverseCompositeView<IAccountsView.IAccountsPresenter> implements
		IAccountsView {
	
	interface AccountsViewUiBinder extends UiBinder<Widget, AccountsView> {}
	private static AccountsViewUiBinder uiBinder = GWT.create(AccountsViewUiBinder.class);
	
	
	Messages messages = GWT.create(Messages.class);
	
	@UiField
	Anchor newButton, deleteButton;
	
	@UiField
	VerticalPanel accountsList;
	
	public AccountsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addAccount(Account account) {
		accountsList.add(getAccountView(account));
	}

	public void addAllAccounts(ArrayList<Account> accounts) {
		if (accounts != null) {
			for (Account account : accounts) {
				addAccount(account);
			}
		}
	}

	public void setAccounts(ArrayList<Account> accounts) {
		accountsList.clear();
		addAllAccounts(accounts);
	}

	
	@UiHandler("newButton")
	public void onClickNew(ClickEvent e) {
		presenter.newAccount();
	}
	
	@UiHandler("deleteButton")
	public void onClickDelete(ClickEvent e) {
		presenter.deleteSelectedAccounts();
	}
	
	final DialogBox dialogBox = new DialogBox();
	{
		dialogBox.setGlassEnabled(true);
	}
	
	public void showAccountForm(Account account) {
		if (account.getId() != null) {
			dialogBox.setText(messages.account() + " " + account.getTitle());
		} else {
			dialogBox.setText(messages.newAccount());
		}
		
		AccountFormView accountFormView = prepareAccountForm(account);
		
		ClickHandler closeDialogHandler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		};
		accountFormView.getSaveButton().addClickHandler(closeDialogHandler);
		accountFormView.getCancelButton().addClickHandler(closeDialogHandler);
		
		dialogBox.setWidget(accountFormView);
		dialogBox.center();
		dialogBox.show();
		
	}
	
	
	
	private AccountFormView prepareAccountForm(final Account account) {
		AccountFormView accountFormView = new AccountFormView(account, presenter);
		return accountFormView;
	}

	protected IAccountView getAccountView(Account account) {
		return new AccountView(account, presenter);
	}
	
	
}

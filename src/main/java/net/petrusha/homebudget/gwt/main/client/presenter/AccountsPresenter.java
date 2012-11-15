package net.petrusha.homebudget.gwt.main.client.presenter;

import java.util.ArrayList;
import java.util.HashSet;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IAccountsView;
import net.petrusha.homebudget.gwt.main.client.view.IAccountsView.IAccountsPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.AccountsView;
import net.petrusha.homebudget.gwt.utils.client.BaseAsyncCallback;
import net.petrusha.homebudget.gwt.utils.client.ClientHelper;
import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Currency;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = AccountsView.class)
public class AccountsPresenter extends
		BasePresenter<IAccountsView, MainEventBus> implements
		IAccountsPresenter {
	
	private ArrayList<Account> accounts;
	
	private HashSet<Account> selected = new HashSet<Account>();
	
	@Inject
	ClientHelper cache;
	
	@Inject
	MainServiceAsync service;
	
	public void onGoToAccounts() {
		if (accounts == null) {
			 accounts = new ArrayList<Account>();
		}
		
		if (accounts.size() == 0) {
			reloadAccounts();
		} else {
			view.setAccounts(accounts);
		}
		
		eventBus.setDashboardCenter(view);
	}
	
	public void newAccount() {
		view.showAccountForm(new Account());
	}

	public void deleteSelectedAccounts() {
		service.deleteAccounts(selected, new BaseAsyncCallback<Void>(eventBus) {
			public void onSuccess(Void result) {
				reloadAccounts();
			}
		});
	}

	public void reloadAccounts() {
		service.getAccounts(new BaseAsyncCallback<ArrayList<Account>>(eventBus) {
			public void onSuccess(ArrayList<Account> result) {
				accounts.clear();
				accounts.addAll(result);
				view.setAccounts(accounts);
			}
		});
	}
	
	public void editAccount(Account account) {
		view.showAccountForm(account);
	}

	public void addToSelected(Account account) {
		selected.add(account);
		
	}

	public void removeFromSelected(Account account) {
		selected.remove(account);
	}

	public void saveAccount(Account account) {
		service.saveAccount(account, new BaseAsyncCallback<Account>(eventBus) {
			public void onSuccess(Account result) {
				reloadAccounts();
			}
		});
	}

	public Currency getCurrencyByCode(String currencyCode) {
		ArrayList<Currency> currencies = cache.getCurrencies();
		for (Currency currency: currencies) {
			if (currency.getCode().equalsIgnoreCase(currencyCode)) {
				return currency;
			}
		}
		throw new IllegalArgumentException();
	}

	public ArrayList<Currency> getCurrencies() {
		return cache.getCurrencies();
	}
	
	

}

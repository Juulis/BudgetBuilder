package net.petrusha.homebudget.gwt.main.client.view;

import java.util.ArrayList;

import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.ui.IsWidget;

public interface IAccountsView extends IsWidget {

	public interface IAccountsPresenter {

		void newAccount();
		
		void deleteSelectedAccounts();

		void editAccount(Account account);

		void addToSelected(Account account);

		void removeFromSelected(Account account);

		void saveAccount(Account account);

		Currency getCurrencyByCode(String currencyCode);

		ArrayList<Currency> getCurrencies();
		
	}

	public interface IAccountView extends IsWidget {
		
		void setAccount(Account account);
		
	}
	
	void addAccount(Account account);
	
	void addAllAccounts(ArrayList<Account> accounts);
	
	void setAccounts(ArrayList<Account> accounts);
	
	void showAccountForm(Account account);
}

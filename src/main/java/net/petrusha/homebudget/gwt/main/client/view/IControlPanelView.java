package net.petrusha.homebudget.gwt.main.client.view;

import java.util.ArrayList;

import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.ui.IsWidget;

public interface IControlPanelView extends IsWidget {

	public interface ICurrencyManager {
		void saveCurrency(Currency currency);
		void newCurrancy();
	}
	
	public interface IConfigurationManager {
		
	}
	
	public interface IControlPanelPresenter extends ICurrencyManager, IConfigurationManager {
		
	}
	
	void showCurrencyForm();

	void setCurrencies(ArrayList<Currency> currencies);
	
}

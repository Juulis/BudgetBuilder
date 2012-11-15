package net.petrusha.homebudget.gwt.utils.client;

import java.util.ArrayList;
import java.util.HashMap;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.utils.client.event.CommonEvents;
import net.petrusha.homebudget.model.Account.AccountType;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.event.BaseEventHandler;

@EventHandler
@Singleton
public class ClientHelper extends BaseEventHandler<CommonEvents> {

	private HashMap<String, Object> attrs = new HashMap<String, Object>();
	
	//Dictionaries
	private ArrayList<Currency> currencies = new ArrayList<Currency>();
	private ArrayList<AccountType> accountTypes = new ArrayList<AccountType>();
	{
		for (AccountType type : AccountType.values()) {
			accountTypes.add(type);
		}
		
	}

	@Inject
	private MainServiceAsync service;
	
	public void onStart() {
		
		/*
		 * Fill currencies
		 */
		service.getCurrencies(new BaseAsyncCallback<ArrayList<Currency>>(eventBus) {
			@Override
			public void onSuccess(ArrayList<Currency> result) {
				setCurrencies(result);
			}
		});
		
		
	}
	
	
	public void onResetCache() {
		onStart();
	}
	
	public void setCurrencies(ArrayList<Currency> list) {
		currencies.clear();
		currencies.addAll(list);
	}
	
	public ArrayList<Currency> getCurrencies() {
		return new ArrayList<Currency>(currencies);
	}
	
	public ArrayList<AccountType> getAccountTypes() {
		return new ArrayList<AccountType>(accountTypes);
	}
	
	public void putAttribute(String name, Object value) {
		attrs.put(name, value);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getAttribute(String name, Class<E> clazz) {
		return (E) attrs.get(name);
	}
	
	public Object getAttribute(String name) {
		return attrs.get(name);
	}

	
	public void onError(Throwable caught) {
		Window.alert(caught.getMessage());
	}
	
	
}

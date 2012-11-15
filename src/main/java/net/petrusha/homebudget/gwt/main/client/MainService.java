package net.petrusha.homebudget.gwt.main.client;

import java.util.ArrayList;
import java.util.HashSet;

import net.petrusha.homebudget.gwt.main.client.model.UiModel;
import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("main")
public interface MainService extends RemoteService {
	
	UiModel.UserInfoResult getUserInfo(String backUrl);
	
	boolean isAuthenticated();
	
	/*
	 * Profiles
	 */
	void createProfile(String name);
	
	ArrayList<UiModel.ProfileInfo> getProfiles();
	
	
	/*
	 * Accounts 
	 */
	ArrayList<Account> getAccounts() throws Exception;
	
	Account saveAccount(Account account) throws Exception;
	
	void deleteAccounts(HashSet<Account> accounts) throws Exception;
	
	/*
	 * Currency
	 */
	void saveCurrency(Currency currency) throws Exception;;
	
	ArrayList<Currency> getCurrencies() throws Exception;;
}

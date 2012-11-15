package net.petrusha.homebudget.gwt.main.client;

import java.util.ArrayList;
import java.util.HashSet;

import net.petrusha.homebudget.gwt.main.client.model.UiModel.ProfileInfo;
import net.petrusha.homebudget.gwt.main.client.model.UiModel.UserInfoResult;
import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MainServiceAsync {

	void createProfile(String name, AsyncCallback<Void> callback);

	void deleteAccounts(HashSet<Account> accounts, AsyncCallback<Void> callback);

	void getAccounts(AsyncCallback<ArrayList<Account>> callback);

	void getCurrencies(AsyncCallback<ArrayList<Currency>> callback);

	void getProfiles(AsyncCallback<ArrayList<ProfileInfo>> callback);

	void getUserInfo(String backUrl, AsyncCallback<UserInfoResult> callback);

	void isAuthenticated(AsyncCallback<Boolean> callback);

	void saveAccount(Account account, AsyncCallback<Account> callback);

	void saveCurrency(Currency currency, AsyncCallback<Void> callback);

}

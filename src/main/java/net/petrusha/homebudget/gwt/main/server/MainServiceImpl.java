package net.petrusha.homebudget.gwt.main.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.petrusha.homebudget.app.HomeBudget;
import net.petrusha.homebudget.app.HomeBudgetFactory;
import net.petrusha.homebudget.gwt.main.client.MainService;
import net.petrusha.homebudget.gwt.main.client.model.UiModel;
import net.petrusha.homebudget.gwt.main.client.model.UiModel.ProfileInfo;
import net.petrusha.homebudget.gwt.main.shared.exception.AuthenticationException;
import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.gae.jdo.ModelConverter;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MainServiceImpl extends RemoteServiceServlet implements
		MainService{

	private static final String CURRENT_USER_PROFILE = "CURRENT_USER_PROFILE";

	private static final long serialVersionUID = -7329427206162352174L;

	private HomeBudget homeBudget = HomeBudgetFactory.getHomeBudgetApplication();
	
	private UserService userService = UserServiceFactory.getUserService();
	
	public UiModel.UserInfoResult getUserInfo(String backUrl) {
		User user = userService.getCurrentUser();
		UiModel.UserInfoResult userInfo = new UiModel.UserInfoResult();
		
		if (user == null) {
			String loginUrl = userService.createLoginURL(backUrl);
			userInfo.setLoginUrl(loginUrl);
			clearSession();
		} else {
			if (getCurrentProfile() != null) {
				userInfo.setHasProfile(true);
			}
			userInfo.setUserName(user.getNickname());
			String logoutUrl = userService.createLogoutURL(backUrl);
			userInfo.setLogoutUrl(logoutUrl);
		}
		
		return userInfo;
	}
	
	private void clearSession() {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session != null) {
			session.invalidate();
		}
	}

	public boolean isAuthenticated() {
		return userService.getCurrentUser() != null && getCurrentProfile() != null;
	}

	public ArrayList<Account> getAccounts() throws Exception {
		checkAuthentication();
		ArrayList<Account> uiAccounts = new ArrayList<Account>();
		
		UserProfileEntity currentProfile = getCurrentProfile();
		
		List<AccountEntity> accounts = currentProfile.getAccounts();

		for (AccountEntity account : accounts) {
			uiAccounts.add(ModelConverter.get(account));
		}
		
		return uiAccounts;
	}

	protected void checkAuthentication() throws AuthenticationException {
		if (getCurrentProfile() == null) {
			throw new AuthenticationException();
		}
	}
	
	protected UserProfileEntity getCurrentProfile() {
		UserProfileEntity currentUserProfile = (UserProfileEntity) getThreadLocalRequest().getSession()
				.getAttribute(CURRENT_USER_PROFILE);
		if (currentUserProfile == null) {
			List<UserProfileEntity> userProfiles = homeBudget.getUserProfiles(userService.getCurrentUser().getUserId());
			if (userProfiles != null && userProfiles.size() > 0) {
				getThreadLocalRequest().getSession(true).setAttribute(
						CURRENT_USER_PROFILE, currentUserProfile = findCurrentUserProfile(userProfiles));
			}
		}
		return currentUserProfile; 
	}
	
	/**
	 * TODO: Implement me using User Preferences 
	 * 
	 * @param profiles
	 * @return
	 */
	private UserProfileEntity findCurrentUserProfile(List<UserProfileEntity> profiles) {
		if (profiles != null) {
			return profiles.get(0);
		}
		return null; 
	}
	
	public void saveCurrency(Currency currency) {
		homeBudget.saveCurrency(currency);
	}

	public ArrayList<Currency> getCurrencies() {
		List<Currency> currencies = homeBudget.getCurrencies();
		return new ArrayList<Currency>(currencies);
	}

	public Account saveAccount(Account account) {
		UserProfileEntity profile = getCurrentProfile();
		
		AccountEntity accountEntity = ModelConverter.get(account);
		accountEntity.setUserId(profile.getUserId());
		
		AccountEntity savedAccountEntity = homeBudget.saveAccount(accountEntity);
		
		if (account.getId() != null) {
			profile.removeAccountById(account.getId());
		}
		
		profile.addAccount(savedAccountEntity);
		homeBudget.saveProfile(profile);
		return ModelConverter.get(savedAccountEntity);
	}

	public void deleteAccounts(HashSet<Account> uiAccounts) {
		UserProfileEntity currentProfile = getCurrentProfile();
		for (Account uiAccount: uiAccounts) {
			currentProfile.removeAccountById(uiAccount.getId());
		}
		homeBudget.saveProfile(currentProfile);
	}

	public void createProfile(String name) {
		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setName(name);
		userProfile.setUserId(userService.getCurrentUser().getUserId());
        homeBudget.saveProfile(userProfile);
	}

	public ArrayList<UiModel.ProfileInfo> getProfiles() {
		List<UserProfileEntity> userProfiles = homeBudget.getUserProfiles(userService.getCurrentUser().getUserId());
		ArrayList<ProfileInfo> profileInfos = new ArrayList<UiModel.ProfileInfo>();
		if (userProfiles != null) {
			for (UserProfileEntity userProfile: userProfiles) {
				profileInfos.add(new UiModel.ProfileInfo(String.valueOf(userProfile.getId()), userProfile.getName()));
			}
		}
		return profileInfos;
	}


}

package net.petrusha.homebudget.app.impl;

import java.util.List;

import net.petrusha.homebudget.app.HomeBudget;
import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.HomeBudgetDAO;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;

public class HomeBudgetImpl implements HomeBudget {

	private HomeBudgetDAO dao;
	
	public HomeBudgetImpl(HomeBudgetDAO dao) {
		this.dao = dao;
	}
	
	public List<Currency> getCurrencies() {
		List<Currency> currencies = dao.getCurrencies();
		return currencies;
	}

	public Currency saveCurrency(final Currency currency) {
		return dao.saveCurrency(currency);
	}

	public List<UserProfileEntity> getUserProfiles(final String userId) {
		return dao.getUserProfilesByUserId(userId);
	}

	public UserProfileEntity saveProfile(final UserProfileEntity profile) {
		return dao.saveUserProfile(profile);
	}

	@Override
	public AccountEntity saveAccount(AccountEntity accountEntity) {
		return dao.saveAccount(accountEntity);
	}
	
	
}

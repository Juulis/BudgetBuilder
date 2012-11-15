package net.petrusha.homebudget.persistence;

import java.util.List;

import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;

public interface HomeBudgetDAO {

	List<Currency> getCurrencies();

	Currency saveCurrency(Currency currency);

	List<UserProfileEntity> getUserProfilesByUserId(String userId);

	UserProfileEntity saveUserProfile(UserProfileEntity profile);

	AccountEntity saveAccount(AccountEntity accountEntity);
	
	
	
}

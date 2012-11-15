package net.petrusha.homebudget.app;

import java.util.List;

import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;

public interface HomeBudget {

	List<Currency> getCurrencies();
	
	Currency saveCurrency(Currency currency);

	List<UserProfileEntity> getUserProfiles(String userId);

	UserProfileEntity saveProfile(UserProfileEntity prfile);

	AccountEntity saveAccount(AccountEntity accountEntity);

}

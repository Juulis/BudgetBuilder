package net.petrusha.homebudget.persistence.gae.jdo;

import java.util.List;

import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.HomeBudgetDAO;
import net.petrusha.homebudget.persistence.gae.jdo.dao.AccountDAO;
import net.petrusha.homebudget.persistence.gae.jdo.dao.CurrencyDAO;
import net.petrusha.homebudget.persistence.gae.jdo.dao.UserProfileDAO;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.CurrencyEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;


public class HomeBudgetDAOJDO implements HomeBudgetDAO {
	
	private AccountDAO accountDAO = new AccountDAO();
	
	private CurrencyDAO currencyDAO = new CurrencyDAO();
	
	private UserProfileDAO userProfileDAO = new UserProfileDAO();
	
	
	
	
	public List<Currency> getCurrencies() {
		List<CurrencyEntity> entities = currencyDAO.findAll();
		return ModelConverter.convert(entities);
	}




	public Currency saveCurrency(Currency currency) {
		final CurrencyEntity currencyEntity = ModelConverter.get(currency);
		currencyDAO.save(currencyEntity);
		return currency;
	}


	public List<UserProfileEntity> getUserProfilesByUserId(String userId) {
		List<UserProfileEntity> profilesByUserId = userProfileDAO.getProfilesByUserId(userId);
		return profilesByUserId;
	}
	
	
	
	public UserProfileEntity saveUserProfile(UserProfileEntity profile) {
		return userProfileDAO.save(profile);
	}




	@Override
	public AccountEntity saveAccount(AccountEntity accountEntity) {
		return accountDAO.save(accountEntity);
	}
	
}

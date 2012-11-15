package net.petrusha.homebudget.persistence.gae.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.petrusha.homebudget.model.Account;
import net.petrusha.homebudget.model.Account.AccountType;
import net.petrusha.homebudget.model.Currency;
import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;
import net.petrusha.homebudget.persistence.gae.jdo.model.CurrencyEntity;


public class ModelConverter {

	private ModelConverter() {
		
	}
	
	public static Account get(AccountEntity entity) {
		Account account = new Account();
		if (entity.getId() != null) {
			account.setId(entity.getId());
		}
		account.setTitle(entity.getName());
		account.setAmmount(entity.getAmount());
		account.setCurrency(entity.getCurrencyCode());
		account.setType(AccountType.valueOf(entity.getType()));
		
		return account;
	}
	
	public static Currency get(CurrencyEntity entity) {
		Currency currency = new Currency(entity.getCode()); 
		return currency;
	}

	public static AccountEntity get(Account account) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(account.getId());
		accountEntity.setName(account.getTitle());
		accountEntity.setAmount(account.getAmmount());
		accountEntity.setCurrencyCode(account.getCurrency());
		accountEntity.setType(account.getType().name());
		
		return accountEntity;
	}
	
	public static CurrencyEntity get(Currency currency) {
		CurrencyEntity entity = new CurrencyEntity(currency.getCode());
		return entity;
	}
	
	
	public static List<Currency> convert(Collection<CurrencyEntity> entities) {
		List<Currency> currencies = new ArrayList<Currency>();
		if (entities != null) {
			for (CurrencyEntity entity: entities) {
				currencies.add(get(entity));
			}
		}
		return currencies;
	}
	

	public static ArrayList<AccountEntity> get(ArrayList<Account> uiAccounts) {
		ArrayList<AccountEntity> accounts = new ArrayList<AccountEntity>(uiAccounts.size());
		for (Account uiAccount: uiAccounts) {
			accounts.add(get(uiAccount));
		}
		return accounts;
	}
	
}

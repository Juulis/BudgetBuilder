package net.petrusha.homebudget.persistence.gae.jdo.dao;

import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;

public class AccountDAO extends BaseDAO<AccountEntity> {

	public AccountDAO() {
		super(AccountEntity.class);
	}

}

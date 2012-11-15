package net.petrusha.homebudget.persistence.gae.jdo.dao;

import net.petrusha.homebudget.persistence.gae.jdo.model.CurrencyEntity;

public class CurrencyDAO extends BaseDAO<CurrencyEntity> {

	public CurrencyDAO() {
		super(CurrencyEntity.class);
	}

}

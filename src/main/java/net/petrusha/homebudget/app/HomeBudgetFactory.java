package net.petrusha.homebudget.app;

import net.petrusha.homebudget.app.impl.HomeBudgetImpl;
import net.petrusha.homebudget.persistence.gae.jdo.HomeBudgetDAOJDO;

public final class HomeBudgetFactory {

	private static final HomeBudget instance = new HomeBudgetImpl(new HomeBudgetDAOJDO());
	
	private HomeBudgetFactory() {
		
	}
	
	public static HomeBudget getHomeBudgetApplication() {
		return instance;
	}
	
	
	
}

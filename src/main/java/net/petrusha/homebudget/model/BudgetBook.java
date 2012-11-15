package net.petrusha.homebudget.model;

import java.util.LinkedList;
import java.util.List;


public class BudgetBook extends BudgetItem {

	private static final long serialVersionUID = -7155238119703645405L;
	
	private List<Account> accounts = new LinkedList<Account>();

	private List<Budget> periods = new LinkedList<Budget>();
	
	private WishList wishList = new WishList();

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public List<Budget> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Budget> periods) {
		this.periods = periods;
	}
	
	public void addPeriod(Budget period) {
		this.periods.add(period);
	}

	public WishList getWishList() {
		return wishList;
	}

	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	
	public void addWish(Wish wish) {
		this.wishList.addWish(wish);
	}
	
}

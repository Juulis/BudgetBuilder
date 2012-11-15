package net.petrusha.homebudget.model;

import java.util.LinkedList;
import java.util.List;

public class WishList extends BudgetItem {

	private static final long serialVersionUID = 7951462853524111632L;

	private List<Wish> wishes = new LinkedList<Wish>();
	
	public List<Wish> getWishList() {
		return wishes;
	}

	public void setWishList(List<Wish> wishes) {
		this.wishes = wishes;
	}

	public void addWish(Wish wish) {
		wishes.add(wish);
	}
	
}

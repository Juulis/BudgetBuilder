package net.petrusha.homebudget.gwt.main.client.view.impl;

import com.google.gwt.user.client.ui.Composite;
import com.mvp4g.client.view.ReverseViewInterface;

public class ReverseCompositeView<P> extends Composite implements
		ReverseViewInterface<P> {

	protected P presenter;
	
	public void setPresenter(P presenter) {
		this.presenter = presenter;
		
	}

	public P getPresenter() {
		return presenter;
	}

}

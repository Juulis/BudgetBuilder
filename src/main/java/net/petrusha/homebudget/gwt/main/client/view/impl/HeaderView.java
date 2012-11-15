package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IHeaderView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class HeaderView extends
		ReverseCompositeView<IHeaderView.IHeaderPresenter> implements
		IHeaderView {
	
	interface HeaderViewUiBinder extends UiBinder<Widget, HeaderView> {}
	private static HeaderViewUiBinder uiBinder = GWT.create(HeaderViewUiBinder.class);
	
	@UiField
	HorizontalPanel headerPanel;
	
	@UiField
	SimplePanel logo, userInfo;
	
	public HeaderView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setLogo(IsWidget logo) {
		this.logo.setWidget(logo);
	}

	public void setUserInfo(IsWidget userInfo) {
		this.userInfo.setWidget(userInfo);
	}
	
}

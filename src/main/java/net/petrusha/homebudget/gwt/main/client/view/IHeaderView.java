package net.petrusha.homebudget.gwt.main.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IHeaderView extends IsWidget {
	
	public interface IHeaderPresenter {
		
	}
	
	public void setLogo(IsWidget logo);
	public void setUserInfo(IsWidget userInfo);

}

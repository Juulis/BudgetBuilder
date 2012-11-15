package net.petrusha.homebudget.gwt.main.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IMainView extends IsWidget {

	public interface IMainPresenter {
		
	}

	void setHeader( IsWidget header );
	
	void setFooter( IsWidget footer );

    void setBody( IsWidget body );
	
}

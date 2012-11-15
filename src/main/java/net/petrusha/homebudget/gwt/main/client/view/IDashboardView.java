package net.petrusha.homebudget.gwt.main.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IDashboardView extends IsWidget {
	
	interface IDashboardPresenter {
		
	}
	
	void setLeft(IsWidget widget);
	void setCenter(IsWidget widget);
	void setRight(IsWidget widget);
}

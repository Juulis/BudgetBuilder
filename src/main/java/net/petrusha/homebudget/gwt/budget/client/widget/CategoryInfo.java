package net.petrusha.homebudget.gwt.budget.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CategoryInfo extends Composite {
	
	interface CategoryInfoUiBinder extends UiBinder<Widget, CategoryInfo> {}
	private static CategoryInfoUiBinder uiBinder = GWT.create(CategoryInfoUiBinder.class);

	public CategoryInfo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}

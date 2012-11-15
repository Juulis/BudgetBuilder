package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IFooterView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class FooterView extends
		ReverseCompositeView<IFooterView.IFooterPresenter> implements
		IFooterView {

	interface FooterViewUiBinder extends UiBinder<Widget, FooterView> {}
	private static FooterViewUiBinder uiBinder = GWT.create(FooterViewUiBinder.class);
	
	@UiField
	HTMLPanel footerPanel; 
	
	public FooterView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}

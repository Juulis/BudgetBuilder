package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IMainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends ReverseCompositeView<IMainView.IMainPresenter>
		implements IMainView {
	
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {}
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	

	@UiField
	SimplePanel header, body, footer;
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setHeader(IsWidget header) {
		this.header.setWidget(header);
	}

	public void setFooter(IsWidget footer) {
		this.footer.setWidget(footer);
	}
	
	public void setBody(IsWidget body) {
		this.body.setWidget(body);
	}

		
}

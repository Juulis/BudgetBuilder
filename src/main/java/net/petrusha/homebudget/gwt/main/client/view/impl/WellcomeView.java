package net.petrusha.homebudget.gwt.main.client.view.impl;


import net.petrusha.homebudget.gwt.main.client.view.IWellcomeView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;

public class WellcomeView extends ReverseCompositeView<IWellcomeView.IWellcomePresenter> implements
		IWellcomeView {

	interface WellcomeViewUiBinder extends UiBinder<Widget, WellcomeView> {}
	private static WellcomeViewUiBinder uiBinder = GWT.create(WellcomeViewUiBinder.class);
	
	@UiField
	Anchor signUpButton;
	
	public WellcomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		signUpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				onSignUpClicked();
			}
		});
	}
	
	private void onSignUpClicked() {
		presenter.signUp();
	}
	
}

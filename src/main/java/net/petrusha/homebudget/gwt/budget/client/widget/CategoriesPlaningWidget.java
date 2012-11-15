package net.petrusha.homebudget.gwt.budget.client.widget;

import java.util.ArrayList;

import net.petrusha.homebudget.gwt.budget.client.model.PaymentDTO;
import net.petrusha.homebudget.model.PaymentCategory;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CategoriesPlaningWidget extends Composite {

	interface CategoriesPlaningWidgetUiBinder extends UiBinder<Widget, CategoriesPlaningWidget> {}
	private static CategoriesPlaningWidgetUiBinder uiBinder = GWT.create(CategoriesPlaningWidgetUiBinder.class);
	
	@UiField
	protected Label title;
	
	@UiField
	protected PaymentEditor paymentEditor;
	
	@UiField
	protected CategoriesInfo categoriesInfo;
	
	
	public CategoriesPlaningWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void refresh(String baseCurrency, ArrayList<PaymentCategory> categories) {
		paymentEditor.refresh(baseCurrency);
		categoriesInfo.refresh(categories);
	}


	public HasClickHandlers getAddButton() {
		return paymentEditor.getAddButton();
	}


	public HasValue<PaymentDTO> getPaymentEditor() {
		return paymentEditor;
	}
	
	
	
}

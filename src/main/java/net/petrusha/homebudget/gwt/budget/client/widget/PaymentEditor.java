package net.petrusha.homebudget.gwt.budget.client.widget;


import net.petrusha.homebudget.gwt.budget.client.model.PaymentDTO;
import net.petrusha.homebudget.gwt.utils.client.ClientUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class PaymentEditor extends Composite implements Editor<PaymentDTO>, HasValue<PaymentDTO> {

	protected interface Driver extends SimpleBeanEditorDriver<PaymentDTO, PaymentEditor> {}
	private Driver driver = GWT.create(Driver.class);
	
	interface PaymentEditorUiBinder extends UiBinder<Widget, PaymentEditor> {}
	private static PaymentEditorUiBinder uiBinder = GWT.create(PaymentEditorUiBinder.class);
		
	@UiField
	protected SuggestBox categoryEditor;
	
	@UiField
	protected TextBox titleEditor;
	
	@UiField
	protected TextBox descriptionEditor;
	
	@UiField
	protected SimplePanel datePlaceHolder;
	protected DateBox dateEditor;
	
	@UiField
	protected TextBox ammountEditor;
	
	@UiField
	protected Label currencyCodeEditor;
	
	@UiField
	protected Button submitButton;
	
	public PaymentEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		datePlaceHolder.setWidget(dateEditor = new DateBox()); 
		dateEditor.setFormat(
				new DateBox.DefaultFormat(
						ClientUtils.getFormat(ClientUtils.DateFormat.SHORT_DATE)));
		driver.initialize(this);
		setValue(new PaymentDTO());
	}
	
	public PaymentDTO getPayment() {
		return driver.flush();
	}

	private boolean valueChangeHandlerInitialized;
	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<PaymentDTO> handler) {
		 // Initialization code
	    if (!valueChangeHandlerInitialized) {
	      valueChangeHandlerInitialized = true;
	      addChangeHandler(new ChangeHandler() {
	        public void onChange(ChangeEvent event) {
	          ValueChangeEvent.fire(PaymentEditor.this, getValue());
	        }
	      });
	    }
	    return addHandler(handler, ValueChangeEvent.getType());
	}

	private HandlerRegistration addChangeHandler(ChangeHandler changeHandler) {
		return addDomHandler(changeHandler, ChangeEvent.getType());
	}

	@Override
	public PaymentDTO getValue() {
		return driver.flush();
	}

	@Override
	public void setValue(PaymentDTO value) {
		driver.edit(value);
	}

	@Override
	public void setValue(PaymentDTO value, boolean fireEvents) {
		setValue(value);
		ValueChangeEvent.fire(this, getValue());
	}
	
	public void setCurrencyCode(String currencyCode) {
		PaymentDTO value = getValue();
		value.setCurrencyCode(currencyCode);
		setValue(value);
	}

	public void refresh(String baseCurrency) {
		setCurrencyCode(baseCurrency);
		titleEditor.setText("");
		descriptionEditor.setText("");
		dateEditor.setValue(null);
		ammountEditor.setText("0.0");
	}

	public HasClickHandlers getAddButton() {
		return submitButton;
	}
}

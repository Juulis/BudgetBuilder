package net.petrusha.homebudget.gwt.budget.client.widget;

import net.petrusha.homebudget.gwt.main.client.MainStyle;
import net.petrusha.homebudget.model.Price;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.client.ui.Label;

public class PriceInfo extends Label {
	
	public static final String STYLE_POSITIVE = "price-positive";
	public static final String STYLE_NEGATIVE = "price-negative";
	
	public void setValue(Price price) {
		String currency = price.getCurrencyCode();

		NumberFormat currencyFormat = null;
		if (currency != null && !currency.isEmpty()) { 
			currencyFormat = NumberFormat.getSimpleCurrencyFormat(price.getCurrencyCode());
		} else {
			currencyFormat = NumberFormat.getSimpleCurrencyFormat();
		}
		Double ammount = price.getAmmount();
		setText(currencyFormat.format(ammount));
		if (ammount > 0) {
			setStyleName(STYLE_POSITIVE);
		} else if (ammount < 0) {
			setStyleName(STYLE_NEGATIVE);
		}
	}
	
}

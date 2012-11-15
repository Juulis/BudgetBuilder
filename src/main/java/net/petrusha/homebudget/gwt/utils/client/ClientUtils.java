package net.petrusha.homebudget.gwt.utils.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.petrusha.homebudget.model.Price;

import com.google.gwt.i18n.client.CurrencyData;
import com.google.gwt.i18n.client.CurrencyList;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

public abstract class ClientUtils {

	public static enum DateFormat {
		SHORT_DATE {
			public PredefinedFormat get() {
				return PredefinedFormat.DATE_SHORT;
			}
		},
		
		SHORT_DATE_TIME {
			public PredefinedFormat get() {
				return PredefinedFormat.DATE_TIME_SHORT;
			}
		};
		
		public abstract PredefinedFormat get();
	}
	
	public static DateTimeFormat getFormat(DateFormat formatDefinition) {
		return DateTimeFormat.getFormat(formatDefinition.get());
	}
	
	public static String getDateString(Date date, DateFormat format) {
		DateTimeFormat dateTimeformat = DateTimeFormat.getFormat(format.get());
		return dateTimeformat.format(date);
	}

	public static String getPriceString(Price price) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyFormat(price.getCurrencyCode());
		return currencyFormat.format(price.getAmmount());
	}
	
	
	private static ArrayList<String> currencyList;
	public static ArrayList<String> getCurrencyList() {
		if (currencyList == null) {
			CurrencyList currencies = CurrencyList.get();
			currencyList = new ArrayList<String>();
			Iterator<CurrencyData> curItr = currencies.iterator();
			while (curItr.hasNext()) {
				CurrencyData curData = curItr.next();
				currencyList.add(curData.getCurrencyCode());
			}
		}
		
		return new ArrayList<String>(currencyList);
	}
	
}

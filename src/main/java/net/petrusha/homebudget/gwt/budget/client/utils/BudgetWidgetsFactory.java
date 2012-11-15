package net.petrusha.homebudget.gwt.budget.client.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.petrusha.homebudget.model.BudgetCalculator;
import net.petrusha.homebudget.model.Payment;
import net.petrusha.homebudget.model.PaymentCategory;
import net.petrusha.homebudget.model.PaymentItem;
import net.petrusha.homebudget.model.Price;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BudgetWidgetsFactory {

	private static BudgetWidgetsFactory instance = new BudgetWidgetsFactory();

	private BudgetWidgetsFactory() {

	}

	public static BudgetWidgetsFactory getInstance() {
		return instance;
	}

	public IsWidget getPaymentCategorysView(ArrayList<PaymentCategory> categories) {
		Panel categoriesPanel = new VerticalPanel();
		categoriesPanel.addStyleName("categories-panel");
		
		for (PaymentCategory incomeGroup : categories) {
			categoriesPanel.add(getCategoryView(incomeGroup));
		}
		
		PaymentItem sumOfCategories = BudgetCalculator.getInstance().getSumOfCategories(categories);
		sumOfCategories.setName("Total:");

		Widget totalItem = getPaymentItemView(sumOfCategories);
		totalItem.addStyleName("total");
		categoriesPanel.add(totalItem);

		return categoriesPanel;
	}
	
	protected IsWidget getCategoryView(PaymentCategory incomesCategory) {
		Panel categoryPanel = new VerticalPanel();
		categoryPanel.addStyleName("payment-category");
		
		Panel incomesCategoryHeader = new FlowPanel();
		Label headerLabel = new Label(incomesCategory.getName());
		headerLabel.setStyleName("header");
		incomesCategoryHeader.add(headerLabel);
		
		categoryPanel.add(incomesCategoryHeader);
		categoryPanel.add(getPaymentItemsView(incomesCategory));

		return categoryPanel;
	}

	private IsWidget getPaymentItemsView(PaymentCategory paymentCategory) {
		Panel itemsGrid = new VerticalPanel(); 
		List<PaymentItem> items = paymentCategory.getItems();
		for (PaymentItem item : items) {
			itemsGrid.add(getPaymentItemView(item));
		}
		return itemsGrid;
	}

	private Widget getPaymentItemView(PaymentItem item) {
		Panel itemView = new HorizontalPanel();
		
		String name = item.getName();
		String description = item.getDescription();
		
		Panel nameDescriptionPanel = new VerticalPanel();
		Label nameLabel = new Label(name);
		nameLabel.setStyleName("name");
		nameDescriptionPanel.add(nameLabel);
		
		Label descriptionLabel = new Label(description);
		descriptionLabel.setStyleName("description");
		nameDescriptionPanel.add(descriptionLabel);
		
		
		itemView.add(nameDescriptionPanel);
		itemView.add(getPaymentView(item.getPlan()));
		itemView.add(getPaymentView(item.getFact()));
		return itemView;
	}
	
	public IsWidget getPaymentView(Payment payment) {
		Panel paymentView = new VerticalPanel();
		
		Price price = payment.getPrice();
		paymentView.add(getPriceView(price));
		
		Date paymentDate = payment.getPaymentDate();
		if (paymentDate != null) {
			DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
			paymentView.add(new Label(dateFormat.format(paymentDate)));
		}
		
		return paymentView;
	}
	
	public IsWidget getPriceView(Price price) {
		Label priceView = new Label();
		if (!price.getCurrencyCode().isEmpty()) {
			NumberFormat currencyFormat = NumberFormat.getCurrencyFormat(price.getCurrencyCode());
			priceView.setText(currencyFormat.format(price.getAmmount()));
		} 
		priceView.setStyleName("price");
		return priceView;
	}

}

package net.petrusha.homebudget.gwt.budget.client.widget;

import java.util.ArrayList;
import java.util.List;

import net.petrusha.homebudget.gwt.budget.client.utils.BudgetWidgetsFactory;
import net.petrusha.homebudget.gwt.main.client.MainStyle;
import net.petrusha.homebudget.model.PaymentCategory;
import net.petrusha.homebudget.model.PaymentItem;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CategoriesInfo extends Composite {
	
	interface CategoriesInfoUiBinder extends UiBinder<Widget, CategoriesInfo> {}
	private static CategoriesInfoUiBinder uiBinder = GWT.create(CategoriesInfoUiBinder.class);
	
	interface Style extends MainStyle {
		String title();
	}
	
	@UiField
	protected Style style;
	
	@UiField
	protected VerticalPanel mainPanel;
	
	
	public CategoriesInfo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void render(List<PaymentCategory> categoryList) {
		for (PaymentCategory category: categoryList) {
			addCategory(category);
		}
	}

	protected void addCategory(PaymentCategory category) {
		Panel categoryPanel = new VerticalPanel();
		Label nameLabel = new Label(category.getName());
		nameLabel.addStyleName(style.title());
		categoryPanel.add(nameLabel);
		categoryPanel.add(getItemsView(category));
		mainPanel.add(categoryPanel);
	}

	private Widget getItemsView(PaymentCategory category) {
		CellTable<PaymentItem> table = prepareItemsTable();
		
		List<PaymentItem> items = category.getItems();
		
		return table;
	}
	
	private CellTable<PaymentItem> prepareItemsTable() {
		CellTable<PaymentItem> table = new CellTable<PaymentItem>();
		
		
		
		return table;
	}

	public void refresh(ArrayList<PaymentCategory> categories) {
		mainPanel.clear();
		mainPanel.add(new CategoryInfo());
		
	}

}

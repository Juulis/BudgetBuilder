package net.petrusha.homebudget.gwt.main.client.presenter;

import java.util.LinkedList;

import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IDashboardView;
import net.petrusha.homebudget.gwt.main.client.view.IDashboardView.IDashboardPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.DashboardView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter( view=DashboardView.class )
public class DashboardPresenter extends
		BasePresenter<IDashboardView, MainEventBus> implements
		IDashboardPresenter {
	
	public void onGoToDashboard() {
		eventBus.startDashboard();
		eventBus.setBody(view);
	}
	
	public void onGoToBudgets() {
		view.setCenter(createBudgetsPanel());
	}
	
	public void onGoToWishList() {
		view.setCenter(createWishListPanel());
	}
	
	
	public void onStartDashboard() {
		
		IsWidget leftPanel = createLeftPanel();
		IsWidget centerPanel = createCenterPanel();
		IsWidget rightPanel = createRightPanel();
		
		eventBus.setDashboardLeft(leftPanel);
		eventBus.setDashboardCenter(centerPanel);
		eventBus.setDashboardRight(rightPanel);
		
	}
	

	public void onSetDashboardLeft(IsWidget widget) {
		view.setLeft(widget);
	}
	
	public void onSetDashboardCenter(IsWidget widget) {
		view.setCenter(widget);
	}
	
	public void onSetDashboardRight(IsWidget widget) {
		view.setRight(widget);
	}
	
	
	
	private LinkedList<HasEnabled> linksList = new LinkedList<HasEnabled>();
	private IsWidget createLeftPanel() {
		VerticalPanel panel = new VerticalPanel();
		
		// DashBoard link
		panel.add(createActionLink("Dashboard", new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.goToDashboard();
			}
		}, false));
		
		// Accounts
		panel.add(createActionLink("Accounts", new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.goToAccounts();
			}
		}, true));
		
		// Budgets
		panel.add(createActionLink("Budgets", new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.goToBudgets();
			}
		}, true));
		
		// Wish Lists
		panel.add(createActionLink("Wish Lists", new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.goToWishList();
			}
		}, true));
		
		// Wish Lists
		panel.add(createActionLink("Control Panel", new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.goToControlPanel();
			}
		}, true));
		
		
		return panel;
	}
	
	private Anchor createActionLink(String title, ClickHandler handler, boolean enable) {
		Anchor link = new Anchor(title);
		link.setEnabled(enable);
		link.addClickHandler(handler);
		linksList.add(link);
		return link;
	}
	
	private IsWidget createCenterPanel() {
		return createDashBoardPanel();
	}

	private IsWidget createRightPanel() {
		return new HTML("Advertising here");
	}
	
	
	
	
	
	
	
	private IsWidget createDashBoardPanel() {
		return  new HTML("<h2>DashBoard</h2>");
	}
	
	private IsWidget createBudgetsPanel() {
		return  new HTML("<h2>Budgets</h2>");
	}
	
	private IsWidget createWishListPanel() {
		return  new HTML("<h2>Wish Lists</h2>");
	}
	
}

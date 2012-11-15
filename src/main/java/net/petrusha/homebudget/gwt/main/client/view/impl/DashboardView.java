package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IDashboardView;
import net.petrusha.homebudget.gwt.main.client.view.IDashboardView.IDashboardPresenter;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

public class DashboardView extends ReverseCompositeView<IDashboardPresenter>
		implements IDashboardView {

	//Panel placeholder = new HTMLPanel("<h2>Dashboard</h2>");
	
	private DockPanel dashboardDock = new DockPanel();
	
	private SimplePanel leftPanel = new SimplePanel();
	private SimplePanel centerPanel = new SimplePanel();
	private SimplePanel rightPanel = new SimplePanel();
	
	public DashboardView() {
		initWidget(dashboardDock);
		
		leftPanel.setHeight("100%");
		centerPanel.setHeight("100%");
		rightPanel.setHeight("100%");
		
		leftPanel.addStyleName("border-right");
		rightPanel.addStyleName("border-left");

		dashboardDock.add(leftPanel, DockPanel.WEST);
		dashboardDock.add(centerPanel, DockPanel.CENTER);
		dashboardDock.add(rightPanel, DockPanel.EAST);
		
		dashboardDock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dashboardDock.setWidth("100%");
		dashboardDock.setHeight("100%");
		dashboardDock.setCellWidth(leftPanel, "250px");
		//dashboardDock.setCellWidth(centerPanel, "100%");
		dashboardDock.setCellWidth(rightPanel, "100px");
		
		
	}

	public void setLeft(IsWidget widget) {
		leftPanel.setWidget(widget);
	}

	public void setCenter(IsWidget widget) {
		centerPanel.setWidget(widget);
	}

	public void setRight(IsWidget widget) {
		rightPanel.setWidget(widget);
	}
}

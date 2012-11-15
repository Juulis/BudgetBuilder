package net.petrusha.homebudget.gwt.main.client.view.impl;

import net.petrusha.homebudget.gwt.main.client.view.IMenuView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuView extends ReverseCompositeView<IMenuView.IMenuPresenter>
		implements IMenuView {

	private VerticalPanel menuPanel = new VerticalPanel();
	
	private Button newBudgetBookButton = new Button("New Book"); 
	
	public MenuView() {
		initWidget(menuPanel);
		
		menuPanel.add(newBudgetBookButton);
		newBudgetBookButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				presenter.newBudgetBook();
			}
			
		});
	}
	
	
	
}

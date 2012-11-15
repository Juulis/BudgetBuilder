package net.petrusha.homebudget.gwt.main.client.presenter;

import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IMenuView;
import net.petrusha.homebudget.gwt.main.client.view.IMenuView.IMenuPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.MenuView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = MenuView.class)
public class MenuPresenter extends BasePresenter<IMenuView, MainEventBus>
		implements IMenuPresenter {

	public void onStart() {
		//eventBus.setMenu(view);
	}

	public void newBudgetBook() {
		//eventBus.newBudgetBook();
	}
	
}

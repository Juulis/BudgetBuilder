package net.petrusha.homebudget.gwt.main.client.presenter;

import java.util.ArrayList;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IControlPanelView;
import net.petrusha.homebudget.gwt.main.client.view.IControlPanelView.IControlPanelPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.ControlPanelView;
import net.petrusha.homebudget.gwt.utils.client.BaseAsyncCallback;
import net.petrusha.homebudget.model.Currency;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = ControlPanelView.class)
public class ControlPanelPresenter extends
		BasePresenter<IControlPanelView, MainEventBus> implements
		IControlPanelPresenter {

	@Inject
	MainServiceAsync service;
	
	
	public void onGoToControlPanel() {
		eventBus.setDashboardCenter(prepareView());
	}
	
	private IsWidget prepareView() {
		reloadCurrencyList();
		return view;
	}

	public void saveCurrency(final Currency currency) {
		service.saveCurrency(currency, new BaseAsyncCallback<Void>(eventBus) {
			public void onSuccess(Void result) {
				eventBus.resetCache();
				reloadCurrencyList();
			}
		});
	}

	protected void reloadCurrencyList() {
		service.getCurrencies(new BaseAsyncCallback<ArrayList<Currency>>(eventBus) {
			public void onSuccess(ArrayList<Currency> result) {
				view.setCurrencies(result);
			}
		});
	}

	public void newCurrancy() {
		view.showCurrencyForm();
	}
	
	

}

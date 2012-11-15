package net.petrusha.homebudget.gwt.main.client.presenter;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IWellcomeView;
import net.petrusha.homebudget.gwt.main.client.view.IWellcomeView.IWellcomePresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.WellcomeView;
import net.petrusha.homebudget.gwt.utils.client.BaseAsyncCallback;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter( view = WellcomeView.class )
public class WellcomePresenter extends
		BasePresenter<IWellcomeView, MainEventBus> implements
		IWellcomePresenter {
	
	@Inject
	MainServiceAsync service;
	
	public void onGoToWellcome() {
		eventBus.setBody(view);
	}

	public void signUp() {
		service.createProfile("Default", new BaseAsyncCallback<Void>(eventBus) {
			public void onSuccess(Void result) {
				eventBus.start();				
			}
		});
	}

}

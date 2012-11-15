package net.petrusha.homebudget.gwt.main.client.presenter;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IMainView;
import net.petrusha.homebudget.gwt.main.client.view.IMainView.IMainPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.MainView;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = MainView.class) 
public class MainPresenter extends BasePresenter<IMainView, MainEventBus>
		implements IMainPresenter {

	@Inject
	MainServiceAsync service;
	
	public void onStart() {
		service.isAuthenticated(new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				if (result) {
					eventBus.goToDashboard();
				} else {
					eventBus.goToWellcome();
				}
			}
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void onSetHeader(IsWidget header) {
		view.setHeader(header);
	}
	
	public void onSetFooter(IsWidget footer) {
		view.setFooter(footer);
	}
	
	public void onSetBody(IsWidget body) {
		view.setBody(body);
	}
}

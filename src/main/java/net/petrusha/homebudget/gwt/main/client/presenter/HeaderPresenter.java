package net.petrusha.homebudget.gwt.main.client.presenter;

import net.petrusha.homebudget.gwt.main.client.MainServiceAsync;
import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.model.UiModel;
import net.petrusha.homebudget.gwt.main.client.view.IHeaderView;
import net.petrusha.homebudget.gwt.main.client.view.IHeaderView.IHeaderPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.HeaderView;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = HeaderView.class)
public class HeaderPresenter extends BasePresenter<IHeaderView, MainEventBus>
		implements IHeaderPresenter {

	@Inject
	private MainServiceAsync budgetService;
	
	public void onStart() {
		
		composeView();
		
		eventBus.setHeader(view);
	}
	
	private void composeView() {
		view.setLogo(new HTML("HomeBudget"));
		String backUrl = Window.Location.getHref();
		budgetService.getUserInfo(backUrl, new AsyncCallback<UiModel.UserInfoResult>() {
			public void onSuccess(UiModel.UserInfoResult result) {
				view.setUserInfo(createUserInfo(result));
			}
			
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	private IsWidget createUserInfo(UiModel.UserInfoResult userInfo) {
		FlowPanel userInfoPanel = new FlowPanel();
		if (userInfo.getUserName() != null) {
			userInfoPanel.add(new Label(userInfo.getUserName()));
			Anchor logoutLink = new Anchor("Logout");
			logoutLink.setHref(userInfo.getLogoutUrl());
			userInfoPanel.add(logoutLink);
		} else {
			Anchor loginLink = new Anchor("Login");
			loginLink.setHref(userInfo.getLoginUrl());
			userInfoPanel.add(loginLink);
		}
		return userInfoPanel;
	}
	
}

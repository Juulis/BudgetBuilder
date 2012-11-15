package net.petrusha.homebudget.gwt.main.client.presenter;

import net.petrusha.homebudget.gwt.main.client.event.MainEventBus;
import net.petrusha.homebudget.gwt.main.client.view.IFooterView;
import net.petrusha.homebudget.gwt.main.client.view.IFooterView.IFooterPresenter;
import net.petrusha.homebudget.gwt.main.client.view.impl.FooterView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = FooterView.class)
public class FooterPresenter extends BasePresenter<IFooterView, MainEventBus>
		implements IFooterPresenter {

	public void onStart() {
		eventBus.setFooter(view);
	}
}

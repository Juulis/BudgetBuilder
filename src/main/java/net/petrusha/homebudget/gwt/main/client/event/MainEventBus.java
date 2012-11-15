package net.petrusha.homebudget.gwt.main.client.event;

import net.petrusha.homebudget.gwt.budget.client.BudgetModule;
import net.petrusha.homebudget.gwt.main.client.presenter.AccountsPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.ControlPanelPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.DashboardPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.FooterPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.HeaderPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.MainPresenter;
import net.petrusha.homebudget.gwt.main.client.presenter.WellcomePresenter;
import net.petrusha.homebudget.gwt.utils.client.ClientHelper;
import net.petrusha.homebudget.gwt.utils.client.event.CommonEvents;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.annotation.module.ChildModule;
import com.mvp4g.client.annotation.module.ChildModules;


@Events(startPresenter = MainPresenter.class)
@ChildModules({
	@ChildModule(moduleClass = BudgetModule.class, async = false, autoDisplay = false)
})
public interface MainEventBus extends CommonEvents {
	
	@Start
	@Event(handlers = {ClientHelper.class, HeaderPresenter.class, FooterPresenter.class,
			MainPresenter.class })
	void start();

	/*
	 * Layout events
	 */
	@Event(handlers = MainPresenter.class)
	void setHeader(IsWidget header);

	@Event(handlers = MainPresenter.class)
	void setFooter(IsWidget footer);

	@Event(handlers = MainPresenter.class)
	void setBody(IsWidget body);

	@Event(handlers = DashboardPresenter.class)
	void startDashboard();

	@Event(handlers = DashboardPresenter.class)
	void setDashboardLeft(IsWidget widget);

	@Event(handlers = DashboardPresenter.class)
	void setDashboardCenter(IsWidget widget);

	@Event(handlers = DashboardPresenter.class)
	void setDashboardRight(IsWidget widget);

	/*
	 * Menu Events
	 */

	@Event(handlers = DashboardPresenter.class)
	void goToDashboard();

	@Event(handlers = WellcomePresenter.class)
	void goToWellcome();

	@Event(handlers = AccountsPresenter.class)
	void goToAccounts();

	@Event(forwardToModules = BudgetModule.class)
	void goToBudgets();

	@Event(handlers = DashboardPresenter.class)
	void goToWishList();

	@Event(handlers = ControlPanelPresenter.class)
	void goToControlPanel();
	
}

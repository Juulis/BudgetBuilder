package net.petrusha.homebudget.gwt.utils.client;

import net.petrusha.homebudget.gwt.utils.client.event.CommonEvents;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class BaseAsyncCallback<T> implements AsyncCallback<T> {

	private CommonEvents eventBus;
	
	public BaseAsyncCallback(CommonEvents eventBus) {
		this.eventBus = eventBus;
	}
	
	public void onFailure(Throwable caught) {
		eventBus.error(caught);		
	}

	public abstract void onSuccess(T result);

}

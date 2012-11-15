package net.petrusha.homebudget.gwt.utils.client.event;

import net.petrusha.homebudget.gwt.utils.client.ClientHelper;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.event.EventBus;

public interface CommonEvents extends EventBus {
	
	@Event( handlers = ClientHelper.class )
	void resetCache();
	
	@Event( handlers = ClientHelper.class)
	void error(Throwable caught);
	
}

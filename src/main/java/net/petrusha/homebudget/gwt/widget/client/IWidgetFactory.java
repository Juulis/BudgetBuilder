package net.petrusha.homebudget.gwt.widget.client;

import java.util.ArrayList;

import net.petrusha.homebudget.gwt.widget.client.DropDown.IDropDownRenderer;


import com.google.gwt.user.client.ui.HasValue;

public interface IWidgetFactory {

	<T> HasValue<T> getDropDown(ArrayList<T> items, IDropDownRenderer<T> renderer);
	
}

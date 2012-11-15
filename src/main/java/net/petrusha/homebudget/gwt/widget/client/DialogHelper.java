package net.petrusha.homebudget.gwt.widget.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class DialogHelper {
	
	DialogBox dialog = new DialogBox();
	{
		dialog.setGlassEnabled(true);
	}
	
	public void showDialog(String title, Widget widget, HasClickHandlers... closeControls) {
		for (HasClickHandlers control: closeControls) {
			control.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialog.hide();
					dialog.clear();
				}
			});
		}
		dialog.setText(title);
		dialog.setWidget(widget);
		dialog.center();
		dialog.show();
	}
	
	public DialogHelper() {
		
	}
	
}

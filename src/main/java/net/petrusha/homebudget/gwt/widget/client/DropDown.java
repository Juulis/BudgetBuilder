package net.petrusha.homebudget.gwt.widget.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;

public class DropDown<T> extends ListBox implements HasValue<T> {

	public static interface IDropDownRenderer<T> {
		String getItemTitle(T item);
	}

	private T value;

	private final HashMap<String, T> itemsMap = new HashMap<String, T>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private DropDown.IDropDownRenderer<T> renderer = new IDropDownRenderer() {
		public String getItemTitle(Object item) {
			return item.toString();
		}};
	
	public DropDown() {
		this(null, null, null);
	}
		
	public DropDown(ArrayList<T> items, DropDown.IDropDownRenderer<T> renderer) {
		this(items, renderer, null);
	}
		
	public DropDown(ArrayList<T> items, DropDown.IDropDownRenderer<T> renderer, T defaultValue) {
		super();
		addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int idx = getSelectedIndex();
				String itemText = getItemText(idx);
				T selectedItem = itemsMap.get(itemText);
				value = selectedItem;
				ValueChangeEvent.fire(DropDown.this, value);
			}
		});
		
		setItems(items, renderer, defaultValue);
	}

	
	public void clear() {
		super.clear();
		value = null;
		itemsMap.clear();
	}
	
	
    public void setItems(ArrayList<T> items, DropDown.IDropDownRenderer<T> renderer) {
		setItems(items, renderer, null);
	}
	
	public void setItems(ArrayList<T> items, DropDown.IDropDownRenderer<T> renderer, T defaultValue) {
		
		this.clear();
		
		if (renderer != null) {
			this.renderer = renderer;
		}
        
		if (items != null) {
			for (T item : items) {
				String itemText = renderer.getItemTitle(item);
				this.itemsMap.put(itemText, item);
				addItem(itemText);
			}
		}
		
		if (defaultValue != null) {
			setValue(defaultValue);
		}
	}
	
	public HandlerRegistration addValueChangeHandler(
			final ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		String itemText = renderer.getItemTitle(value);
		for (int i = 0; i < getItemCount(); i++ ) {
			if (getItemText(i).equals(itemText)) {
				this.value = itemsMap.get(itemText);
				setSelectedIndex(i);
				break;
			}
		}
	}

	public void setValue(T value, boolean fireEvents) {
		setValue(value);
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	
	
}
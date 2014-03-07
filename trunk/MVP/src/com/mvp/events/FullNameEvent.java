package com.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class FullNameEvent extends GwtEvent<FullNameEventHandler> {
	
	public static Type<FullNameEventHandler> TYPE = new Type<FullNameEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FullNameEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FullNameEventHandler handler) {
		handler.onSetFullName(this);
	}

}

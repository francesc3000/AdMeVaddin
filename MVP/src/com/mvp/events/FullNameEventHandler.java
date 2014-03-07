package com.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface FullNameEventHandler extends EventHandler{
	void onSetFullName(FullNameEvent event);
}

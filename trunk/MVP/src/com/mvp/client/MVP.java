package com.mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.mvp.client.presenters.PersonPresenter;
import com.mvp.client.presenters.PersonPresenter.Display;
import com.mvp.client.views.PersonView;
import com.mvp.shared.Person;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MVP implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Person me = new Person();
		Display view = new PersonView();
		SimpleEventBus eventBus = new SimpleEventBus();
		
		PersonPresenter presenter = new PersonPresenter(me,view, eventBus);
		presenter.go(RootPanel.get());
		
	}
	
}

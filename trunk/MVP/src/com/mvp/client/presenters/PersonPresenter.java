package com.mvp.client.presenters;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp.events.FullNameEvent;
import com.mvp.events.FullNameEventHandler;
import com.mvp.shared.Person;

public class PersonPresenter implements Presenter {

	private Person person;
	private Display view;
	private SimpleEventBus eventBus;
	
	public interface Display{
		public void clear();
		public void setName(String Name);
		public Widget asWidget();
		public void setPresenter(PersonPresenter presenter);
		public void setEventBus(SimpleEventBus eventBus);
	}
	
	public PersonPresenter(Person person, Display view, SimpleEventBus eventBus){
		this.person = person;
		this.view = view;
		this.eventBus = eventBus;
		
		bind();
	}
	
	@Override
	public void bind() {
		this.eventBus.addHandler(FullNameEvent.TYPE, new FullNameEventHandler(){

			@Override
			public void onSetFullName(FullNameEvent event) {
				view.setName(person.getFullName());
			}			
		});
		view.setPresenter(this);
		view.setEventBus(this.eventBus);
		view.setName(person.getFirstName());
	}

	@Override
	public void go(Panel panel) {
		panel.add(view.asWidget());
	}
	
	public void showFullName(){
		view.setName(person.getFullName());
	}

}

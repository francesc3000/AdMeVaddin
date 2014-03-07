package com.mvp.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mvp.client.presenters.PersonPresenter;
import com.mvp.events.FullNameEvent;

public class PersonView extends Composite implements PersonPresenter.Display{

	private static PersonViewUiBinder uiBinder = GWT
			.create(PersonViewUiBinder.class);
	
	private PersonPresenter presenter;
	private SimpleEventBus eventBus;

	interface PersonViewUiBinder extends UiBinder<Widget, PersonView> {
	}

	public PersonView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	
	@UiField
	Label name;

	public PersonView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		if(this.presenter!=null){
			this.presenter.showFullName();
		}
		/*if(this.eventBus!=null){
			this.eventBus.fireEvent(new FullNameEvent());
		}*/
	}

	@Override
	public void clear() {
		name.setText(" ");
	}

	@Override
	public void setName(String name) {
		this.name.setText(name);
	}

	@Override
	public void setPresenter(PersonPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setEventBus(SimpleEventBus eventBus) {
		this.eventBus = eventBus;
	}
}

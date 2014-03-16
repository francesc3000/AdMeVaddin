package zone.adme.gwt.client.activities;

import zone.adme.gwt.client.ClientFactory;
import zone.adme.gwt.client.events.OpenCellListEvent;
import zone.adme.gwt.client.events.PControlClickedEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.views.interfaces.PControlView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class PControlActivity extends AbstractActivity implements PControlView.Presenter{
	interface MyEventBinder extends EventBinder<PControlActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	private ClientFactory clientFactory;
	
	public PControlActivity(PControlPlace place, ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	public boolean OpenCellListClick() {
		this.clientFactory.getEventBus().fireEvent(new OpenCellListEvent());
		
		return true;
	}
	
	@EventHandler
	void onUserRegistered(UserRegisteredEvent event){
		this.clientFactory.getMainView().getNorte().clear();
		//this.clientFactory.getMainView().getCentro().add(this.clientFactory.getPControlView());
	}
	
	@EventHandler
	void onShowPControl(PControlClickedEvent event){
		
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.clientFactory.getEventBus());
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		PControlView pControlView = this.clientFactory.getPControlView();
		pControlView.setPresenter(this);
		containerWidget.setWidget(pControlView.asWidget());
		
	}
	
}

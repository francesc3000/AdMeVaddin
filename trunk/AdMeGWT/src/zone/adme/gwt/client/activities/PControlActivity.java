package zone.adme.gwt.client.activities;

import zone.adme.gwt.client.ClientFactory;
import zone.adme.gwt.client.events.OpenCellListEvent;
import zone.adme.gwt.client.events.PControlClickedEvent;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.views.PControlView;

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

	@Override
	public boolean start() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.clientFactory.getEventBus());
		}
		return true;
	}

	@Override
	public boolean stop() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
		return true;
	}
	
	@EventHandler
	void onShowPControl(PControlClickedEvent event){

	}

	public boolean OpenCellListClick() {
		this.clientFactory.getEventBus().fireEvent(new OpenCellListEvent());
		
		return true;
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		PControlView pControlView = this.clientFactory.getPControlView();
		pControlView.setPresenter(this);
		containerWidget.setWidget(pControlView.asWidget());
		
	}
	
}

package zone.adme.gwt.client.activities;

import javax.inject.Inject;

import zone.adme.gwt.client.events.BuscarEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.places.ShowPlace;
import zone.adme.gwt.client.views.interfaces.BuscaView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class BuscaIniActivity extends BaseActivity<BuscaView> implements Activity, BuscaView.Presenter{
	interface MyEventBinder extends EventBinder<BuscaIniActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	@Inject
    PlaceControllerHolder placeControllerHolder;
	
	@EventHandler
	void onUserRegistered(UserRegisteredEvent event){
		
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.placeControllerHolder.getEventBus());
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}

	@Override
	public void onStop() {
		this.stopHandler();
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.startHandler();
	}

	@Override
	public void goTo(Place place) {
		this.placeControllerHolder.getPlaceController().goTo(place);
	}

	@Override
	public void buscar() {
		this.placeControllerHolder.getPlaceController().goTo(new ShowPlace("Busca"));
		this.placeControllerHolder.getEventBus().fireEvent(new BuscarEvent("Hola"));
	}	
}

package zone.adme.gwt.client;

import zone.adme.gwt.client.views.interfaces.MainView;
import zone.adme.gwt.client.views.interfaces.PControlView;
import zone.adme.gwt.client.views.interfaces.SignView;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();
	SignView getSignView();
	PControlView getPControlView();
	MainView getMainView();
}

package zone.adme.gwt.client;

import zone.adme.gwt.client.views.PControlView;
import zone.adme.gwt.client.views.SignView;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();
	SignView getSignView();
	PControlView getPControlView();
}

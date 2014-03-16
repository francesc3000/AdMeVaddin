package zone.adme.gwt.client;

import zone.adme.gwt.client.views.MainViewImpl;
import zone.adme.gwt.client.views.PControlViewImpl;
import zone.adme.gwt.client.views.SignViewImpl;
import zone.adme.gwt.client.views.interfaces.MainView;
import zone.adme.gwt.client.views.interfaces.PControlView;
import zone.adme.gwt.client.views.interfaces.SignView;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory
{
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final SignView signView = new SignViewImpl();
	private static final PControlView pControlView = new PControlViewImpl();
	private static final MainView mainView = new MainViewImpl();
	
	@Override
	public EventBus getEventBus() {
		return  eventBus;
	}

	@Override
	public PlaceController getPlaceController()
	{
		return placeController;
	}

	@Override
	public SignView getSignView() {
		return signView;
	}

	@Override
	public PControlView getPControlView() {
		return pControlView;
	}

	@Override
	public MainView getMainView() {
		return mainView;
	}

}

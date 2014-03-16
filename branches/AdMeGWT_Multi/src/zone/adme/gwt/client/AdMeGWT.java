package zone.adme.gwt.client;

import zone.adme.gwt.client.history.AppActivityMapper;
import zone.adme.gwt.client.history.AppPlaceHistoryMapper;
import zone.adme.gwt.client.places.MainPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
	private Place defaultPlace = new MainPlace("Main");
	private SimplePanel appWidget = new SimplePanel();

  @Override
  public void onModuleLoad() {
	// Create ClientFactory using deferred binding so we can replace with different
	// impls in gwt.xml
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	EventBus eventBus = clientFactory.getEventBus();
	PlaceController placeController = clientFactory.getPlaceController();

	// Start ActivityManager for the main widget with our ActivityMapper
	ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
	ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
	activityManager.setDisplay(appWidget);

	// Start PlaceHistoryHandler with our PlaceHistoryMapper
	AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
	PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
	historyHandler.register(placeController, eventBus, defaultPlace);

	RootLayoutPanel.get().add(appWidget);
	// Goes to place represented on URL or default place
	historyHandler.handleCurrentHistory();
  }
}

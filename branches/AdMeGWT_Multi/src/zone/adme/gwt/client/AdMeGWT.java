package zone.adme.gwt.client;

import zone.adme.gwt.client.infrastructure.injection.MyGinjector;
import zone.adme.gwt.client.mapper.AppPlaceHistoryMapper;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.places.InitPlace;
import zone.adme.gwt.client.views.interfaces.RootView;
import zone.adme.gwt.client.views.regions.Region;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
	private final MyGinjector injector = GWT.create(MyGinjector.class);

  @Override
  public void onModuleLoad() {
	  
	RootView.Presenter rootPresenter = injector.getRootPresenter();

    PlaceControllerHolder placeControllerHolder = injector.getPlaceControllerHolder();

    // Initialize a manager per region
    ActivityManager northActivityManager = new ActivityManager(injector.getNorthActivityMapper(), placeControllerHolder.getEventBus());
    northActivityManager.setDisplay(rootPresenter.getView().getRegion(Region.North));

    ActivityManager westActivityManager = new ActivityManager(injector.getWestActivityMapper(), placeControllerHolder.getEventBus());
    westActivityManager.setDisplay(rootPresenter.getView().getRegion(Region.West));
    
    ActivityManager centerActivityManager = new ActivityManager(injector.getCenterActivityMapper(), placeControllerHolder.getEventBus());
    centerActivityManager.setDisplay(rootPresenter.getView().getRegion(Region.Center));

    // Do the same for center, south ...


    AppPlaceHistoryMapper historyMapper= injector.getAppPlaceHistoryMapper();
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeControllerHolder.getPlaceController(), placeControllerHolder.getEventBus(), new InitPlace("test"));

    RootLayoutPanel.get().add(rootPresenter.getView().asWidget());

    // Activate navigation history and go to default place
    historyHandler.handleCurrentHistory();
  }
}

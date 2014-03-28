package zone.adme.gwt.client.infrastructure.injection;

import zone.adme.gwt.client.mapper.AppPlaceHistoryMapper;
import zone.adme.gwt.client.mapper.CenterActivityMapper;
import zone.adme.gwt.client.mapper.NorthActivityMapper;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.mapper.SouthActivityMapper;
import zone.adme.gwt.client.mapper.WestActivityMapper;
import zone.adme.gwt.client.views.interfaces.RootView;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(MyModule.class)
public interface MyGinjector extends Ginjector
{
    RootView.Presenter getRootPresenter();

    PlaceControllerHolder getPlaceControllerHolder();

    AppPlaceHistoryMapper getAppPlaceHistoryMapper();

    NorthActivityMapper getNorthActivityMapper();
    
    CenterActivityMapper getCenterActivityMapper();

    WestActivityMapper getWestActivityMapper();
    
    SouthActivityMapper getSouthActivityMapper();
}

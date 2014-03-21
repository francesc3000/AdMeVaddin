package zone.adme.gwt.client.views.interfaces;

import zone.adme.gwt.client.views.regions.Region;
import zone.adme.gwt.client.views.regions.RegionContainer;

import com.google.gwt.user.client.ui.IsWidget;

public interface RootView extends IsWidget
{
    void setRegionVisibility(Region region, boolean visible);

    RegionContainer getRegion(Region region);

    interface Presenter extends zone.adme.gwt.client.presenters.Presenter<RootView>
    {

    }
}

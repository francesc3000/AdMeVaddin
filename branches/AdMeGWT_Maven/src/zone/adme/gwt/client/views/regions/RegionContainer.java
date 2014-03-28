package zone.adme.gwt.client.views.regions;

import zone.adme.gwt.client.views.interfaces.RootView;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface RegionContainer extends HasOneWidget, IsWidget
{
    void setVisible(boolean visibility);

    void setStyleName(String style);

    RootView getRootView();

    void setRootView(RootView rootView);

    void setRegionw(Region region);

    Region getRegion();
}

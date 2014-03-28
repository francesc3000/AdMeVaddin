package zone.adme.gwt.client.views;

import javax.inject.Inject;

import zone.adme.gwt.client.views.interfaces.RootView;
import zone.adme.gwt.client.views.regions.Region;
import zone.adme.gwt.client.views.regions.RegionContainer;
import zone.adme.gwt.client.views.regions.RegionContainerGetterVisitor;
import zone.adme.gwt.client.views.regions.RegionVisibilityVisitor;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class RootViewImpl extends Composite implements RootView
{
    @UiField
    DockLayoutPanel rootContainer;

    @Inject
    @UiField(provided = true)
    RegionContainer northRegionContainer;

    @Inject
    @UiField(provided = true)
    RegionContainer westRegionContainer;

    @Inject
    @UiField(provided = true)
    RegionContainer eastRegionContainer;

    @Inject
    @UiField(provided = true)
    RegionContainer southRegionContainer;

    @Inject
    @UiField(provided = true)
    RegionContainer centerRegionContainer;

    private RegionVisibilityVisitor regionVisibilityVisitor;

    private RegionContainerGetterVisitor regionContainerGetter;

    @UiTemplate("RootView.ui.xml")
    interface RootViewUiBinder extends UiBinder<Widget, RootViewImpl>
    {
    }

    @Inject
    void init(RootViewUiBinder binder)
    {
        initWidget(binder.createAndBindUi(this));
        regionVisibilityVisitor = new RegionVisibilityVisitor(northRegionContainer, southRegionContainer, eastRegionContainer, westRegionContainer,
                centerRegionContainer, rootContainer);
        regionContainerGetter = new RegionContainerGetterVisitor(northRegionContainer, southRegionContainer, eastRegionContainer, westRegionContainer,
                centerRegionContainer);

        northRegionContainer.setRootView(this);

        westRegionContainer.setRootView(this);
        eastRegionContainer.setRootView(this);
        southRegionContainer.setRootView(this);
        centerRegionContainer.setRootView(this);

        northRegionContainer.setRegionw(Region.North);
        westRegionContainer.setRegionw(Region.West);
        eastRegionContainer.setRegionw(Region.East);
        southRegionContainer.setRegionw(Region.South);
        centerRegionContainer.setRegionw(Region.Center);

    }


    @Override
    public void setRegionVisibility(Region region, boolean visible)
    {
        region.accept(regionVisibilityVisitor, visible);
    }

    @Override
    public RegionContainer getRegion(Region region)
    {
        return region.accept(regionContainerGetter, null);
    }

}

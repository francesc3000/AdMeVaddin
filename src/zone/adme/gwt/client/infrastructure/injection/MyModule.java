package zone.adme.gwt.client.infrastructure.injection;

import javax.inject.Singleton;

import zone.adme.gwt.client.activities.BuscaIniActivity;
import zone.adme.gwt.client.activities.ShowActivity;
import zone.adme.gwt.client.activities.NorthSingletonActivity;
import zone.adme.gwt.client.activities.RegisterActivity;
import zone.adme.gwt.client.activities.SignActivity;
import zone.adme.gwt.client.activities.SouthActivity;
import zone.adme.gwt.client.activities.WestSingletonActivity;
import zone.adme.gwt.client.mapper.AppPlaceHistoryMapper;
import zone.adme.gwt.client.mapper.CenterPlaceToActivityVisitor;
import zone.adme.gwt.client.mapper.NorthPlaceToActivityVisitor;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.mapper.SouthPlaceToActivityVisitor;
import zone.adme.gwt.client.mapper.WestPlaceToActivityVisitor;
import zone.adme.gwt.client.presenters.RootPresenter;
import zone.adme.gwt.client.views.BuscaViewImpl;
import zone.adme.gwt.client.views.PubliCellViewImpl;
import zone.adme.gwt.client.views.ShowViewImpl;
import zone.adme.gwt.client.views.PControlViewImpl;
import zone.adme.gwt.client.views.RegisterViewImpl;
import zone.adme.gwt.client.views.RootViewImpl;
import zone.adme.gwt.client.views.SignViewImpl;
import zone.adme.gwt.client.views.interfaces.BuscaView;
import zone.adme.gwt.client.views.interfaces.ShowView;
import zone.adme.gwt.client.views.interfaces.PControlView;
import zone.adme.gwt.client.views.interfaces.RegisterView;
import zone.adme.gwt.client.views.interfaces.RootView;
import zone.adme.gwt.client.views.interfaces.SignView;
import zone.adme.gwt.client.views.north.NorthView1;
import zone.adme.gwt.client.views.north.NothView1Impl;
import zone.adme.gwt.client.views.regions.RegionContainer;
import zone.adme.gwt.client.views.regions.SimpleLayoutPanelRegionContainer;
import zone.adme.gwt.client.views.south.SouthView;
import zone.adme.gwt.client.views.south.SouthViewImpl;
import zone.adme.gwt.client.views.west.WestView;
import zone.adme.gwt.client.views.west.WestViewImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class MyModule extends AbstractGinModule
{
    @Override
    protected void configure()
    {
        // Views
        bind(RootView.class).to(RootViewImpl.class).in(Singleton.class);
        bind(RegionContainer.class).to(SimpleLayoutPanelRegionContainer.class);

        bind(NorthView1.class).to(NothView1Impl.class);
        bind(WestView.class).to(WestViewImpl.class);
        bind(SignView.class).to(SignViewImpl.class);
        bind(RegisterView.class).to(RegisterViewImpl.class);
        bind(PControlView.class).to(PControlViewImpl.class);
        bind(BuscaView.class).to(BuscaViewImpl.class);
        bind(ShowView.class).to(ShowViewImpl.class);
        bind(PubliCellViewImpl.class);
        bind(SouthView.class).to(SouthViewImpl.class);

        // Presenters
        bind(RootView.Presenter.class).to(RootPresenter.class).in(Singleton.class);

        bind(AppPlaceHistoryMapper.class);
        bind(PlaceControllerHolder.class).in(Singleton.class);
        bind(EventBus.class).to(SimpleEventBus.class);

        bind(NorthPlaceToActivityVisitor.class);
        bind(NorthSingletonActivity.class);
        bind(SignActivity.class).in(Singleton.class);
        
        bind(CenterPlaceToActivityVisitor.class);
        bind(RegisterActivity.class);
        bind(BuscaIniActivity.class);
        bind(ShowActivity.class);

        bind(WestPlaceToActivityVisitor.class);
        // Make this activity a singleton to always use the same activity in west region
        bind(WestSingletonActivity.class);
        
        bind(SouthPlaceToActivityVisitor.class);
        bind(SouthActivity.class);
    }

}

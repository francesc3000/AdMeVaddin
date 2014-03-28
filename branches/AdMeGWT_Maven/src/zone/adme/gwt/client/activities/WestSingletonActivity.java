package zone.adme.gwt.client.activities;

import javax.inject.Inject;

import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.views.west.WestView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class WestSingletonActivity extends BaseActivity<WestView> implements Activity, WestView.Presenter
{
    @Inject
    PlaceControllerHolder placeControllerHolder;

    @Override
    public void onStop()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(AcceptsOneWidget panel)
    {
        panel.setWidget(getView());
        getView().setPresenter(this);
    }
}

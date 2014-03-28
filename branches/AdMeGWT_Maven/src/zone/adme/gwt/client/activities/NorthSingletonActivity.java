package zone.adme.gwt.client.activities;

import zone.adme.gwt.client.views.north.NorthView1;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NorthSingletonActivity extends BaseActivity<NorthView1> implements Activity
{

    @Override
    public void onStop()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(AcceptsOneWidget panel)
    {
        panel.setWidget(getView());
    }

}

package zone.adme.gwt.client.views.south;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SouthViewImpl extends Composite implements SouthView
{
    private Presenter presenter;

    @Inject
    public SouthViewImpl(WestViewUiBinder binder)
    {
        initWidget(binder.createAndBindUi(this));
    }

    @UiTemplate("SouthView.ui.xml")
    interface WestViewUiBinder extends UiBinder<Widget, SouthViewImpl>
    {

    }

    @UiHandler("place1Button")
    void click1(ClickEvent event)
    {
    }

    @UiHandler("place2Button")
    void click2(ClickEvent event)
    {
    }

    @Override
    public void setPresenter(Presenter presenter)
    {
        this.presenter = presenter;
    }

}

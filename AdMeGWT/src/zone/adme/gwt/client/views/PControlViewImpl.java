package zone.adme.gwt.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PControlViewImpl extends Composite implements PControlView {

	private static PControlUIUiBinder uiBinder = GWT.create(PControlUIUiBinder.class);
	private Presenter presenter = null;

	interface PControlUIUiBinder extends UiBinder<Widget, PControlViewImpl> {
	}

	public PControlViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	@UiHandler("button")
	void onOpenCellListClick(ClickEvent e) {
		this.presenter.OpenCellListClick();
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}

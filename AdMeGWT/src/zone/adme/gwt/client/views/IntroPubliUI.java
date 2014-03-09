package zone.adme.gwt.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IntroPubliUI extends Composite {

	private static IntroPubliUiBinder uiBinder = GWT
			.create(IntroPubliUiBinder.class);

	interface IntroPubliUiBinder extends UiBinder<Widget, IntroPubliUI> {
	}

	public IntroPubliUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}

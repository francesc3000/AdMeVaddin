package zone.adme.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IntroPubli extends Composite {

	private static IntroPubliUiBinder uiBinder = GWT
			.create(IntroPubliUiBinder.class);

	interface IntroPubliUiBinder extends UiBinder<Widget, IntroPubli> {
	}

	public IntroPubli() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}

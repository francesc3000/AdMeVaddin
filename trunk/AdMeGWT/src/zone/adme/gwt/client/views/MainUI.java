package zone.adme.gwt.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainUI extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	interface MainUiBinder extends UiBinder<Widget, MainUI> {
	}

	public MainUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	FlowPanel norte;
	@UiField
	FlowPanel centro;
	
	public FlowPanel getNorte() {
		return norte;
	}


	public void setNorte(FlowPanel norte) {
		this.norte = norte;
	}


	public FlowPanel getCentro() {
		return centro;
	}


	public void setCentro(FlowPanel centro) {
		this.centro = centro;
	}

}

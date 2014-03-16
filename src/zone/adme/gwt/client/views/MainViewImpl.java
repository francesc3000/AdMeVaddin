package zone.adme.gwt.client.views;

import zone.adme.gwt.client.views.interfaces.MainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MainViewImpl extends Composite implements MainView, ProvidesResize {

	private static MainViewImplUiBinder uiBinder = GWT
			.create(MainViewImplUiBinder.class);
	
	@SuppressWarnings("unused")
	private Presenter presenter = null;

	interface MainViewImplUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//Utilizamos el siguiente método para que aprezca correctamente
		//el DockLayoutPanel
		getElement().removeAttribute("style");
		
		buscaBtn.setTitle("Buscar");
	}
	
	@UiField
	FlowPanel norte;
	@UiField
	FlowPanel centro;
	@UiField
	TextBox buscaTBox;
	@UiField
	Button buscaBtn;
	
	@Override
	public FlowPanel getNorte(){
		return norte;
	}
	
	@Override
	public FlowPanel getCentro(){
		return centro;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}

package zone.adme.gwt.client.views;

import javax.inject.Inject;

import zone.adme.gwt.client.views.interfaces.BuscaView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BuscaViewImpl extends Composite implements BuscaView { //ProvidesResize {

	private static MainViewImplUiBinder uiBinder = GWT
			.create(MainViewImplUiBinder.class);
	
	private Presenter presenter = null;

	@UiTemplate("BuscaView.ui.xml")
	interface MainViewImplUiBinder extends UiBinder<Widget, BuscaViewImpl> {
	}

	@Inject
	public BuscaViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//Utilizamos el siguiente método para que aprezca correctamente
		//el DockLayoutPanel
		//getElement().removeAttribute("style");
		
		buscaBtn.setTitle("Buscar");
		buscaBtn.setText("Busca");
	}
	
	@UiField
	TextBox buscaTBox;
	@UiField
	Button buscaBtn;

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("buscaBtn")
	void onBuscaBtnClick(ClickEvent e){
		this.presenter.buscar();
	}

}

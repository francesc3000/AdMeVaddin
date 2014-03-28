package zone.adme.gwt.client.views;

import zone.adme.gwt.client.views.interfaces.FormPubliView;
import zone.adme.gwt.client.views.interfaces.ShowView.Presenter;
import zone.adme.gwt.shared.PubliGWT;

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

public class FormPubliViewImpl extends Composite implements FormPubliView {

	private static FormPubliViewImplBinder uiBinder = GWT
			.create(FormPubliViewImplBinder.class);

	@UiTemplate("FormPubliView.ui.xml")
	interface FormPubliViewImplBinder extends UiBinder<Widget, FormPubliViewImpl> {
	}
	
	private Presenter presenter = null;

	public FormPubliViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	Button crearPubli;
	@UiField
	TextBox tituloPubli;
	@UiField
	TextBox textoPubli;
	
	@UiHandler("crearPubli")
	void OnClick1(ClickEvent e){
	
	
		PubliGWT publi = new PubliGWT();
		
		publi.setUsuario("Jose Andres");
		publi.setTexto((textoPubli.getText()));
		publi.setTitulo(tituloPubli.getText());
	
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}

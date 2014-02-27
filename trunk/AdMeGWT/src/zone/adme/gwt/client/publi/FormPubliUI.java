package zone.adme.gwt.client.publi;

import java.util.List;

import zone.adme.gwt.client.services.PubliService;
import zone.adme.gwt.client.services.PubliServiceAsync;
import zone.adme.gwt.shared.MensajeGWT;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FormPubliUI extends Composite {

	private static FormPubliUiBinder uiBinder = GWT
			.create(FormPubliUiBinder.class);

	interface FormPubliUiBinder extends UiBinder<Widget, FormPubliUI> {
	}

	public FormPubliUI() {
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
	

		
		final PubliServiceAsync PubliService = GWT.create(PubliService.class);
		
		PubliService.putPubli(publi,new AsyncCallback<List<MensajeGWT>>() {


			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<MensajeGWT> listaMensajeGWT) {
				Window.alert("Siiiii...Ole!");
				
			}	
		});
	}

}

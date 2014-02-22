package zone.adme.gwt.client;

import java.util.Iterator;
import java.util.List;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowPubli extends Composite {

	private static ShowPubliUiBinder uiBinder = GWT
			.create(ShowPubliUiBinder.class);

	interface ShowPubliUiBinder extends UiBinder<Widget, ShowPubli> {
	}

	public ShowPubli() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	Button btn;
	@UiField
	FlowPanel norte;
	@UiField
	FlowPanel centro;


	public FlowPanel getCentro() {
		return centro;
	}


	public void setCentro(FlowPanel centro) {
		this.centro = centro;
	}


	@UiHandler("btn")
	void OnClick(ClickEvent e){
		
		RootPanel.get().clear();
		
		final FlexTable t = new FlexTable();
		
		t.setText(0, 0, "Usuario");  
		t.setText(0, 1, "Titulo");  
		t.setText(0, 2, "Publicacion");
		t.getRowFormatter().addStyleName(0, "watchListHeader");
		RootPanel.get().add(t);
		
		final GetPubliServiceAsync getPubliService = GWT.create(GetPubliService.class);
		getPubliService.getPubli(new AsyncCallback<List<PubliGWT>>() {


			public void onSuccess(List<PubliGWT> result) {


				Iterator<PubliGWT> iter = result.iterator();
				int i=1;


				while (iter.hasNext()){

					PubliGWT publi = (PubliGWT) iter.next();

					t.setText(i, 0,publi.getUsuario() );
					t.setText(i, 1,publi.getTitulo() );
					t.setText(i, 2,publi.getTexto());	
					i++;
					
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
		
	}


	public FlowPanel getNorte() {
		return norte;
	}


	public void setNorte(FlowPanel norte) {
		this.norte = norte;
	}
	
	

}

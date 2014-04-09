package zone.adme.gwt.client.activities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zone.adme.core.constantes.Constante.ConstanteOperador;
import zone.adme.core.constantes.Constante.ConstantePubli;
import zone.adme.core.constantes.Constante.Tabla;
import zone.adme.core.modelo.Metadato;
import zone.adme.gwt.client.events.BuscarEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.services.PubliService;
import zone.adme.gwt.client.services.PubliServiceAsync;
import zone.adme.gwt.client.views.PubliCellViewImpl;
import zone.adme.gwt.client.views.interfaces.ShowView;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class ShowActivity extends BaseActivity<ShowView> implements Activity, ShowView.Presenter{
	interface MyEventBinder extends EventBinder<ShowActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	@Inject
    private PlaceControllerHolder placeControllerHolder;
	
	private String conceptoBusqueda;
	private final PubliServiceAsync getPubliService = GWT.create(PubliService.class);
	
	@EventHandler
	void onUserRegistered(UserRegisteredEvent event){
		
	}
	
	@EventHandler
	void onBusqueda(BuscarEvent event){
		this.conceptoBusqueda = event.getBusqueda();
		
		List<Metadato> listaMetadato = new ArrayList<Metadato>();
		listaMetadato.add(new Metadato(Tabla.PUBLICACION,ConstantePubli.TITULO,ConstanteOperador.EQUAL,this.conceptoBusqueda));
		
		getPubliService.getPubliGWT(listaMetadato, new AsyncCallback<List<PubliGWT>>() {
			public void onSuccess(List<PubliGWT> result) {
				setTableData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.placeControllerHolder.getEventBus());
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}

	@Override
	public void onStop() {
		this.stopHandler();
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.startHandler();
	}

	@Override
	public void goTo(Place place) {
		this.placeControllerHolder.getPlaceController().goTo(place);
	}

	@Override
	public void setTableData(List<PubliGWT> listaPubliGWT) {
		
		PubliCellViewImpl cell = new PubliCellViewImpl();
	    CellList<PubliGWT> cellList = new CellList<PubliGWT>(cell);
		List<PubliGWT> list = new ArrayList<PubliGWT>();
		
		
		this.view.setFlexTable(listaPubliGWT);
		
		for(PubliGWT publiGWT : listaPubliGWT){
			list.add(publiGWT);
		}
		
		cellList.setRowCount(list.size(), true);  
	    cellList.setRowData(list);

		this.view.setCellTable(cellList);
	}	
}

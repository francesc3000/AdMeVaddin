package zone.adme.gwt.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class BuscarEvent extends GenericEvent{
	
	private String busqueda;
	
	public BuscarEvent(String busqueda){
		this.busqueda = busqueda;
	}
	
	public String getBusqueda(){
		return this.busqueda;
	}

}

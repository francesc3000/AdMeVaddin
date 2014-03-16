package zone.adme.gwt.client.events;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class UserRegisteredEvent extends GenericEvent{

	private UsuarioGWT usuarioGWT = null;
	
	public UserRegisteredEvent(UsuarioGWT usuarioGWT){
		this.usuarioGWT = usuarioGWT;
	}

	public UsuarioGWT getUsuarioGWT() {
		return usuarioGWT;
	}
}

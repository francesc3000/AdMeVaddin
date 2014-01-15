package zone.adme.gwt.client;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsuarioServiceAsync {

	void getUsuarioServer(String correo, AsyncCallback<UsuarioGWT> callback);

	void putUsuarioServer(UsuarioGWT usuario, AsyncCallback<Boolean> callback);

}

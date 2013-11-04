package zone.adme.client;


import zone.adme.core.modelo.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ControladorGWTAsync {

	void acceder(AsyncCallback<Usuario> callback);

}

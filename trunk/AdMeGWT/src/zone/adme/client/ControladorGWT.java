package zone.adme.client;

import zone.adme.core.modelo.Usuario;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("controladorGWT")
public interface ControladorGWT extends RemoteService {
	
	Usuario acceder();

}

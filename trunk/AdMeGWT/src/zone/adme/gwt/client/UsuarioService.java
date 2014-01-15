package zone.adme.gwt.client;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usuarioService")
public interface UsuarioService extends RemoteService {
	UsuarioGWT getUsuarioServer(String correo);
	boolean putUsuarioServer(UsuarioGWT usuario);
}

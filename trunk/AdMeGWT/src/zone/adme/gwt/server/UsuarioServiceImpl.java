package zone.adme.gwt.server;

import zone.adme.core.modelo.Usuario;
import zone.adme.gwt.client.UsuarioService;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UsuarioServiceImpl extends RemoteServiceServlet implements
		UsuarioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1226848944633675013L;
	
	private ControladorCore controladorCore = new ControladorCore();

	@Override
	public UsuarioGWT getUsuarioServer(String correo) {
		UsuarioGWT usuarioGWT = null;
		usuarioGWT = controladorCore.getUsuario("francesc3000@gmail.com");
		return usuarioGWT;
	}

	@Override
	public boolean putUsuarioServer(UsuarioGWT usuario) {
		
		return false;
	}

}

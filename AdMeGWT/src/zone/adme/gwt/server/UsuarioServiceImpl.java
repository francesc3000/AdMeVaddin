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

	@Override
	public UsuarioGWT getUsuarioServer(String correo) {
		Usuario usuario = new Usuario("Francesc","Francesc","Francesc", "Francesc", "Francesc");
		UsuarioGWT usuariogwt = new UsuarioGWT(usuario.getCorreo());
		usuariogwt.setNombre(usuario.getNombre());
		return usuariogwt;
	}

}

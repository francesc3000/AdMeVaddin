package zone.adme.gwt.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zone.adme.gwt.client.sign.SignService;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SignServiceImpl extends RemoteServiceServlet implements
SignService{

	/**
	 * SECCIÓN PRIVADA
	 */
	private static final long serialVersionUID = 1L;
	private ControladorCore controladorCore = new ControladorCore();
	
	/**
	 * SECCIÓN PUBLICA
	 */
	public static final String SESSION_USUARIO = "SessionUsuario";
	
	@Override
	public UsuarioGWT signIn(String correo, String contrasena) {
		UsuarioGWT usuarioGWT = controladorCore.getUsuario(correo);
		if(usuarioGWT!=null){
			if(usuarioGWT.getContrasena()!=contrasena){
				usuarioGWT.setTipoMensaje("E");
				usuarioGWT.setMensaje("Contraseña Incorrecta!");
			}else{
				// create session and store userid
				HttpServletRequest request = this.getThreadLocalRequest();
				//true will create a new session if it not yet exists
				HttpSession session = request.getSession(true);
				session.setAttribute("Usuario", usuarioGWT);
			}
		}
		return usuarioGWT;
	}

	@Override
	public boolean signOut(String correo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsuarioGWT getUsuarioSession() {
		// create session and store userid
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession(true);
		return (UsuarioGWT) session.getAttribute(SESSION_USUARIO);
	}

	@Override
	public UsuarioGWT register(String correo, String contrasena, String nombre,
			String apellido1, String apellido2) {
		UsuarioGWT usuarioGWT = new UsuarioGWT(correo);
		usuarioGWT.setContrasena(contrasena);
		usuarioGWT.setNombre(nombre);
		usuarioGWT.setApellido1(apellido1);
		usuarioGWT.setApellido2(apellido2);
		controladorCore.putUsuario(usuarioGWT);
		
		// create session and store userid
		HttpServletRequest request = this.getThreadLocalRequest();
		//true will create a new session if it not yet exists
		HttpSession session = request.getSession(true);
		session.setAttribute("Usuario", usuarioGWT);
		return null;
	}
}

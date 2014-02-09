package zone.adme.gwt.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zone.adme.gwt.client.sign.SignService;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SignServiceImpl extends RemoteServiceServlet implements
SignService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorCore controladorCore = new ControladorCore();
	
	@Override
	public UsuarioGWT signIn(String correo) {
		UsuarioGWT usuarioGWT = controladorCore.getUsuario(correo);
		if(usuarioGWT==null){
			usuarioGWT = new UsuarioGWT(correo);
			controladorCore.putUsuario(usuarioGWT);
		}
		// create session and store userid
		HttpServletRequest request = this.getThreadLocalRequest();
		//true will create a new session if it not yet exists
		HttpSession session = request.getSession(true);
		session.setAttribute("Usuario", usuarioGWT);
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
		return (UsuarioGWT) session.getAttribute("Usuario");
	}

}

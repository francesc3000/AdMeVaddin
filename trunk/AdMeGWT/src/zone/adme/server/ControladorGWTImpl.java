package zone.adme.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zone.adme.client.ControladorGWT;
import zone.adme.core.controlador.ControladorModelo;
import zone.adme.core.modelo.Usuario;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ControladorGWTImpl extends RemoteServiceServlet implements ControladorGWT{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorModelo cm = new ControladorModelo();
	@Override
	public Usuario acceder(){
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        	try {
				cm.acceder(httpServletRequest, session);
			} catch (IOException e) {
				return null;
			}
        	
		return null;
	}

}

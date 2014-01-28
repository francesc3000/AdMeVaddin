package zone.adme.gwt.server;


import zone.adme.gwt.client.IntroPubliService;
import zone.adme.gwt.shared.PubliGWT;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class IntroPubliServiceImpl extends RemoteServiceServlet implements IntroPubliService{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void introducePubli(PubliGWT publiGWT) {
		//HttpServletRequest request = this.getThreadLocalRequest();
				// dont create a new one -> false
				//HttpSession session = request.getSession(false);

				//session.getAttribute(arg0);
				
				ControladorCore cc = new ControladorCore();
				
				
				//cc.putPubli(Session.getInstance(properties), publi);
				cc.putPubli(new UsuarioGWT("a@a.com"), publiGWT);
		
	}

}

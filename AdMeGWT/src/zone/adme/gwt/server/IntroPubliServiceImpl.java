package zone.adme.gwt.server;


import java.util.List;

import zone.adme.gwt.client.IntroPubliService;
import zone.adme.gwt.shared.MensajeGWT;
import zone.adme.gwt.shared.PubliGWT;

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
				List<MensajeGWT> listaMensajeGWT = cc.putPubli(publiGWT);
		
	}

}

package zone.adme.gwt.server;

import java.util.List;

import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Metadato;
import zone.adme.gwt.client.services.PubliService;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class PubliServiceImpl extends RemoteServiceServlet implements
PubliService {


	private static final long serialVersionUID = -6238344776565658480L;
	private ControladorCore controladorCore = new ControladorCore();

	@Override
	public List<PubliGWT> getPubliGWT(List<Metadato> listaMetadato) throws IllegalArgumentException {
		/*
		List<PubliGWT> lista = new ArrayList<PubliGWT>();
		
		PubliGWT p1 = new PubliGWT();
		
		//p1.setUsuarioGWT(new UsuarioGWT("Alberto@com.com"));
		p1.setTitulo("TITULO 1");
		p1.setTexto("Esta es la publicacion numero 1");
		
		PubliGWT p2 = new PubliGWT();
		
		//p2.setUsuarioGWT(new UsuarioGWT("Alfonso.Javier@com.com"));
		p2.setTitulo("TITULO 2");
		p2.setTexto("Esta es la publicacion numero 2, iauygp aui apuihgpa aui gauerp ahit ");
		
		PubliGWT p3 = new PubliGWT();
		
		//p2.setUsuarioGWT(new UsuarioGWT("Luis.Euduardo@com.com"));
		p2.setTitulo("TITULO 322322");
		p2.setTexto("Eijhgzfuizs zuihaiu hapuhaiph pahipuah aouh uiahg apuhgap gapwuh pag au, iauygp aui apuihgpa aui gauerp ahit as oifasug auia"
				+ ""
				+ "aoifhauihfiau oia ioaiofaoihtaiohtia hiotaio hioah a+"
				+ "jaifhafha+p+aetp ajhio  oihhhhhhhhhhhhhhhh oiuioi haoih oìah oaihriohorahorahoi hoaih ioahoiahoiah oiah ioah "
				+ "awraw  ");
		
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
*/
		//return this.controladorCore.getListaPubliGWT(listaMetadato);
		
		
		return null;
	}

	@Override
	public List<Mensaje> putPubliGWT(PubliGWT publiGWT) throws IllegalArgumentException {
		//return this.controladorCore.putPubli(publiGWT);
		return null;
	}
}


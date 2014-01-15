package zone.adme.gwt.server;

import java.util.ArrayList;
import java.util.List;

import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Usuario;
import zone.adme.core.modelo.lista.ListaMetadato;
import zone.adme.gwt.shared.MetadatoGWT;
import zone.adme.gwt.shared.PubliGWT;
import zone.adme.gwt.shared.UsuarioGWT;

public class Dto {

	public Dto(){}
	
	public UsuarioGWT usuarioGWT(Usuario usuario){
		UsuarioGWT usuarioGWT = new UsuarioGWT(usuario.getCorreo());
		
		//Proceso de igualación
		
		return usuarioGWT;
	}
	
	public ListaMetadato listaMetadato(List<MetadatoGWT> ListaMetadatoGWT){
		
		ListaMetadato listaMetadato = new ListaMetadato();
		
		//Proceso de igualación
		
		return listaMetadato;
	}
	
	public List<PubliGWT> listaPubliGWT(List<Publi> listaPubli){
		
		List<PubliGWT> listaPubliGWT = new ArrayList<PubliGWT>();
		
		//Proceso de igualación
		
		return listaPubliGWT;
	}
}

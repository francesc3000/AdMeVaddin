package zone.adme.gwt.server;

import java.util.ArrayList;
import java.util.List;

import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Usuario;
import zone.adme.core.modelo.lista.ListaMensaje;
import zone.adme.core.modelo.lista.ListaMetadato;
import zone.adme.gwt.shared.MensajeGWT;
import zone.adme.gwt.shared.MetadatoGWT;
import zone.adme.gwt.shared.PubliGWT;
import zone.adme.gwt.shared.UsuarioGWT;

public class Dto {

	public Dto(){}
	
	public UsuarioGWT toUsuarioGWT(Usuario usuario){
		if(usuario!=null){
			//Proceso de igualación
			UsuarioGWT usuarioGWT = new UsuarioGWT(usuario.getCorreo());
			usuarioGWT.setContrasena(usuario.getContrasena());
			usuarioGWT.setNombre(usuario.getNombre());
			usuarioGWT.setApellido1(usuario.getApellido1());
			usuarioGWT.setApellido2(usuario.getApellido2());
			
			
			return usuarioGWT;
		}
		else{
			return null;
		}
	}
	
	public Usuario toUsuario(UsuarioGWT usuarioGWT){
		if(usuarioGWT!=null){
			//Proceso de igualación
			Usuario usuario = new Usuario(usuarioGWT.getCorreo(),usuarioGWT.getContrasena(),
										  usuarioGWT.getNombre(),usuarioGWT.getApellido1(),
										  usuarioGWT.getApellido2());
			
			
			
			return usuario;
		}
		else{
			return null;
		}
		
	}
	
	public ListaMetadato toListaMetadato(List<MetadatoGWT> ListaMetadatoGWT){
		
		ListaMetadato listaMetadato = new ListaMetadato();
		
		//Proceso de igualación
		
		return listaMetadato;
	}
	
	public List<PubliGWT> toListaPubliGWT(List<Publi> listaPubli){
		
		List<PubliGWT> listaPubliGWT = new ArrayList<PubliGWT>();
		
		//Proceso de igualación
		
		return listaPubliGWT;
	}
	
	public Publi toPubli(PubliGWT publiGWT) {
		
		//Proceso de igualación
		
		Publi publi = new Publi(new Usuario("a@a.com", "", "", "", ""), publiGWT.getTitulo(), publiGWT.getTexto(), "");
		return publi;
	}
	
	public List<MensajeGWT> toListaMensajeGWT(ListaMensaje listaMensaje){
		List<MensajeGWT> listaMensajeGWT = new ArrayList<MensajeGWT>();
		for(Mensaje mensaje:listaMensaje){
			MensajeGWT mensajeGWT = new MensajeGWT(mensaje.getTipo().toString(),mensaje.getMensaje());
			listaMensajeGWT.add(mensajeGWT);
		}
		
		return listaMensajeGWT;
	}
}

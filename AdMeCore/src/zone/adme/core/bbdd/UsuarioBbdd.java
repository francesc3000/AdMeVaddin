package zone.adme.core.bbdd;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import static zone.adme.core.bbdd.OfyService.ofy;

import zone.adme.core.modelo.Grupo;
import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Usuario;
import zone.adme.core.modelo.lista.ListaMensaje;
import zone.adme.core.modelo.lista.ListaMetadato;

public class UsuarioBbdd{
	
	public UsuarioBbdd(){}
	
	public Usuario getUsuario(String correo){
		
		Usuario usuario = ofy().load().type(Usuario.class).id(correo).now();
		
	    return usuario;
	}
	
	public Usuario getUsuarioByKey(Key<Usuario> key){
		
		Usuario usuario = ofy().load().key(key).now();
		ofy().save();
		return usuario;
	}
	
	public List<Usuario> getListaUsuarioByKey(List<Key<Usuario>> listaKey){
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		for(Key<Usuario> key:listaKey){
			listaUsuario.add(ofy().load().key(key).now());
		}
		ofy().save();
		return listaUsuario;
	}
	
	public List<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		return this.getListaUsuario(listaMetadato, null, null);
	}
	
	public List<Usuario> getListaUsuario(ListaMetadato listaMetadato, ArrayList<Grupo> listaGrupo, ArrayList<Publi> listaPubli){
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		return listaUsuario;
	}

	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().save().entity(usuario).now();
		
		return listaMensaje;
	}

	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().delete().entity(usuario);
		
		return listaMensaje;
	}
}

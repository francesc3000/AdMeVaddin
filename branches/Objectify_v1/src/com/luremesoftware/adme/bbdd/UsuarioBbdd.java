package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class UsuarioBbdd{
	
	private GestorGrupo gestorGrupo = new GestorGrupo();
	
	public UsuarioBbdd(){}
	
	public Usuario getUsuario(String correo){
		
		Usuario usuario = (Usuario) ofy().load().type(Usuario.class).filter("correo =", correo);
	    
	    return usuario;
	}
	
	public Usuario getUsuarioByKey(Key<Usuario> key){
		
		Usuario usuario = ofy().load().key(key).now();
		
		return usuario;
	}
	
	public List<Usuario> getListaUsuarioByKey(List<Key<Usuario>> listaKey){
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		for(Key<Usuario> key:listaKey){
			listaUsuario.add(ofy().load().key(key).now());
		}
		
		return listaUsuario;
	}
	
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		return this.getListaUsuario(listaMetadato, null, null);
	}
	
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato, ArrayList<Grupo> listaGrupo, ArrayList<Publi> listaPubli){
		ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		return listaUsuario;
	}

	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		ofy().save().entity(usuario).now();
        for(Grupo grupo:usuario.getListaGrupo()){
    		this.gestorGrupo.putGrupo(grupo);
    	}
		
		return listaMensaje;
	}

	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().delete().entity(usuario);
		
		return listaMensaje;
	}
}

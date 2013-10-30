package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import static com.luremesoftware.adme.bbdd.OfyService.ofy;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class UsuarioBbdd{
	
	private GestorGrupo gestorGrupo = new GestorGrupo();
	private GestorPubli gestorPubli = new GestorPubli();
	
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
		//Se guardan los grupos
		for(Grupo grupo:usuario.getGruposParaBBDD()){
			this.gestorGrupo.putGrupo(grupo);
		}
		//Se guardan las publicaciones
		for(Publi publi:usuario.getPublisParaBBDD()){
			this.gestorPubli.putPubli(publi);
		}
		
		return listaMensaje;
	}

	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().delete().entity(usuario);
		
		return listaMensaje;
	}
}

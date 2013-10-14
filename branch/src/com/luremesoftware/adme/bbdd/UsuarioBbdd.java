package com.luremesoftware.adme.bbdd;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.luremesoftware.adme.bbdd.PMF;

public class UsuarioBbdd extends Bbdd{

	private Query query = null;
	
	public UsuarioBbdd(){
		super();
		query = new Query(Tabla.USUARIO.toString());
	}
	
	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();

		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(usuario);
        } finally {
            pm.close();
        }
		
		return listaMensaje;
	}
	
	public Usuario getUsuario(String correo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Usuario.class.getName() + " where id == :correo";

	    @SuppressWarnings("unchecked")
		List<Usuario> listaUsuario = (List<Usuario>) pm.newQuery(query).execute(correo);
	    pm.close();
	    return listaUsuario.get(0);
	}

	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		return this.getListaUsuario(listaMetadato, null, null);
	}
	
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato, ListaGrupo listaGrupo){
		return this.getListaUsuario(listaMetadato, listaGrupo, null);
	}

	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato, ListaPubli listaPubli){
		return this.getListaUsuario(listaMetadato, null, listaPubli);
	}
	
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato, ListaGrupo listaGrupo, ListaPubli listaPubli){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		this.buildQuery(this.query, listaMetadato);
		
		PreparedQuery pq = this.datastore.prepare(query);

		for (Entity result : pq.asIterable()) {			
		  Usuario usuario = new Usuario(
		  (String) result.getProperty(ConstanteUsuario.CORREO.toString()),
		  (String) result.getProperty(ConstanteUsuario.CONTRASENA.toString()),
		  (String) result.getProperty(ConstanteUsuario.NOMBRE.toString()),
		  (String) result.getProperty(ConstanteUsuario.APELLIDO1.toString()),
		  (String) result.getProperty(ConstanteUsuario.APELLIDO2.toString()));
		  
		  if(listaGrupo != null){
			  usuario.setListaGrupo(listaGrupo);
		  }

		  if(listaPubli != null){
			  usuario.setListaPubli(listaPubli);
		  }
		  listaUsuario.add(usuario);
		}
		
		return listaUsuario;
	}
}

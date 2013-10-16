package com.luremesoftware.adme.bbdd;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;
import com.luremesoftware.adme.bbdd.PMF;
import com.luremesoftware.adme.constantes.Constante.Tabla;

public class UsuarioBbdd{
	
	public UsuarioBbdd(){}
	
	public ListaMensaje creaUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();

		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(usuario);
        } finally {
            pm.close();
        }
		
		return listaMensaje;
	}
	
	public ListaMensaje actualizaUsuario(Key key){
		//TODO implementar actualización
		Usuario usuario = null;
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
	    	usuario = pm.getObjectById(Usuario.class,key);
	    	
	    }catch (JDOObjectNotFoundException e) {
	        //TODO Crear mensaje de error
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
	
	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.deletePersistent(usuario);
	    }catch (JDOObjectNotFoundException e) {
	        //TODO Crear mensaje de error
	    } 
	    finally {
	        pm.close();
	    }
		
		
		
		return listaMensaje;
	}

	public Usuario getUsuario(String correo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Tabla.USUARIO + " where id == :correo";
	    
	    Usuario usuario = null;
	    try{
	    	@SuppressWarnings("unchecked")
	    	List<Usuario> listaUsuario = (List<Usuario>) pm.newQuery(query).execute(correo);
	    	if(!listaUsuario.isEmpty()){
		    	usuario = listaUsuario.get(0);
		    }
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	    return usuario;
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
		
		return listaUsuario;
	}
}

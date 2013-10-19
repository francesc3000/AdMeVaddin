package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.bbdd.PMF;
import com.luremesoftware.adme.constantes.Constante.Tabla;

public class UsuarioBbdd{
	
	public UsuarioBbdd(){}
	
	public Usuario getUsuario(String correo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Tabla.USUARIO + " where correo == :correo";
	    
	    Usuario usuario = null, detached = null;
	    try{
	    	@SuppressWarnings("unchecked")
	    	List<Usuario> listaUsuario = (List<Usuario>) pm.newQuery(query).execute(correo);
	    	if(!listaUsuario.isEmpty()){
		    	usuario = listaUsuario.get(0);
		    	detached = pm.detachCopy(usuario);
		    }
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	    
	    
	    return detached;
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
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        pm.makePersistent(usuario);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    }finally {
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
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
}

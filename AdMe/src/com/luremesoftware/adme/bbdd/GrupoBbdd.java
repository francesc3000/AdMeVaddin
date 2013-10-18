package com.luremesoftware.adme.bbdd;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class GrupoBbdd{
	
	public GrupoBbdd(){}
	
	public Grupo getGrupo(String nombreGrupo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Tabla.GRUPO + " where nombre == :nombreGrupo";
	    
	    Grupo grupo = null, detached = null;
	    try{
	    	grupo = (Grupo) pm.newQuery(query).execute(nombreGrupo);
		    detached = pm.detachCopy(grupo);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	    return detached;
	}
	
	public List<Grupo> getListaGrupo(Usuario usuario){
		return this.getListaGrupo(null, usuario);
	}
	
	public List<Grupo> getListaGrupo(ListaMetadato listaMetadato){
		return this.getListaGrupo(listaMetadato, null);
	}

	/**
	 * Este m�todo busca los grupos de un usuario
	 * @param listaMetadato
	 * @param usuario
	 * @return
	 */
	public List<Grupo> getListaGrupo(ListaMetadato listaMetadato, Usuario usuario){
		List<Grupo> listaGrupo = new List<Grupo>();
		
		return listaGrupo; 
	}

	public ListaMensaje putGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        pm.makePersistent(grupo);
	    }catch (JDOObjectNotFoundException e) {
	        listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}

	public ListaMensaje borraGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.deletePersistent(grupo);
	    }catch (JDOObjectNotFoundException e) {
	        listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
}

package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class GrupoBbdd{
	
	public GrupoBbdd(){}
	
	public Propietario getGrupo(String nombreGrupo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Tabla.GRUPO.getName() + " where nombre == :nombreGrupo";
	    
	    Propietario grupo = null, detached = null;
	    try{
	    	grupo = (Propietario) pm.newQuery(query).execute(nombreGrupo);
		    detached = pm.detachCopy(grupo);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	    return detached;
	}
	
	public List<Propietario> getListaGrupo(Propietario usuario){
		return this.getListaGrupo(null, usuario);
	}
	
	public List<Propietario> getListaGrupo(ListaMetadato listaMetadato){
		return this.getListaGrupo(listaMetadato, null);
	}

	/**
	 * Este método busca los grupos de un usuario
	 * @param listaMetadato
	 * @param usuario
	 * @return
	 */
	public List<Propietario> getListaGrupo(ListaMetadato listaMetadato, Propietario usuario){
		List<Propietario> listaGrupo = new ArrayList<Propietario>();
		
		return listaGrupo; 
	}

	public ListaMensaje putGrupo(Propietario grupo){
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

	public ListaMensaje borraGrupo(Propietario grupo){
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

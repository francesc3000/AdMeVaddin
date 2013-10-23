package com.luremesoftware.adme.bbdd;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class GrupoBbdd{
	
	public GrupoBbdd(){}
	
	public List<Grupo> getListaGrupo(ListaMetadato listaMetadato){
		return null;
	}
	
	public Grupo getGrupoByKey(Key key){
		Grupo grupo;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			grupo = pm.getObjectById(Grupo.class,key);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return grupo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> getListaGrupoByKey(List<Key> listaKey){
		List<Grupo> listaGrupo = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			listaGrupo = (List<Grupo>) pm.getObjectsById(listaKey);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaGrupo;
	}
	ListaMensaje listaMensaje = new ListaMensaje();
	
	public ListaMensaje putGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
	    try {
	    	tx.begin();
	        pm.makePersistent(grupo);
	        tx.commit();
	    }catch (JDOObjectNotFoundException e) {
	        listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	    	if (tx.isActive()) {
	            tx.rollback();
	        }
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

package com.luremesoftware.adme.bbdd;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;

public class PropietaroBbdd {
	
	public PropietaroBbdd(){}
	
	public Propietario getPropietario(String id){
		
		Key key = KeyFactory.createKey(Tabla.PROPIETARIO.getSimpleName(), id);
		
		return this.getPropietarioByKey(key);
		
	}
	
	public Propietario getPropietarioByKey(Key key){
		Propietario propietario = null, detached = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
	    	propietario = pm.getObjectById(Propietario.class,key);
	    	detached = pm.detachCopy(propietario);
	    	//detached.setListaPubli(pm.detachCopy(detached.getListaPubli()));
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return detached;
	}
	
	public ListaMensaje putPropietario(Propietario propietario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
	    	pm.makePersistent(propietario);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	    	tx.commit();
	        pm.close();
	    }	
		
		return listaMensaje;
	}

}

package com.luremesoftware.adme.bbdd;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.Propietario;

public class PropietaroBbdd {
	
	public PropietaroBbdd(){}
	
	public Propietario getPropietarioByKey(Key key){
		Propietario propietario;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
	    	propietario = pm.getObjectById(Propietario.class,key);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return propietario;
	}

}

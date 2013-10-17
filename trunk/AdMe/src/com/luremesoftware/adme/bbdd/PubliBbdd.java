package com.luremesoftware.adme.bbdd;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;

public class PubliBbdd{
	
	public PubliBbdd(){
	}
	
	public ListaPubli getListaPubli(Propietario propietario){
		ListaPubli listaPubli = new ListaPubli();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + Tabla.PUBLICACION + " where propietarioKey == :propietarioKey";
	    
	    try{
	    	@SuppressWarnings("unchecked")
	    	List<Publi> listaPubliList = (List<Publi>) pm.newQuery(query).execute(propietario.getId());
	    	for(Publi publi:listaPubliList){
				listaPubli.add(publi);
			}
	    }catch (JDOObjectNotFoundException e) {
	        //Do nothing
	    } 
	    finally {
	        pm.close();
	    }
	    
		return listaPubli;
	}
	
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		ListaPubli listaPubli = new ListaPubli();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//Se construye la sentencia de selección
		String query = "select from " + Tabla.PUBLICACION;
		boolean first=false;
		for(Metadato metadato:listaMetadato){
			if(first==false){
				query = query + " where ";
				first = true;
			}
			if(metadato.getNombreTabla().compareTo(Tabla.PUBLICACION)==0){
				query = query + metadato.getNombreMetadato() + metadato.getOperador().toString() + metadato.getValor().toString();
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Publi> listaPubliList = (List<Publi>) pm.newQuery(query).execute();
		for(Publi publi:listaPubliList){
			listaPubli.add(publi);
		}
		return listaPubli;
	}

	public ListaMensaje putPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        pm.makePersistent(publi);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
	
	public ListaMensaje borraPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.deletePersistent(publi);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
}

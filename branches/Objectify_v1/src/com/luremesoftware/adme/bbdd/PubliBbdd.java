package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class PubliBbdd{
	
	public PubliBbdd(){}
	
	public List<Publi> getListaPubli(ListaMetadato listaMetadato){
		List<Publi> listaPubli = new ArrayList<Publi>();
		
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		//Se construye la sentencia de selecci�n
		Query query = pm.newQuery(Publi.class);
		for(Metadato metadato:listaMetadato){
			if(metadato.getNombreTabla().compareTo(Tabla.PUBLICACION)==0){
				//query.setFilter(metadato.getNombreMetadato() + " " + metadato.getOperatorFilter() + " '" + metadato.getValor().toString() + "'" );
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Publi> listaPubliList = (List<Publi>) pm.newQuery(query).execute();
		for(Publi publi:listaPubliList){
			listaPubli.add(publi);
		}*/
		return listaPubli;
	}

	public ListaMensaje putPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        pm.makePersistent(publi);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } finally {
	        pm.close();
	    }*/
		
		return listaMensaje;
	}
	
	public ListaMensaje borraPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.deletePersistent(publi);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }*/
		
		return listaMensaje;
	}
}

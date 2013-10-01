package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class Bbdd {
	
	private DatastoreService datastore = null;
	
	public Bbdd(){
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public PreparedQuery prepareDatastore(Query query){
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		return datastore.prepare(query);
	}
	
	public ListaMensaje putDatastore(Entity entity){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		try{
			datastore.put(entity);
		}catch(IllegalArgumentException iae){
			listaMensaje.add(new Mensaje(TipoError.ERROR,iae.getMessage()));
		}catch(ConcurrentModificationException cme){
			listaMensaje.add(new Mensaje(TipoError.ERROR,cme.getMessage()));
		}catch(DatastoreFailureException dfe){
			listaMensaje.add(new Mensaje(TipoError.ERROR,dfe.getMessage()));
		}

		return listaMensaje;
	}
	
	public boolean buildQuery(Query query, ListaMetadato listaMetadato){
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		
		if(listaMetadato.size() == 0){
			//nothing to do
		}
		else if(listaMetadato.size() == 1){
			query.setFilter(listaMetadato.get(0).getlikeFilterPredicate());
		}
		else{
			for(Metadato metadato:listaMetadato){
				listaFiltros.add(metadato.getlikeFilterPredicate());
			}
			Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
			query.setFilter(filtroCompuesto);
		}
		
		return true;
	}
}

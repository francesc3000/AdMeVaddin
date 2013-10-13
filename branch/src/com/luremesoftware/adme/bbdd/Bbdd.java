package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class Bbdd {
	
	protected DatastoreService datastore = null;
	
	public Bbdd(){
		datastore = DatastoreServiceFactory.getDatastoreService();
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
		return this.buildQuery(query, listaMetadato, null);
	}
	
	public boolean buildQuery(Query query, ListaMetadato listaMetadato_in, NombreTabla nombreTabla){
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		ListaMetadato listaMetadato = new ListaMetadato();
		
		if(nombreTabla != null){
			for(Metadato metadato:listaMetadato_in){
				if(metadato.getNombreTabla()==nombreTabla){
					listaMetadato.add(metadato);
				}
			}
		}
		else{
			listaMetadato = (ListaMetadato) listaMetadato_in.clone();
		}
		
		if(listaMetadato.size() == 0){
			//nothing to do
		}
		else if(listaMetadato.size() == 1){
			FilterPredicate filterPredicate = listaMetadato.get(0).getlikeFilterPredicate();
			if(filterPredicate!=null){
				query.setFilter(filterPredicate);
			}
		}
		else{
			for(Metadato metadato:listaMetadato){
				FilterPredicate filterPredicate = metadato.getlikeFilterPredicate();
				if(filterPredicate!=null){
					listaFiltros.add(filterPredicate);
				}
			}
			Filter filtroCompuesto = null;
			if(!listaFiltros.isEmpty()){
				CompositeFilterOperator.and(listaFiltros);
			}
			query.setFilter(filtroCompuesto);
		}
		
		return true;
	}
}

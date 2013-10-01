package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class Bbdd {
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

package com.luremesoftware.adme.modelo;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Metadato {

	FilterPredicate filterPredicate = null;
	private String tabla;
	private String nombreMetadato;
    private FilterOperator operador;
    private Object valor;
	
	public Metadato(String tabla, String nombreMetadato, FilterOperator operador, Object valor ) {
		this.tabla = tabla;
		this.nombreMetadato = nombreMetadato;
	    this.operador = operador;
	    this.valor = valor;
	    
	    this.filterPredicate = new FilterPredicate(nombreMetadato, operador, valor);
	}
	
	public FilterPredicate getlikeFilterPredicate(){
		return this.filterPredicate;
	}

}

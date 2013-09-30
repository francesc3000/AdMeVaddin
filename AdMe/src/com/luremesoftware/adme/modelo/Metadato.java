package com.luremesoftware.adme.modelo;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.luremesoftware.adme.constantes.Constante;
import com.luremesoftware.adme.constantes.NombreTabla;

public class Metadato {

	FilterPredicate filterPredicate = null;
	private NombreTabla nombreTabla;
	private Constante nombreMetadato;
    private FilterOperator operador;
    private Object valor;
	
	public Metadato(NombreTabla nombreTabla, Constante nombreMetadato, FilterOperator operador, Object valor ) {
		this.nombreTabla = nombreTabla;
		this.nombreMetadato = nombreMetadato;
	    this.operador = operador;
	    this.valor = valor;
	    
	    this.filterPredicate = new FilterPredicate(this.nombreMetadato.toString(), this.operador, this.valor);
	}


	public String getTabla(){
		return this.nombreTabla.toString();
	}
	
	public FilterPredicate getlikeFilterPredicate(){
		return this.filterPredicate;
	}

}

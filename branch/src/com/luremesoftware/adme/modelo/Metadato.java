package com.luremesoftware.adme.modelo;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.luremesoftware.adme.constantes.Constante;
import com.luremesoftware.adme.constantes.Constante.Tabla;

public class Metadato {

	FilterPredicate filterPredicate = null;
	private Tabla tabla;
	private Constante nombreMetadato;
    private FilterOperator operador;
    private Object valor;
	
	public Metadato(Tabla tabla, Constante nombreMetadato, FilterOperator operador, Object valor ) {
		this.tabla = tabla;
		this.nombreMetadato = nombreMetadato;
	    this.operador = operador;
	    this.valor = valor;
	    
	    this.filterPredicate = new FilterPredicate(this.nombreMetadato.toString(), this.operador, this.valor);
	}


	public String getTabla(){
		return this.tabla.toString();
	}
	
	public FilterPredicate getlikeFilterPredicate(){
		return this.filterPredicate;
	}
	
	public Tabla getNombreTabla(){
		return this.tabla;
	}
	
	public Constante getNombreMetadato(){
		return this.nombreMetadato;
	}
	
	public FilterOperator getOperador(){
		return this.operador;
	}
	
	public Object getValor(){
		return this.valor;
	}

}

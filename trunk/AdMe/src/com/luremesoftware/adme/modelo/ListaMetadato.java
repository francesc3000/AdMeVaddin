package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Query.FilterOperator;

public class ListaMetadato extends ArrayList<Metadato>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean setMetadato(String tabla, String nombreMetadato, FilterOperator operador, Object valor)
	{
		return this.add(new Metadato(tabla, nombreMetadato, operador, valor));
	}
	


	

	
}

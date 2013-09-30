package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.constantes.Constante;
import com.luremesoftware.adme.constantes.NombreTabla;

public class ListaMetadato extends ArrayList<Metadato>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean setMetadato(NombreTabla tabla, Constante nombreMetadato, FilterOperator operador, Object valor)
	{
		return this.add(new Metadato(tabla, nombreMetadato, operador, valor));
	}
	

	public boolean add(Metadato metadato){
		if(metadato != null){
			return this.add(metadato);
		}else{
			return true;
		}
	}
	

	
}

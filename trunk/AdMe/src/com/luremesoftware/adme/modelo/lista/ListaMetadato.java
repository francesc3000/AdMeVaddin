package com.luremesoftware.adme.modelo.lista;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.constantes.Constante;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Metadato;

public class ListaMetadato extends ArrayList<Metadato>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*public boolean setMetadato(NombreTabla tabla, Constante nombreMetadato, FilterOperator operador, Object valor)
	{
		return this.add(new Metadato(tabla, nombreMetadato, operador, valor));
	}*/
	

	/*public boolean add(Metadato metadato){
		if(metadato != null){
			ensureCapacityInternal(size + 1);  // Increments modCount!!
	        elementData[size++] = e;
	        return true;
		}else{
			return true;
		}
	}*/
	

	
}

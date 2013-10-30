package com.luremesoftware.adme.bbdd;

import java.util.List;

import static com.luremesoftware.adme.bbdd.OfyService.ofy;

import com.googlecode.objectify.cmd.Query;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class PubliBbdd{
	
	public PubliBbdd(){}
	
	public List<Publi> getListaPubli(ListaMetadato listaMetadato){
		Query<Publi> listaPubli = null;
		
		for(Metadato metadato:listaMetadato){
			if(metadato.getNombreTabla().compareTo(Tabla.PUBLICACION)==0){
				listaPubli = ofy().load().
							type(Publi.class).
							filter(metadato.getNombreMetadato() + " " + metadato.getOperador().toString()
							, metadato.getValor().toString());
			}
		}

		return listaPubli.list();
	}

	public ListaMensaje putPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
	
		ofy().save().entity(publi).now();
		
		return listaMensaje;
	}
	
	public ListaMensaje borraPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().delete().entity(publi);
		
		return listaMensaje;
	}
}

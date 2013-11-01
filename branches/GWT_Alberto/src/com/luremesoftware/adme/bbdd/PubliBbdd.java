package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
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
		List<Publi> listaPubli = new ArrayList<Publi>();
		Query<Publi> queryPubli = ofy().load().type(Publi.class);
		
		for(Metadato metadato:listaMetadato){
			if(metadato.getNombreTabla().compareTo(Tabla.PUBLICACION)==0){
				String nombreMetadato = metadato.getNombreMetadato().toString();
				String operador =  metadato.getOperador().toString();
				String valor = metadato.getValor().toString();
				queryPubli.filter(nombreMetadato + " " + operador, valor);
			}
		}


		for(Publi publi: queryPubli){
			listaPubli.add(publi);
		}

		return listaPubli;
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

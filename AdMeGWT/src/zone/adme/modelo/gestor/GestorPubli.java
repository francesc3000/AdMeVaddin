package zone.adme.modelo.gestor;

import java.util.List;

import zone.adme.bbdd.PubliBbdd;
import zone.adme.modelo.Publi;
import zone.adme.modelo.lista.ListaMensaje;
import zone.adme.modelo.lista.ListaMetadato;

public class GestorPubli {
	
	private PubliBbdd publiBbdd = null;
	
	public GestorPubli(){
		this.publiBbdd = new PubliBbdd();
	}
	
	/**
	 * Se retorna el listado de Publicaciones a partir de una tabla de Metadatos
	 * @param listaMetadato Filtros que se quiere buscar publicaciones
	 * @return Listado de Publicaciones
	 */	
	public List<Publi> getListaPubli(ListaMetadato listaMetadato){
		return this.publiBbdd.getListaPubli(listaMetadato);
	}

	/**
	 * Crea una publicación en el sistema
	 * 
	 * @return Retorna el identificador de la publicación
	 */
	public ListaMensaje putPubli(Publi publi){
		return publiBbdd.putPublicacion(publi);
	}
	
	public ListaMensaje borraPubli(Publi publi){
		return publiBbdd.borraPublicacion(publi);
	}
}

/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Publi
 */
package com.luremesoftware.adme.constantes;

/**
 * @author francesc3000@gmail.com
 *
 */
public enum MetadatosPubli {
	PROPIETARIO("Propietario"),
	TITULO("Titulo"),
	CIUDAD("Ciudad"),
	DESCRIPCION("Descripcion");
	
	private final String metadato;
	
	private MetadatosPubli(String metadato){
		this.metadato = metadato;
	}
	
	public String toString(){
		return this.metadato;
	}
}

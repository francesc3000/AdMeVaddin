/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Grupo
 */
package com.luremesoftware.adme.constantes;

/**
 * @author francesc3000@gmail.com
 *
 */
public enum MetadatosGrupo {
	NOMBRE("Nombre");
	
	private final String metadato;
	
	private MetadatosGrupo(String metadato){
		this.metadato = metadato;
	}
	
	public String toString(){
		return this.metadato;
	}
}
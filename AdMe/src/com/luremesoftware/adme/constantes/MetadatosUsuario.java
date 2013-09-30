/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Usuario
 */
package com.luremesoftware.adme.constantes;

/**
 * @author francesc3000@gmail.com
 *
 */
public enum MetadatosUsuario {
	CORREO("Correo"),
	CONTRASENA("Contrasena"),
	NOMBRE("Nombre"),
	APELLIDO1("Apellido1"),
	APELLIDO2("Apellido2");
	
	private final String metadato;
	
	private MetadatosUsuario(String metadato){
		this.metadato = metadato;
	}
	
	public String toString(){
		return this.metadato;
	}
}

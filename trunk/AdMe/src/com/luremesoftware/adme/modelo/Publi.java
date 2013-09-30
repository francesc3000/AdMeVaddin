package com.luremesoftware.adme.modelo;

/**
 * Clase Publicación
 *
*/
public class Publi{
	private Propietario propietario; //Puede ser un usuario o un grupo
	private String titulo;	 		//Titulo de la publicacion
	private String ciudad; 			//Ciudad donde se va mostrar el logotipo
	private String descripcion; 	//Descripción de la publicación
	
	/**
	 * Crea la clase Publicación sin parámetros descriptivos
	 * 
	 * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
	 */
	public Publi(Propietario propietario){
		this.propietario = propietario;
	}
	
	/**
	* Crea la clase Publicación rellenando todos sus parámetros
	* Al ser el título obligatorio, si es nulo o vacío se lanzará
	* una excepción.
    *
    * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
    * @param titulo El nuevo título de la publicacion.
	*/	
	public Publi(Propietario propietario, String titulo, String ciudad, String descripcion){
		this.propietario = propietario;
		this.titulo = titulo;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public String getCiudad(){
		return this.ciudad;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public String getPropietarioId(){
		return this.propietario.getId();
	}
	
	public Propietario getPropietario(){
		return this.propietario;
	}
	
	public String toString(){
		return this.propietario.getId() + " es propietario de: " + this.getTitulo();
	}
}

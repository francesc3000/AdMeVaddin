package com.luremesoftware.adme.modelo;

/**
 * Clase Publicaci�n
 *
*/
public class Publi{
	private Propietario propietario; //Puede ser un usuario o un grupo
	private String titulo;	 		//Titulo de la publicacion
	private String ciudad; 			//Ciudad donde se va mostrar el logotipo
	private String descripcion; 	//Descripci�n de la publicaci�n
	
	/**
	 * Crea la clase Publicaci�n sin par�metros descriptivos
	 * 
	 * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
	 */
	public Publi(Propietario propietario){
		this.propietario = propietario;
	}
	
	/**
	* Crea la clase Publicaci�n rellenando todos sus par�metros
	* Al ser el t�tulo obligatorio, si es nulo o vac�o se lanzar�
	* una excepci�n.
    *
    * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
    * @param titulo El nuevo t�tulo de la publicacion.
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

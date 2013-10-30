package com.luremesoftware.adme.modelo;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

/**
 * Clase Publicación
 *
*/
@Entity
public class Publi implements Serializable{
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Load private Ref<Propietario> propietario; //Puede ser un usuario o un grupo
	private String titulo;	 		//Titulo de la publicacion
	private String ciudad; 			//Ciudad donde se va mostrar el logotipo
	private String descripcion; 	//Descripción de la publicación
	
	@SuppressWarnings("unused")
	private Publi(){}
	
	/**
	* Crea la clase Publicación rellenando todos sus parámetros
	* Al ser el título obligatorio, si es nulo o vacío se lanzará
	* una excepción.
    *
    * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
    * @param titulo El nuevo título de la publicacion.
	*/	
	public Publi(Propietario propietario, String titulo, String descripcion, String ciudad){
		this.setPropietario(propietario);
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
	
	public Propietario getPropietario(){
		return this.propietario.get();
	}
	
	public boolean setPropietario(Propietario propietario){
		this.propietario = Ref.create(propietario);
		return true;
	}
	
	public String toString(){
		return this.propietario.getKey() + " es propietario de: " + this.getTitulo();
	}
}

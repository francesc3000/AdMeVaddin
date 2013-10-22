package com.luremesoftware.adme.modelo;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorPropietario;

/**
 * Clase Publicación
 *
*/
@PersistenceCapable(detachable = "true")
public class Publi implements Serializable{
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	/*
	@Persistent
	private Key propietarioKey;
	@Persistent(mappedBy = "listaPubli")
	private Propietario propietario; //Puede ser un usuario o un grupo
	*/
	@Persistent
	private Propietario propietario;
	@Persistent
	private String titulo;	 		//Titulo de la publicacion
	@Persistent
	private String ciudad; 			//Ciudad donde se va mostrar el logotipo
	@Persistent
	private String descripcion; 	//Descripción de la publicación
	
	public Publi(){}
	
	/**
	* Crea la clase Publicación rellenando todos sus parámetros
	* Al ser el título obligatorio, si es nulo o vacío se lanzará
	* una excepción.
    *
    * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
    * @param titulo El nuevo título de la publicacion.
	*/	
	public Publi(Propietario propietario, String titulo, String descripcion, String ciudad){
		//this.propietarioKey = propietario.getKey();
		//this.propietario = propietario;
		this.setPropietario(propietario);
		this.titulo = titulo;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
	}
	
	public Key getKey(){
		/*if(this.key == null){
			String name = this.getPropietario().getKey() + this.titulo;
			this.key = KeyFactory.createKey(Tabla.PUBLICACION.getSimpleName(), name);
		}*/
		return this.key;
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
	/*
	public Propietario getPropietario(){
		if(this.propietario==null){
			this.propietario = new GestorPropietario().getPropietarioByKey(this.propietario.getKey());
		}
		return this.propietario;
	}
	
	public boolean setPropietario(Propietario propietario){
		this.propietario = propietario;
		return true;
	}
	
	public String toString(){
		return this.propietario.getKey() + " es propietario de: " + this.getTitulo();
	}*/
	public Propietario getPropietario(){
		return this.propietario;
	}
	
	public boolean setPropietario(Propietario propietario){
		this.propietario = propietario;
		return true;
	}
	
	public String toString(){
		Propietario propietario = this.getPropietario();
		return propietario.getKey() + " es propietario de: " + this.getTitulo();
	}
}

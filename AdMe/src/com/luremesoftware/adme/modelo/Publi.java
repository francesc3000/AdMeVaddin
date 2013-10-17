package com.luremesoftware.adme.modelo;

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
@PersistenceCapable
public class Publi{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	//Por restricciones de GAE/JDO no se pueden tener relaciones polimorficas
	//private Propietario propietario; //Puede ser un usuario o un grupo
	private Key propietarioKey; //En futuras versiones de GAE/JDO podria solucionarse
	@NotPersistent
	private Propietario propietario; //Puede ser un usuario o un grupo
	@Persistent
	private String titulo;	 		//Titulo de la publicacion
	@Persistent
	private String ciudad; 			//Ciudad donde se va mostrar el logotipo
	@Persistent
	private String descripcion; 	//Descripción de la publicación
	
	/**
	* Crea la clase Publicación rellenando todos sus parámetros
	* Al ser el título obligatorio, si es nulo o vacío se lanzará
	* una excepción.
    *
    * @param propietario Propietario de la publicacion(Puede pasarse un Usuario o un Grupo directamente).
    * @param titulo El nuevo título de la publicacion.
	*/	
	public Publi(Propietario propietario, String titulo, String descripcion, String ciudad){
		
		this.propietarioKey = propietario.getKey();
		this.propietario = propietario;
		this.titulo = titulo;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		
		String name = Publi.class.getSimpleName() + this.titulo;
		this.key = KeyFactory.createKey(Tabla.PUBLICACION.toString(), name);
	}
	
	public Key getKey(){
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
	
	public Propietario getPropietario(){
		if(this.propietario==null){
			this.propietario = new GestorPropietario().getPropietarioByKey(this.propietarioKey);
		}
		return this.propietario;
	}
	
	public String toString(){
		return this.propietario.getId() + " es propietario de: " + this.getTitulo();
	}
}

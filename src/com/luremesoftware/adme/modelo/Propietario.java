package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.Image;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
/**
 * Debido a las limitaciones del SDK de google app engine para JDO
 * se ha tenido que abandonar la relación de herencia entre las clases
 * Propietario/Usuario-Grupo. El resultado es la inserción de las clases
 * hijas dentro de la clase padre Propietario
 * 
 * @author francesc3000@gmail.com
 *
 */
@PersistenceCapable(detachable = "true")
//@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
//@Inheritance(customStrategy = "complete-table")
public class Propietario implements Serializable{
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;
	//private String video;
	//private Image avatar;
	@Persistent
	private String entidad;
	@Persistent(mappedBy = "propietario", defaultFetchGroup = "true")
	protected List<Publi> listaPubli = null;
	@Persistent
	protected Puntuaciones puntuaciones = null;
	
	@Persistent(defaultFetchGroup = "true")
    @Embedded
    private Usuario usuario = null;
	
	@Persistent(defaultFetchGroup = "true")
    @Embedded
    private Grupo grupo = null;
	
	public Propietario(){}
	
	public Propietario(String correo, String contrasena, String nombre, String apellido1, String apellido2){
		this.usuario = new Usuario(correo, contrasena, nombre, apellido1, apellido2);
		this.buildKey(Tabla.PROPIETARIO.getSimpleName(), correo);
		this.setEntidad(Tabla.USUARIO.getSimpleName());
	}
	
	public Propietario(Propietario usuario, String nombre, String descripcion, String ciudad){
		this.grupo = new Grupo(this,nombre, descripcion, ciudad);
		this.buildKey(Tabla.PROPIETARIO.getSimpleName(), usuario.getKey() + nombre);
		this.setEntidad(Tabla.GRUPO.getSimpleName());
	}
	
	protected boolean buildKey(String SimpleName, String id){
		return this.setKey(KeyFactory.createKey(SimpleName, id));
	}
	
	public Key getKey(){
		return this.key;
	}
	
	public String getEntidad(){
		return this.entidad;
	}
	
	public String getNombre(){
		String nombre = null;
		
		switch(this.getEntidad()){
		case "Usuario":
			nombre = this.usuario.getNombre();
			break;
		case "Grupo":
			nombre = this.grupo.getNombre();
			break;
		}
		
		return nombre;
	}
	
	public List<Publi> getListaPubli(){
		if(this.listaPubli==null){
			this.listaPubli = new GestorPubli().getListaPubli(this);	
		}
		return this.listaPubli;
	}
	
	public List<Propietario> getListaGrupo(){
		return this.usuario.getListaGrupo(this);
	}
	
	public Puntuaciones getControlPuntuacion(){
		return this.puntuaciones;
	}

	public Puntuacion getAltaPuntuacion(){
		return this.puntuaciones.getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.puntuaciones.getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.puntuaciones.getPuntuacionPromedio();
	}

	public boolean setKey(Key key){
		this.key = key;
		return true;
	}
	
	public boolean setEntidad(String entidad){
		this.entidad = entidad;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		if(this.listaPubli==null){
			this.listaPubli = new ArrayList<Publi>();
		}
		return this.listaPubli.add(publi);
	}
	
	public boolean setListaPubli(List<Publi> listaPubli){
		return this.listaPubli.addAll(listaPubli);
	}
	
	public boolean setPuntuacion(Puntuacion puntuacion){
		return this.puntuaciones.setPuntuacion(puntuacion);
	}
}

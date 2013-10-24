package com.luremesoftware.adme.modelo;

import java.io.Serializable;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Puntuacion implements Serializable{
	
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent(defaultFetchGroup = "true")
	private Key puntuadorKey;
	@NotPersistent
	private Usuario puntuador;
	//@Persistent(defaultFetchGroup = "true")
	//private Propietario puntuado;
	@Persistent(defaultFetchGroup = "true")
	private int puntuacion;
	
	public Puntuacion(){}
	
	public Puntuacion(Usuario puntuador, int puntuacion){
		this.puntuadorKey = puntuador.getKey();
		this.puntuador = puntuador;
		this.puntuacion = puntuacion;
	}
	
	public Usuario getPuntuador(){
		return this.puntuador;
	}
	
	public int getPuntuacion(){
		return this.puntuacion;
	}
}

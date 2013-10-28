package com.luremesoftware.adme.modelo;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Puntuacion implements Serializable{
	
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Load private Ref<Usuario> puntuador;
	//@Load private Ref<Usuario> puntuado;
	private int puntuacion;
	
	public Puntuacion(){}
	
	public Puntuacion(Usuario puntuador, int puntuacion){
		this.setPuntuador(puntuador);
		this.puntuacion = puntuacion;
	}
	
	public Usuario getPuntuador(){
		return this.puntuador.get();
	}
	
	public int getPuntuacion(){
		return this.puntuacion;
	}
	
	private boolean setPuntuador(Usuario puntuador){
		this.puntuador = Ref.create(puntuador);
		return true;
	}
}

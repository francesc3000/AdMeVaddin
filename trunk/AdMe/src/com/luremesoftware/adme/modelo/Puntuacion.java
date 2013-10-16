package com.luremesoftware.adme.modelo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Puntuacion {
	
	@Persistent
	private Propietario puntuador;
	@Persistent
	private Propietario puntuado;
	@Persistent
	private int puntuacion;
	
	public Puntuacion(){}
	
	public Puntuacion(Propietario puntuador, Propietario puntuado, int puntuacion){
		this.puntuador = puntuador;
		this.puntuado = puntuado;
		this.puntuacion = puntuacion;
	}
	
	public Propietario getPuntuador(){
		return this.puntuador;
	}
	
	public Propietario getPuntuado(){
		return this.puntuado;
	}
	
	public int getPuntuacion(){
		return this.puntuacion;
	}
}

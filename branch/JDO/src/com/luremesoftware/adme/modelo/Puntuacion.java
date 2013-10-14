package com.luremesoftware.adme.modelo;

public class Puntuacion {
	
	private Propietario puntuador;
	private Propietario puntuado;
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

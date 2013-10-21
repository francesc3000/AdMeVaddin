package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Puntuaciones {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	@Persistent
	private Propietario propietario;
	@Persistent
	private ArrayList<Puntuacion> listaPuntuacion = new ArrayList<Puntuacion>();
	
	public Puntuaciones(Propietario propietario){
		this.setPropietario(propietario);
	}
	
	public Propietario getPropietario(){
		return this.propietario;
	}
	
	public Puntuacion getAltaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.listaPuntuacion.size()==0){
			return new Puntuacion();
		}else{
			int intpuntuacion = 0;
			for(Puntuacion puntuacionAux:this.listaPuntuacion){
				if(puntuacionAux.getPuntuacion()>=intpuntuacion){
					puntuacion = puntuacionAux;
				}
			}
			return puntuacion;
		}
	}
	
	public Puntuacion getBajaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.listaPuntuacion.size()==0){
			return new Puntuacion();
		}else{
			int intpuntuacion = 0;
			for(Puntuacion puntuacionAux:this.listaPuntuacion){
				if(puntuacionAux.getPuntuacion()<=intpuntuacion){
					puntuacion = puntuacionAux;
				}
			}
			return puntuacion;
		}
	}
	
	public int getPuntuacionPromedio(){
		int puntuacionPromedio = 0;
		
		//Se contabilizan todas las puntuaciones
		for(Puntuacion puntuacion:this.listaPuntuacion){
			puntuacionPromedio = puntuacionPromedio + puntuacion.getPuntuacion();
		}
		
		//Se calcula la puntuacion Promedio
		if(puntuacionPromedio!=0){
			puntuacionPromedio = puntuacionPromedio / this.listaPuntuacion.size();
		}
		
		return puntuacionPromedio;
	}

	public boolean setPropietario(Propietario propietario){
		this.propietario = propietario;
		return true;
	}
	
	public boolean setPuntuacion(Puntuacion puntuacion){
		return this.listaPuntuacion.add(puntuacion);
	}
}

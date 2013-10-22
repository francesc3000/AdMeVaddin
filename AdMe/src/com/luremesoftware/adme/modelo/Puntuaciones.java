package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
@FetchGroup(name="Puntuaciones", members={@Persistent(name="listaPuntuacion")})
public class Puntuaciones implements Serializable{
	
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	@Element(dependent = "true")
	private List<Puntuacion> listaPuntuacion = new ArrayList<Puntuacion>();
	
	public Puntuaciones(){}
	
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
	
	public List<Puntuacion> getListaPuntuaciones(){
		return this.listaPuntuacion;
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
	
	public boolean setPuntuacion(Puntuacion puntuacion){
		return this.listaPuntuacion.add(puntuacion);
	}
}

package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

@Entity
public class ControlPuntuacion implements Serializable{
	
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Load
	private List<Ref<Puntuacion>> listaPuntuacion = new ArrayList<Ref<Puntuacion>>();
	
	public ControlPuntuacion(){}
	
	public Puntuacion getAltaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.listaPuntuacion.size()==0){
			return new Puntuacion();
		}else{
			int intpuntuacion = 0;
			for(Ref<Puntuacion> puntuacionAux:this.listaPuntuacion){
				if(puntuacionAux.get().getPuntuacion()>=intpuntuacion){
					puntuacion = puntuacionAux.get();
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
			for(Ref<Puntuacion> puntuacionAux:this.listaPuntuacion){
				if(puntuacionAux.get().getPuntuacion()<=intpuntuacion){
					puntuacion = puntuacionAux.get();
				}
			}
			return puntuacion;
		}
	}
	
	public List<Puntuacion> getListaPuntuaciones(){
		List<Puntuacion> listaPuntuacionNoRef = new ArrayList<Puntuacion>();
		
		for(Ref<Puntuacion> puntuacion:this.listaPuntuacion){
			listaPuntuacionNoRef.add(puntuacion.get());
		}
		return listaPuntuacionNoRef;
	}
	
	public int getPuntuacionPromedio(){
		int puntuacionPromedio = 0;
		
		//Se contabilizan todas las controlPuntuacion
		for(Ref<Puntuacion> puntuacion:this.listaPuntuacion){
			puntuacionPromedio = puntuacionPromedio + puntuacion.get().getPuntuacion();
		}
		
		//Se calcula la puntuacion Promedio
		if(puntuacionPromedio!=0){
			puntuacionPromedio = puntuacionPromedio / this.listaPuntuacion.size();
		}
		
		return puntuacionPromedio;
	}
	
	public boolean setPuntuacion(Usuario puntuador, int puntuacion){
		this.listaPuntuacion.add(Ref.create(new Puntuacion(puntuador, puntuacion)));
		return true;
	}
}

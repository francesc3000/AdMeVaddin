package com.luremesoftware.adme.modelo.lista;

import java.util.ArrayList;

import com.luremesoftware.adme.modelo.Puntuacion;

public class ListaPuntuacion extends ArrayList<Puntuacion>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Puntuacion getAltaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.size()==0){
			return new Puntuacion();
		}else{
			int intpuntuacion = 0;
			for(Puntuacion puntuacionAux:this){
				if(puntuacionAux.getPuntuacion()>=intpuntuacion){
					puntuacion = puntuacionAux;
				}
			}
			return puntuacion;
		}
	}
	
	public Puntuacion getBajaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.size()==0){
			return new Puntuacion();
		}else{
			int intpuntuacion = 0;
			for(Puntuacion puntuacionAux:this){
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
		for(Puntuacion puntuacion:this){
			puntuacionPromedio = puntuacionPromedio + puntuacion.getPuntuacion();
		}
		
		//Se calcula la puntuacion Promedio
		if(puntuacionPromedio!=0){
			puntuacionPromedio = puntuacionPromedio / this.size();
		}
		
		return puntuacionPromedio;
	}

}

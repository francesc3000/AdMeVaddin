package com.luremesoftware.adme.modelo;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public abstract class Propietario{
	@Id
	protected String id;
	//private String video;
	//private Image avatar;
	@Ignore
	protected List<Publi> listaPubli = new ArrayList<Publi>();
	protected List<Ref<Publi>> listaPubliRef = new ArrayList<Ref<Publi>>();
	protected ControlPuntuacion controlPuntuacion = new ControlPuntuacion();
	
	protected Propietario(){}
	
	public String getId(){
		return this.id;
	}
	
	public List<Publi> getListaPubli(){
		List<Publi> listaPubliNoRef = new ArrayList<Publi>();
		
		for(Ref<Publi> publi:this.listaPubliRef){
			listaPubliNoRef.add(publi.get());
		}
		return listaPubliNoRef;
	}

	public Puntuacion getAltaPuntuacion(){
		return this.controlPuntuacion.getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.controlPuntuacion.getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.controlPuntuacion.getPuntuacionPromedio();
	}
	
	/**
	 * Este metodo solo se debe utilizar dentro del controlador para bases
	 * de datos. ¡¡NO UTILIZAR PARA OTROS PROPOSITOS!!
	 * @return
	 */
	public List<Publi> getPublisParaBBDD(){
		return this.listaPubli;
	}

	public boolean setId(String id){
		this.id = id;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		this.listaPubli.add(publi);
		return this.listaPubliRef.add(Ref.create(publi));
	}
	
	public boolean setListaPubli(List<Publi> listaPubli){
		
		for(Publi publi:listaPubli){
			this.setPubli(publi);
		}
		
		return this.listaPubli.addAll(listaPubli);
	}
}

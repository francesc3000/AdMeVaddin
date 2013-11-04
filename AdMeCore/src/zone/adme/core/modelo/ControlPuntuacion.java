package zone.adme.core.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import zone.adme.core.bbdd.PropietarioBbdd;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class ControlPuntuacion implements Serializable{
	
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Parent @Load
	private Ref<Propietario> propietarioRef = null;
	@Load
	private List<Ref<Puntuacion>> listaPuntuacionRef = new ArrayList<Ref<Puntuacion>>();
	
	@SuppressWarnings("unused")
	private ControlPuntuacion(){}
	
	public ControlPuntuacion(Key<Propietario> key){
		this.propietarioRef = Ref.create(key);
	}
	
	public Propietario getPropietario(){
		return this.propietarioRef.get();
	}
	
	public Puntuacion getAltaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.listaPuntuacionRef.size()==0){
			return null;
		}else{
			int intpuntuacion = 0;
			for(Ref<Puntuacion> puntuacionAux:this.listaPuntuacionRef){
				if(puntuacionAux.get().getPuntuacion()>=intpuntuacion){
					puntuacion = puntuacionAux.get();
				}
			}
			return puntuacion;
		}
	}
	
	public Puntuacion getBajaPuntuacion(){
		Puntuacion puntuacion = null;
		
		if(this.listaPuntuacionRef.size()==0){
			return null;
		}else{
			int intpuntuacion = 0;
			for(Ref<Puntuacion> puntuacionAux:this.listaPuntuacionRef){
				if(puntuacionAux.get().getPuntuacion()<=intpuntuacion){
					puntuacion = puntuacionAux.get();
				}
			}
			return puntuacion;
		}
	}
	
	public List<Puntuacion> getListaPuntuaciones(){
		List<Puntuacion> listaPuntuacionNoRef = new ArrayList<Puntuacion>();
		
		for(Ref<Puntuacion> puntuacion:this.listaPuntuacionRef){
			listaPuntuacionNoRef.add(puntuacion.get());
		}
		return listaPuntuacionNoRef;
	}
	
	public int getPuntuacionPromedio(){
		int puntuacionPromedio = 0;
		
		//Se contabilizan todas las controlPuntuacion
		for(Ref<Puntuacion> puntuacion:this.listaPuntuacionRef){
			puntuacionPromedio = puntuacionPromedio + puntuacion.get().getPuntuacion();
		}
		
		//Se calcula la puntuacion Promedio
		if(puntuacionPromedio!=0){
			puntuacionPromedio = puntuacionPromedio / this.listaPuntuacionRef.size();
		}
		
		return puntuacionPromedio;
	}
	
	public boolean setPuntuacion(Usuario puntuador, int puntuacionInt){
		Puntuacion puntuacion = new Puntuacion(this, puntuador, puntuacionInt);
		this.listaPuntuacionRef.add(Ref.create(new PropietarioBbdd().creaPuntuacion(puntuacion)));
		return true;
	}
}

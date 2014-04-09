package zone.adme.core.shared.modelo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Puntuacion implements Serializable, IsSerializable{
	
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Parent @Load
	private Ref<ControlPuntuacion> controlPuntuacionRef = null;
	@Load 
	private Ref<Usuario> puntuadorRef;
	private int puntuacion;
	
	@SuppressWarnings("unused")
	private Puntuacion(){}
	
	public Puntuacion(ControlPuntuacion controlPuntuacion, Usuario puntuador, int puntuacion){
		this.controlPuntuacionRef = Ref.create(Key.create(controlPuntuacion));
		this.setPuntuador(puntuador);
		this.puntuacion = puntuacion;
	}
	
	public Usuario getPuntuador(){
		return this.puntuadorRef.get();
	}
	
	public int getPuntuacion(){
		return this.puntuacion;
	}
	
	private boolean setPuntuador(Usuario puntuador){
		this.puntuadorRef = Ref.create(puntuador);
		return true;
	}
}

package zone.adme.core.shared.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

import zone.adme.core.shared.modelo.gestor.GestorPropietario;

/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
@EntitySubclass(index=true)
public class Usuario extends Propietario implements Serializable, IsSerializable{
	
	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	private String correo;
	private String contrasena;
	private String nombre;
	private String apellido1;
	private String apellido2;
	@Load 
	private List<Ref<Grupo>> listaGrupoRef = new ArrayList<Ref<Grupo>>();
	
	@SuppressWarnings("unused")
	private Usuario(){}
	
	/**
	* Completa los datos personales del usuario, no realiza busquedas
	* en BBDD
    *
    * @param correo El correo del usuario
    * @param contrasena La contraseña elegida por el usuario
    * @param nombre Nombre del usuario
    * @param apellido1 Primer apellido del usuario
    * @param apellido2 Segundo apellido del usuario
	*/	
	public Usuario(String correo, String contrasena, String nombre, String apellido1, String apellido2){
		super();
		this.setId(correo);
		this.correo = correo;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public String getCorreo(){
		return this.correo;
	}
	
	public String getContrasena(){
		return this.contrasena;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido1(){
		return this.apellido1;
	}
	
	public String getApellido2(){
		return this.apellido2;
	}
	
	public Grupo getGrupo(int indice){
		return this.listaGrupoRef.get(indice).get();
	}
	
	/**
	 * Si el listado de grupos está vacío recupera los grupos 
	 * donde participa el usuario en BBDD. Si el listado de grupos
	 * ya ha sido cargado se comporta igual que el método getListaGrupo()
	 * 
	 * @return
	 */
	public List<Grupo> getListaGrupo(){
		List<Grupo> listaGrupoNoRef = new ArrayList<Grupo>();
		
		for(Ref<Grupo> grupo:this.listaGrupoRef){
			listaGrupoNoRef.add(grupo.get());
		}
		
		return listaGrupoNoRef;
	}
	
	public boolean setCorreo(String correo){
		this.correo = correo;
		return true;
	}
	
	public boolean setContrasena(String contrasena){
		this.contrasena = contrasena;
		return true;
	}
	
	public boolean setNombre(String nombre){
		this.nombre = nombre;
		return true;
	}
	public boolean setApellido1(String apellido1){
		this.apellido1 = apellido1;
		return true;
	}
	
	public boolean setApellido2(String apellido2){
		this.apellido2 = apellido2;
		return true;
	}
	
	public boolean addGrupo(Grupo grupo){
		return this.listaGrupoRef.add(Ref.create(grupo));
	}
	
	public boolean addListaGrupo(List<Grupo> listaGrupo){
		for(Grupo grupo:listaGrupo){
			this.addGrupo(grupo);
		}
		return true;
	}
	
	public boolean setPuntuacion(Usuario puntuador, int puntuacion){
		if(this.controlPuntuacionRef==null){
			this.controlPuntuacionRef = Ref.create(new GestorPropietario().creaControlPuntuacion(this));
		}
		return this.controlPuntuacionRef.get().setPuntuacion(puntuador, puntuacion);
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}
}

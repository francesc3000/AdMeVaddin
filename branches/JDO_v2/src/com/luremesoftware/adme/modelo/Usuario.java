package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;

/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
@PersistenceCapable(detachable = "true")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@Persistent
	private String correo;
	@Persistent
	private String contrasena;
	@Persistent
	private String nombreUsuario;
	@Persistent
	private String apellido1;
	@Persistent
	private String apellido2;
	@Persistent
	//Por restricciones de GAE/JDO no se pueden tener relaciones polimorficas
	//private Propietario propietario; //Puede ser un usuario o un grupo
	//En futuras versiones de GAE/JDO podria solucionarse
	private List<Key> listaGrupoKey = new ArrayList<Key>();
	@NotPersistent
	private List<Propietario> listaGrupo = new ArrayList<Propietario>();
	
	public Usuario(){}
	
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
		this.correo = correo;
		this.contrasena = contrasena;
		this.nombreUsuario = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public String getCorreo(){
		//if(this.correo==null){this.correo = new String();}
		return this.correo;
	}
	
	public String getContrasena(){
	   //if(this.contrasena==null){this.contrasena = new String();}
		return this.contrasena;
	}
	
	public String getNombre(){
		//if(this.nombre==null){this.nombre = new String();}
		return this.nombreUsuario;
	}
	
	public String getApellido1(){
		//if(this.apellido1==null){this.apellido1 = new String();}
		return this.apellido1;
	}
	
	public String getApellido2(){
		//if(this.apellido2==null){this.apellido2 = new String();}
		return this.apellido2;
	}
	
	public Propietario getGrupo(int indice){
		return this.listaGrupo.get(indice);
	}
	
	/**
	 * Si el listado de grupos está vacío recupera los grupos 
	 * donde participa el usuario en BBDD. Si el listado de grupos
	 * ya ha sido cargado se comporta igual que el método getListaGrupo()
	 * 
	 * @return
	 */
	public List<Propietario> getListaGrupo(Propietario propietario){
		if(this.listaGrupo == null){
			this.listaGrupo = new GestorGrupo().getListaGrupo(propietario);
		}
		
		return this.listaGrupo;
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
		this.nombreUsuario = nombre;
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
	
	public boolean setGrupoKey(Key key){
		return this.listaGrupoKey.add(key);
	}
	
	public boolean setGrupo(Propietario grupo){
		if(this.listaGrupo.add(grupo)){
			return this.setGrupoKey(grupo.getKey());
		}
		return false;
	}
	
	public boolean setListaGrupoKey(ArrayList<Key> listaGrupoKey){
		this.listaGrupoKey.addAll(listaGrupoKey);
		return true;
	}
	
	public boolean setListaGrupo(List<Propietario> listaGrupo){
		if(this.listaGrupo.addAll(listaGrupo)){
			for(Propietario grupo:listaGrupo){
				this.setGrupoKey(grupo.getKey());
			}
		}
		return true;
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}
}

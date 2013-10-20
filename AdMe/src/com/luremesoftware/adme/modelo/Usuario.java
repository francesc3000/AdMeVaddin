package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;

/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
@PersistenceCapable
public class Usuario extends Propietario implements Serializable{
	
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
	private String nombre;
	@Persistent
	private String apellido1;
	@Persistent
	private String apellido2;
	@Persistent
	private ArrayList<Key> listaGrupoKey = new ArrayList<Key>();
	@NotPersistent
	private ArrayList<Grupo> listaGrupo = new ArrayList<Grupo>();
	
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
		super();
		this.buildKey(correo);
		this.correo = correo;
		this.contrasena = contrasena;
		this.nombre = nombre;
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
		return this.nombre;
	}
	
	public String getApellido1(){
		//if(this.apellido1==null){this.apellido1 = new String();}
		return this.apellido1;
	}
	
	public String getApellido2(){
		//if(this.apellido2==null){this.apellido2 = new String();}
		return this.apellido2;
	}
	
	public Grupo getGrupo(int indice){
		return this.listaGrupo.get(indice);
	}
	
	/**
	 * Si el listado de grupos está vacío recupera los grupos 
	 * donde participa el usuario en BBDD. Si el listado de grupos
	 * ya ha sido cargado se comporta igual que el método getListaGrupo()
	 * 
	 * @return
	 */
	public ArrayList<Grupo> getListaGrupo(){
		if(this.listaGrupo == null){
			this.listaGrupo = new GestorGrupo().getListaGrupo(this);
		}
		
		return this.listaGrupo;
	}
	
	public Puntuaciones getControlPuntuacion(){
		return this.puntuaciones;
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
	
	public boolean setGrupoKey(Key key){
		return this.listaGrupoKey.add(key);
	}
	
	public boolean setGrupo(Grupo grupo){
		if(this.listaGrupo.add(grupo)){
			return this.setGrupoKey(grupo.getKey());
		}
		return false;
	}
	
	public boolean setListaGrupoKey(ArrayList<Key> listaGrupoKey){
		this.listaGrupoKey.addAll(listaGrupoKey);
		return true;
	}
	
	public boolean setListaGrupo(ArrayList<Grupo> listaGrupo){
		if(this.listaGrupo.addAll(listaGrupo)){
			for(Grupo grupo:listaGrupo){
				this.setGrupoKey(grupo.getKey());
			}
		}
		return true;
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}
}

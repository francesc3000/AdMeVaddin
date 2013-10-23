package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;

/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
@PersistenceCapable(detachable = "true")
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
	private List<Key> listaGrupoKey = new ArrayList<Key>();
	@NotPersistent
	private List<Grupo> listaGrupo = new ArrayList<Grupo>();
	
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
	
	private boolean buildKey(String id){
		return this.setKey(KeyFactory.createKey(Tabla.USUARIO.getSimpleName(), id));
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
	public List<Grupo> getListaGrupo(){
		if(this.listaGrupo.isEmpty()){
			this.listaGrupo = new GestorGrupo().getListaGrupoByKey(listaGrupoKey);
		}
		
		return this.listaGrupo;
	}
	
	public ControlPuntuacion getControlPuntuacion(){
		return this.controlPuntuacion;
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
	
	public boolean crearGrupo(Grupo grupo){
		//Se introduce el grupo en el usuario
		this.addGrupo(grupo);
		this.setGrupoKey(grupo.getKey());
		
		//Se informa del usuario en el grupo
		grupo.addUsuarioKey(this.getKey());
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
	
	public boolean setPuntuacion(Usuario puntuador, int puntuacion){
		if(this.controlPuntuacion==null){
			this.controlPuntuacion = new ControlPuntuacion();
		}
		return this.controlPuntuacion.setPuntuacion(puntuador, puntuacion);
	}
	
	private boolean addGrupo(Grupo grupo){
		if(this.getListaGrupo().isEmpty()){
			this.listaGrupo = new ArrayList<Grupo>();
		}
		
		return this.listaGrupo.add(grupo);
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}
}

package com.luremesoftware.adme.modelo;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.bbdd.UsuarioBbdd;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
public class Usuario extends Propietario{
	
	private String correo;
	private String contrasena;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	
	private ListaGrupo listaGrupo = null;
	
	/**
	 * Este Constructor crear un usuario a partir de su correo electronico
	 * recupera todos sus datos personales, los grupos donde participa y 
	 * todas sus publicaciones
	 * 
	 * @param correo
	 */
	public Usuario(String correo){
		super(correo);
		this.setCorreo(correo);
		//Se recogen los datos del Usuario de BBDD
		this.setDatosBbdd();
	}
	
	public Usuario(String correo, ListaGrupo listaGrupo){
		super(correo);
		this.setCorreo(correo);
		//Se recogen los datos del Usuario de BBDD
		this.setDatosBbdd();
		this.setListaGrupo(listaGrupo);
	}
	
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
		this.setId(correo);
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
	public ListaGrupo getListaGrupo(){
		if(this.listaGrupo == null){
			this.listaGrupo = new GestorGrupo().getListaGrupo(this);
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
	
	public boolean setListaGrupo(ListaGrupo listaGrupo){
		this.listaGrupo = listaGrupo;
		return true;
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}
	
	private Usuario getDatosBbdd(){
		ListaMetadato listaMetadato = new ListaMetadato();
		
		listaMetadato.add(
				new Metadato(NombreTabla.USUARIO,
							 ConstanteUsuario.CORREO,
							 FilterOperator.EQUAL,
							 this.correo));
		
		ListaUsuario listausuario = new UsuarioBbdd().getListaUsuario(listaMetadato);
		
		return listausuario.get(0);
	}
	
	private boolean setDatosBbdd(){
		Usuario usuario = this.getDatosBbdd();
		
		this.setContrasena(usuario.getContrasena());
		this.setNombre(usuario.getNombre());
		this.setApellido1(usuario.getApellido1());
		this.setApellido2(usuario.getApellido2());
		
		return true;
	}
	
	/*
	private boolean setDatosBbdd(){
		ListaMetadato listaMetadato = new ListaMetadato();
		
		listaMetadato = this.getDatosBbdd();
		
		for(Metadato metadato:listaMetadato){
			//TODO Hacer que funciona enum en switch case
			String nombreMetadato = metadato.getNombreMetadato().toString();
			if(nombreMetadato == ConstanteUsuario.CORREO.toString()){
				if(this.correo!=metadato.getValor().toString()){
					return false;
				}
				this.setCorreo(metadato.getValor().toString());
			}
			else if(nombreMetadato == ConstanteUsuario.CONTRASENA.toString()){
				this.setContrasena(metadato.getValor().toString());
			}
			else if(nombreMetadato == ConstanteUsuario.NOMBRE.toString()){
				this.setNombre(metadato.getValor().toString());
			}
			else if(nombreMetadato == ConstanteUsuario.APELLIDO1.toString()){
				this.setApellido1(metadato.getValor().toString());
			}
			else if(nombreMetadato == ConstanteUsuario.APELLIDO2.toString()){
				this.setApellido2(metadato.getValor().toString());
			}
		}
		
		return true;
	}*/
}

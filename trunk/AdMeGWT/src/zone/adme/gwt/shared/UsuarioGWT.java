package zone.adme.gwt.shared;

import java.io.Serializable;


/**
 * Clase Usuario
 * 
 * @author francesc3000@gmail.com
 *
*/
public class UsuarioGWT implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6786037227487428148L;
	private String correo;
	private String nombre;
	
	@SuppressWarnings("unused")
	private UsuarioGWT(){}
	
	public UsuarioGWT(String correo){
		this.correo = correo;
	}

	public String getCorreo(){
		return this.correo;
	}
	/*
	public String getContrasena(){
		return this.usuario.getContrasena();
	}
	*/
	public String getNombre(){
		return this.nombre;
	}
	/*
	public String getApellido1(){
		return this.usuario.getApellido1();
	}
	
	public String getApellido2(){
		return this.usuario.getApellido2();
	}
	/*
	public Grupo getGrupo(int indice){
		return this.listaGrupoRef.get(indice).get();
	}*/
	
	/**
	 * Si el listado de grupos está vacío recupera los grupos 
	 * donde participa el usuario en BBDD. Si el listado de grupos
	 * ya ha sido cargado se comporta igual que el método getListaGrupo()
	 * 
	 * @return
	 */
	/*
	public List<Grupo> getListaGrupo(){
		List<Grupo> listaGrupoNoRef = new ArrayList<Grupo>();
		
		for(Ref<Grupo> grupo:this.listaGrupoRef){
			listaGrupoNoRef.add(grupo.get());
		}
		
		return listaGrupoNoRef;
	}*/
	
	public boolean setCorreo(String correo){
		this.correo = correo;
		return true;
	}
	/*
	public boolean setContrasena(String contrasena){
		return this.usuario.setContrasena(contrasena);
	}*/
	
	public boolean setNombre(String nombre){
		this.nombre = nombre;
		return true;
	}
	
	/*
	public boolean setApellido1(String apellido1){
		return this.usuario.setApellido1(apellido1);
	}
	
	public boolean setApellido2(String apellido2){
		return this.usuario.setApellido2(apellido2);
	}
	/*
	public boolean addGrupo(Grupo grupo){
		return this.listaGrupoRef.add(Ref.create(grupo));
	}
	
	public boolean addListaGrupo(List<Grupo> listaGrupo){
		for(Grupo grupo:listaGrupo){
			this.addGrupo(grupo);
		}
		return true;
	}
	
	public boolean setPuntuacion(UsuarioGWT puntuador, int puntuacion){
		if(this.controlPuntuacionRef==null){
			this.controlPuntuacionRef = Ref.create(new GestorPropietario().creaControlPuntuacion(this));
		}
		return this.controlPuntuacionRef.get().setPuntuacion(puntuador, puntuacion);
	}

	public String toString(){
		return getCorreo() + " " + getNombre() + " " + getApellido1() + " " + getApellido2();

	}*/
}

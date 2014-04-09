/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Grupo
 */
package zone.adme.core.constantes;

import java.io.Serializable;

import zone.adme.core.modelo.Grupo;
import zone.adme.core.modelo.Propietario;
import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Usuario;

/**
 * @author francesc3000@gmail.com
 *
 */
public interface Constante extends Serializable{
	
	public static enum Tabla implements Constante, Serializable {
		PROPIETARIO(Propietario.class),
		USUARIO(Usuario.class),
		GRUPO(Grupo.class),
		PUBLICACION(Publi.class);
		
		private final Class<?> constante;
		
		private Tabla(Class<?> constante){
			this.constante = constante;
		}
		
		public String getName(){
			return this.constante.getName();
		}
		/*
		public String getSimpleName(){
			return this.constante.getSimpleName();
		}*/
	}
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Propietario
	 */
	public static enum ConstantePropietario implements Constante, Serializable{
		CLASE("Clase"),
		USUARIO("Usuario"),
		GRUPO("Grupo");
		
		private final String constante;
		
		private ConstantePropietario(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
	
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Usuario
	 */
	public static enum ConstanteUsuario implements Constante, Serializable{
		ID("IdUsuario"),
		CORREO("Correo"),
		CONTRASENA("Contrasena"),
		NOMBRE("Nombre"),
		APELLIDO1("Apellido1"),
		APELLIDO2("Apellido2");
		
		private final String constante;
		
		private ConstanteUsuario(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
		
		
	}
	
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Grupo
	 */
	public static enum ConstanteGrupo  implements Constante, Serializable{
		ID("IdGrupo"),
		NOMBRE("Nombre");
		
		private final String constante;
		
		private ConstanteGrupo(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
	
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Publi
	 */
	public static enum ConstantePubli implements Constante, Serializable{
		PROPIETARIO("propietario"),
		TITULO("titulo"),
		CIUDAD("ciudad"),
		DESCRIPCION("descripcion");
		
		private final String constante;
		
		private ConstantePubli(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
		
	public static enum ConstanteSession implements Constante, Serializable{
		USUARIO("User"),
		USUARIOMAIL("UserMail");
		
		private final String constante;
		
		private ConstanteSession(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
		
	public static enum ConstanteOperador implements Constante, Serializable{
		LESS_THAN("<"),
	    LESS_THAN_OR_EQUAL("<="),
	    GREATER_THAN(">"),
	    GREATER_THAN_OR_EQUAL(">="),
	    EQUAL("="),
	    NOT_EQUAL("!="),
	    IN("IN");
		
		private final String constante;
		
		private ConstanteOperador(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
}
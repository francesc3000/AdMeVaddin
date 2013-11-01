/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Grupo
 */
package com.luremesoftware.adme.constantes;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;

/**
 * @author francesc3000@gmail.com
 *
 */
public interface Constante {
	
	public enum Tabla implements Constante{
		PROPIETARIO(Propietario.class),
		USUARIO(Usuario.class),
		GRUPO(Grupo.class),
		PUBLICACION(Publi.class);
		
		@SuppressWarnings("rawtypes")
		private final Class constante;
		
		@SuppressWarnings("rawtypes")
		private Tabla(Class constante){
			this.constante = constante;
		}
		
		public String getName(){
			return this.constante.getName();
		}
		
		public String getSimpleName(){
			return this.constante.getSimpleName();
		}
	}
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Propietario
	 */
	public enum ConstantePropietario implements Constante{
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
	public enum ConstanteUsuario implements Constante{
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
	public static enum ConstanteGrupo  implements Constante{
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
	public static enum ConstantePubli implements Constante{
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
		
	public static enum ConstanteSession implements Constante{
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
}
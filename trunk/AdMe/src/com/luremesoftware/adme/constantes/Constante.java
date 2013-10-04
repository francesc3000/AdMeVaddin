/**
 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
 * la clase Grupo
 */
package com.luremesoftware.adme.constantes;

/**
 * @author francesc3000@gmail.com
 *
 */
public interface Constante {
	/**
	 * En esta clase introduciremos las constantes y metadatos pertenecientes a 
	 * la clase Propietario
	 */
	public enum ConstantePropietario implements Constante{
		CLASS("Class"),
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
	public enum ConstanteGrupo implements Constante{
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
	public enum ConstantePubli implements Constante{
		PROPIETARIO("Propietario"),
		CLASE("Clase"),
		TITULO("Titulo"),
		CIUDAD("Ciudad"),
		DESCRIPCION("Descripcion");
		
		private final String constante;
		
		private ConstantePubli(String constante){
			this.constante = constante;
		}
		
		public String toString(){
			return this.constante;
		}
	}
}
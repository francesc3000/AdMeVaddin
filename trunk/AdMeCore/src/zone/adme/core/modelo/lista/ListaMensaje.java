package zone.adme.core.modelo.lista;

import java.util.ArrayList;

import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Mensaje.TipoError;

public class ListaMensaje extends ArrayList<Mensaje> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Busca en la lista si existen algun mensaje de error
	 * @return
	 */
	public boolean contieneErrores(){
		for(Mensaje mensaje: this){
			if(mensaje.getTipo()==TipoError.ERROR){
				return true;
			}
		}
		return false;
	}

}

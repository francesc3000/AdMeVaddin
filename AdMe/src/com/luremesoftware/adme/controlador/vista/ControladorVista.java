package com.luremesoftware.adme.controlador.vista;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luremesoftware.adme.controlador.ControladorWeb;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.excepcion.MultipleUsuario;

public class ControladorVista {
	
	private ControladorWeb cw = null;
	
	public ControladorVista(){
		cw = new ControladorWeb();
	}
	
	/**
	 * Si el usuario esta registrado se retornan los datos del usuario a 
	 * partir de su correo electronico
	 * @param correo
	 * @return Si el usuario no esta registrado se retorna null
	 * @throws IOException 
	 * @throws MultipleUsuario 
	 */
	public Usuario acceder(HttpServletRequest request, HttpServletResponse response) throws IOException, MultipleUsuario{
		Acceder acceder = new Acceder(request, response);
		String correo = acceder.runAcceder();
		if(correo==null){
			return null;
		}
		return cw.getUsuario(correo);
	}
}

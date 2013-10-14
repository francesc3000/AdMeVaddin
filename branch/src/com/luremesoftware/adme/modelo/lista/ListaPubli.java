package com.luremesoftware.adme.modelo.lista;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;

import com.luremesoftware.adme.modelo.Publi;

@PersistenceCapable
public class ListaPubli extends ArrayList<Publi> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}

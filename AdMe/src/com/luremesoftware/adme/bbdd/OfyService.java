package com.luremesoftware.adme.bbdd;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.luremesoftware.adme.modelo.ControlPuntuacion;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Puntuacion;
import com.luremesoftware.adme.modelo.Usuario;

public final class OfyService {
	
	static {
		factory().register(Propietario.class);
		factory().register(Usuario.class);
		factory().register(Grupo.class);
		factory().register(Publi.class);
		factory().register(ControlPuntuacion.class);
		factory().register(Puntuacion.class);
    }
	
	public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}

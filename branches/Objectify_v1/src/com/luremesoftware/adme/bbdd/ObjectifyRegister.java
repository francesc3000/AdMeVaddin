package com.luremesoftware.adme.bbdd;

import com.googlecode.objectify.ObjectifyService;
import com.luremesoftware.adme.modelo.ControlPuntuacion;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Puntuacion;
import com.luremesoftware.adme.modelo.Usuario;

public final class ObjectifyRegister {
	static {
        ObjectifyService.register(Propietario.class);
        ObjectifyService.register(Usuario.class);
        ObjectifyService.register(Grupo.class);
        ObjectifyService.register(Publi.class);
        ObjectifyService.register(ControlPuntuacion.class);
        ObjectifyService.register(Puntuacion.class);
    }
}

package zone.adme.core.bbdd;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import zone.adme.core.modelo.ControlPuntuacion;
import zone.adme.core.modelo.Grupo;
import zone.adme.core.modelo.Propietario;
import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Puntuacion;
import zone.adme.core.modelo.Usuario;

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

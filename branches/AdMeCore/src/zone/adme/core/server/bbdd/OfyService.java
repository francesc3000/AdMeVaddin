package zone.adme.core.server.bbdd;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import zone.adme.core.shared.modelo.ControlPuntuacion;
import zone.adme.core.shared.modelo.Grupo;
import zone.adme.core.shared.modelo.Propietario;
import zone.adme.core.shared.modelo.Publi;
import zone.adme.core.shared.modelo.Puntuacion;
import zone.adme.core.shared.modelo.Usuario;

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

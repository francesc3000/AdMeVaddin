package zone.adme.bbdd;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import zone.adme.modelo.ControlPuntuacion;
import zone.adme.modelo.Grupo;
import zone.adme.modelo.Propietario;
import zone.adme.modelo.Publi;
import zone.adme.modelo.Puntuacion;
import zone.adme.modelo.Usuario;

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

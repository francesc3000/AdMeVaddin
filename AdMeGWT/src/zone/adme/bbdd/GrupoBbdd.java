package zone.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import static zone.adme.bbdd.OfyService.ofy;
import zone.adme.modelo.Grupo;
import zone.adme.modelo.lista.ListaMensaje;
import zone.adme.modelo.lista.ListaMetadato;

public class GrupoBbdd{
	
	public GrupoBbdd(){}
	
	public List<Grupo> getListaGrupo(ListaMetadato listaMetadato){
		return null;
	}
	
	public Grupo getGrupoByKey(Key<Grupo> key){		
		Grupo grupo = ofy().load().key(key).now();
		
		return grupo;
	}
	
	public List<Grupo> getListaGrupoByKey(List<Key<Grupo>> listaKey){
		List<Grupo> listaGrupo = new ArrayList<Grupo>();
		
		for(Key<Grupo> key:listaKey){
			listaGrupo.add(ofy().load().key(key).now());
		}
		
		return listaGrupo;
	}
	
	public ListaMensaje putGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().save().entity(grupo).now();
		
		return listaMensaje;
	}

	public ListaMensaje borraGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().delete().entity(grupo);
		
		return listaMensaje;
	}
}
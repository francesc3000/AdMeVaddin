package zone.adme.core.modelo.gestor;

import java.util.List;

import zone.adme.core.bbdd.GrupoBbdd;
import zone.adme.core.modelo.Grupo;

import com.googlecode.objectify.Key;
import zone.adme.core.modelo.lista.ListaMensaje;

public class GestorGrupo {

	private GrupoBbdd grupoBbdd =  new GrupoBbdd();
	
	public GestorGrupo(){}
	
	public Grupo getGrupoByKey(Key<Grupo> key){
		return this.grupoBbdd.getGrupoByKey(key);
	}

	public List<Grupo> getListaGrupoByKey(List<Key<Grupo>> listaKey){
		return this.grupoBbdd.getListaGrupoByKey(listaKey);
	}

	/**
	 * Regista un grupo en el sistema
	 * 
	 * @return Se retorna un listado de mensajes del sistema
	 */	
	public ListaMensaje putGrupo(Grupo grupo){
		return this.grupoBbdd.putGrupo(grupo);
	}

	public ListaMensaje borraGrupo(Grupo grupo){
		return this.grupoBbdd.borraGrupo(grupo);
	}
}

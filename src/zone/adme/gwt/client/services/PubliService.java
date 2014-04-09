package zone.adme.gwt.client.services;

import java.util.List;

import zone.adme.core.shared.constantes.Constante;
import zone.adme.core.shared.modelo.Mensaje;
import zone.adme.core.shared.modelo.Metadato;
import zone.adme.core.shared.modelo.Metadato2;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("PubliService")
public interface PubliService extends RemoteService {
	List<PubliGWT> getPubliGWT(List<Metadato> listaMetadato) throws IllegalArgumentException;
	
	List<Mensaje> putPubliGWT(PubliGWT publiGWT) throws IllegalArgumentException;
	
	List<PubliGWT> getPubliGWT2(Metadato2 listaMetadato) throws IllegalArgumentException;
	
}

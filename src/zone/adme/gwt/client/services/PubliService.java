package zone.adme.gwt.client.services;

import java.util.List;

import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Metadato;
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
	
}

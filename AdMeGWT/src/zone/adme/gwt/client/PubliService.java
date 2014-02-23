package zone.adme.gwt.client;

import java.util.List;

import zone.adme.gwt.shared.MensajeGWT;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("getPubliService")
public interface PubliService extends RemoteService {
	List<PubliGWT> getPubli() throws IllegalArgumentException;
	
	List<MensajeGWT> putPubli(PubliGWT publiGWT) throws IllegalArgumentException;
}

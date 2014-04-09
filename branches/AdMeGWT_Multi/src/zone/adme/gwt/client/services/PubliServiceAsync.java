package zone.adme.gwt.client.services;

import java.util.List;

import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Metadato;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PubliServiceAsync {

	void putPubliGWT(PubliGWT publiGWT, AsyncCallback<List<Mensaje>> callback);

	void getPubliGWT(List<Metadato> listaMetadato, AsyncCallback<List<PubliGWT>> callback);


}

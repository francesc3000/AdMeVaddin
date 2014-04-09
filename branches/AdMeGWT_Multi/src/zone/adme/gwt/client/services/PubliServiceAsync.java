package zone.adme.gwt.client.services;

import java.util.List;

import zone.adme.core.shared.constantes.Constante;
import zone.adme.core.shared.modelo.Mensaje;
import zone.adme.core.shared.modelo.Metadato;
import zone.adme.core.shared.modelo.Metadato2;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PubliServiceAsync {

	void putPubliGWT(PubliGWT publiGWT, AsyncCallback<List<Mensaje>> callback);

	void getPubliGWT(List<Metadato> listaMetadato, AsyncCallback<List<PubliGWT>> callback);

	void getPubliGWT2(Metadato2 listaMetadato, AsyncCallback<List<PubliGWT>> callback);
	
}

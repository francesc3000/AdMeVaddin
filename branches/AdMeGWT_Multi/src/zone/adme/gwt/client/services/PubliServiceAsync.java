package zone.adme.gwt.client.services;

import java.util.List;

import zone.adme.gwt.shared.MensajeGWT;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PubliServiceAsync {

	void getPubli( AsyncCallback<List<PubliGWT>> callback);

	void putPubli(PubliGWT publiGWT, AsyncCallback<List<MensajeGWT>> callback);

}

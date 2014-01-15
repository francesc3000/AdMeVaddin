package zone.adme.gwt.client;

import java.util.List;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetPubliServiceAsync {

	void getPubli( AsyncCallback<List<PubliGWT>> callback);

}

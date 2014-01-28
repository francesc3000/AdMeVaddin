package zone.adme.gwt.client;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface IntroPubliServiceAsync {

	void introducePubli(PubliGWT publi, AsyncCallback<Void> callback);

}

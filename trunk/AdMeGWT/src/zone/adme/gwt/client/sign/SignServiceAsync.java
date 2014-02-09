package zone.adme.gwt.client.sign;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SignServiceAsync {

	void signIn(String correo, AsyncCallback<UsuarioGWT> callback);

	void signOut(String correo, AsyncCallback<Boolean> callback);

}

package zone.adme.gwt.client.sign;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("signInService")
public interface SignService extends RemoteService {
	UsuarioGWT signIn(String correo);
	boolean signOut(String correo);
}

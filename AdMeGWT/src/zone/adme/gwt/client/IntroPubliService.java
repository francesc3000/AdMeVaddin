package zone.adme.gwt.client;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("introPubli")
public interface IntroPubliService extends RemoteService{
	public void introducePubli(PubliGWT publi) throws IllegalArgumentException;
}

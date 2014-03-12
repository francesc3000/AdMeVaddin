package zone.adme.gwt.client.views;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface SignView extends IsWidget{
	void clear();
	void disable();
	void setPresenter(Presenter presenter);
	void userRegistered(String userText);
	
	public interface Presenter
	{
		void login(String usuario, String contrasena);
		void googleIn();
		void PControlClicked();
		UsuarioGWT getUsuarioSession();
		void goTo(Place place);
		void startHandler();
		void stopHandler();
	}
}
